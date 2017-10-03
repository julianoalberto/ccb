package ccb.model;

public class Local {
	private String code;
	
	public Local(String code)
	{
		this.code = code;
	}
	
	public String getCode() 
	{
		return code;
	}
	
	public void setCode(String code) 
	{
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "Local [code=" + code + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
		{
			return true;
		}
		
		if (o == null)
		{
			return false;
		}
		
		if (getClass() != o.getClass())
		{
			return false;
		}
		
		Local other = (Local) o;
		if (code == null) 
		{
			if (other.code != null)
			{
				return false;
			}
		} 
		else if (!code.equals(other.code))
		{
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return code.hashCode();
	}
	
	
	
	
}
