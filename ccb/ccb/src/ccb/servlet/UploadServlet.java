package ccb.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

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
import ccb.model.Local;


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
	
	public static final String PARAM_LOCAL_PREFIX = "local_";
	public static final String PARAM_LOCAL_1 = "local_1";
	public static final String PARAM_LOCAL_2 = "local_2";
	public static final String PARAM_LOCAL_3 = "local_3";
	public static final String PARAM_LOCAL_4 = "local_4";
	public static final String PARAM_LOCAL_5 = "local_5";
	public static final String PARAM_LOCAL_6 = "local_6";
	public static final String PARAM_LOCAL_7 = "local_7";
	public static final String PARAM_LOCAL_8 = "local_8";
	public static final String PARAM_LOCAL_9 = "local_9";
	public static final String PARAM_LOCAL_10 = "local_10";
	
       
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
		Part filePart = request.getPart(PARAM_FILE);
	    InputStream fileContent = filePart.getInputStream();
	    String month = request.getParameter(PARAM_MONTH);
	    
	    String outputFileName = "Seguro_" + (Integer.parseInt(month) + 1) + ".xls";
	    response.setContentType("application/xls");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + outputFileName + "\"");
	    
	    HashMap<Local, String> locals = parseLocals(request);
	    
	    
	    ServletOutputStream os = response.getOutputStream();
        OutputBuilder o = new OutputBuilder(fileContent, Integer.parseInt(month), TEMPLATE_FILE_NAME, locals);
		
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
	
	private HashMap<Local, String> parseLocals(HttpServletRequest request)
	{
		HashMap<Local, String> locals = new HashMap<Local, String>();
		
		Map<String, String[]> paramMap = request.getParameterMap();
		
		for (int i = 1; i <= 10; i++)
		{
			String[] paramLocal = paramMap.get(PARAM_LOCAL_PREFIX + i);
			if (paramLocal != null)
			{
				String localString = paramLocal[0];
				if (localString != null && localString.length() > 0)
				{
					Local local = new Local(localString);
					locals.put(local, "Bairro " + i);
				}
			}
		}	
				
		return locals;
	}

}
