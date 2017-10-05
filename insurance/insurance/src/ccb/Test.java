package ccb;

import java.util.Calendar;

public class Test {
	public static void main(String args[])
	{
		try {
			
			OutputBuilder o = new OutputBuilder("/home/jalberto/git/ccb/insurance/insurance/input/xls/voluntario_082017.xls", Calendar.AUGUST, "/home/jalberto/git/ccb/insurance/insurance/PLANILHA_modelo.xls");
			o.buildSpreadSheetOuput("/home/jalberto/git/ccb/insurance/insurance/output/Seguro_8_age_70_2.xls");
			
			
			
			
			
			
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
