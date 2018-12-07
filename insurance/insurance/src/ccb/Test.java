package ccb;

import java.util.Calendar;

public class Test {
	public static void main(String args[])
	{
		try {
			
			OutputBuilder o = new OutputBuilder("/home/jalberto/ccb/Listagem de Horas.xls", Calendar.NOVEMBER, "/home/jalberto/git/ccb/insurance/insurance/PLANILHA_modelo.xls");
			o.buildCompressedSpreadSheetOuput("/home/jalberto/Downloads/Seguro_11.zip");
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
