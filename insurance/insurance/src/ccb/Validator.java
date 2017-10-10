package ccb;

public class Validator {

	public static final String PATTERN_PIA = "PIA - ";
	public static final String PATTERN_ADM = "ADM - ";
	public static final String PATTERN_DL = "DL - ";
	public static final String PATTERN_DR = "DR - ";
	public static final String PATTERN_LOCAL = "BR ";
	
	
	public static boolean isValidLocal(String localString)
	{
		if (localString == null)
		{
			return false;
		}
		
		if (localString.length() == 0)
		{
			return false;
		}
		
		if (localString.startsWith(PATTERN_ADM))
		{
			return true;
		}
		
		if (localString.startsWith(PATTERN_DL))
		{
			return true;
		}
		
		if (localString.startsWith(PATTERN_DR))
		{
			return true;
		}
		
		if (localString.startsWith(PATTERN_LOCAL))
		{
			return true;
		}
		
		if (localString.startsWith(PATTERN_PIA))
		{
			return true;
		}
		
		return false;
	}
	
}
