package ccb.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import ccb.OutputBuilder;


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet({ "/seguro"})
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request,response);   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("file");
	    InputStream fileContent = filePart.getInputStream();
	    String month = request.getParameter("month");
	    
	    String outputFileName = "Seguro_" + (Integer.parseInt(month) + 1) + ".xls";
	    response.setContentType("application/xls");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + outputFileName + "\"");
	    
	    ServletOutputStream os = response.getOutputStream();
        OutputBuilder o = new OutputBuilder(fileContent, Integer.parseInt(month), "PLANILHA_modelo.xls");
		
        try {
			o.buildSpreadSheetOuput(os);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fileContent.close();
		
	    os.flush();
        os.close();
	}

}
