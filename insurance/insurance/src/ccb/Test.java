package ccb;

import java.util.Calendar;

public class Test {
	public static void main(String args[])
	{
		try {
			
			OutputBuilder o = new OutputBuilder("/home/jalberto/Downloads/ListagemHoras_amparo_092017.xls", Calendar.SEPTEMBER, "/home/jalberto/git/ccb/insurance/insurance/PLANILHA_modelo.xls");
			o.buildCompressedSpreadSheetOuput("/home/jalberto/Downloads/Seguro_9_amparo.zip");
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
