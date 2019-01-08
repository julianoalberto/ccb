package ccb.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

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
	
	public static final String TEMPLATE_FILE_NAME = "PLANILHA_modelo.xls";
	
	public static final String PARAM_MONTH = "month";
	public static final String PARAM_FILE = "file";
	
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
		ArrayList<String> errors = new ArrayList<String>();
		Part filePart = request.getPart(PARAM_FILE);
		
		String month = request.getParameter(PARAM_MONTH);
		
		if (PARAM_MONTH.equals(month))
		{
			errors.add("Selecione o mês dos apontamentos.");
		}
		else
		{
		
			if(filePart.getSize() > 0)
			{
				InputStream fileContent = filePart.getInputStream();
				
			    String outputFileName = "Seguro_" + (Integer.parseInt(month) + 1) + ".zip";
			    response.setContentType("application/zip");
			    response.setHeader("Content-Disposition", "attachment; filename=\"" + outputFileName + "\"");
			    
			    ServletOutputStream os = response.getOutputStream();
			    
			    OutputBuilder o = new OutputBuilder(fileContent, 
			    		Integer.parseInt(month), 
			    		request.getServletContext().getRealPath(TEMPLATE_FILE_NAME));
				
		        try {
					o.buildCompressedSpreadSheetOuput(os);
					os.flush();
			        os.close();
				} catch (Exception e) {
					//errors.add("Erro no processamento: " + e.getMessage());
					request.getSession().setAttribute("message", e.getMessage());
					
					StringWriter details = new StringWriter();
					e.printStackTrace(new PrintWriter(details));
					
					request.getSession().setAttribute("details", details.toString());
					
					response.sendRedirect("system_error.jsp");
					e.printStackTrace();
				}
				
				fileContent.close();
				
			    
			}
			else
			{
				errors.add("Selecione um arquivo gerado pelo SIGA.");
			}
		}
		
		if (!errors.isEmpty())
		{
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("errors.jsp").forward(request, response);
		}
	}
}
