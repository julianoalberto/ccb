package ccb.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
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
	public static final String PARAM_LOCAL_NAME_PREFIX = "local_name_";
	public static final String PARAM_ADM_PREFIX = "adm_";
	public static final String PARAM_CNPJ_PREFIX = "cnpj_";
	public static final String PARAM_RESP_PREFIX = "resp_";
	public static final String PARAM_RESP_PHONEL_PREFIX = "resp_phone_";
       
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
		
		if(filePart.getSize() > 0)
		{
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
			} catch (Exception e) {
				errors.add("Erro no arquivo de entrada: " + e.getMessage());
				e.printStackTrace();
			}
			
			fileContent.close();
			
		    os.flush();
	        os.close();
		}
		else
		{
			errors.add("Selecione um arquivo gerado pelo SIGA.");
		}
		
		if (!errors.isEmpty())
		{
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("errors.jsp").forward(request, response);
		}
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
					local.setName(paramMap.get(PARAM_LOCAL_NAME_PREFIX + i)[0]);
					local.setAdministration(paramMap.get(PARAM_ADM_PREFIX + i)[0]);
					local.setCnpj(paramMap.get(PARAM_CNPJ_PREFIX + i)[0]);
					local.setResponsible(paramMap.get(PARAM_RESP_PREFIX + i)[0]);
					local.setResponsiblePhone(paramMap.get(PARAM_RESP_PHONEL_PREFIX + i)[0]);
					
					// dirt tweak to fix a bug: sheet 9 has a space at the end ("Bairro 9 ")
					if (i == 9)
					{
						locals.put(local, "Bairro " + i + " ");
					}
					else
					{
						locals.put(local, "Bairro " + i);
					}
				}
			}
		}	
				
		return locals;
	}

}
