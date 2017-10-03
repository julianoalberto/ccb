package ccb;

import java.util.Calendar;

public class Test {
	public static void main(String args[])
	{
		try {
			
			OutputBuilder o = new OutputBuilder("/home/jalberto/data/workspaces/ccb/insurance/input/xls/voluntario_062017.xls", Calendar.AUGUST, "/home/jalberto/data/workspaces/ccb/insurance/src/PLANILHA_modelo.xls");
			o.buildSpreadSheetOuput("/home/jalberto/data/workspaces/ccb/insurance/output/Seguro_6_age_70.xls");
			
			o = new OutputBuilder("/home/jalberto/data/workspaces/ccb/insurance/input/xls/voluntario_072017.xls", Calendar.AUGUST, "/home/jalberto/data/workspaces/ccb/insurance/src/PLANILHA_modelo.xls");
			o.buildSpreadSheetOuput("/home/jalberto/data/workspaces/ccb/insurance/output/Seguro_7_age_70.xls");
			
			o = new OutputBuilder("/home/jalberto/data/workspaces/ccb/insurance/input/xls/voluntario_082017.xls", Calendar.AUGUST, "/home/jalberto/data/workspaces/ccb/insurance/src/PLANILHA_modelo.xls");
			o.buildSpreadSheetOuput("/home/jalberto/data/workspaces/ccb/insurance/output/Seguro_8_age_70.xls");
			
			
			
			
			
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
