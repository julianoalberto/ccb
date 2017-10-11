package ccb.model;

public class Local implements Comparable<Local> {
	private String code;
	private String name;
	private String cnpj;
	private String administration;
	private String responsible;
	private String responsiblePhone;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getAdministration() {
		return administration;
	}

	public void setAdministration(String administration) {
		this.administration = administration;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public String getResponsiblePhone() {
		return responsiblePhone;
	}

	public void setResponsiblePhone(String responsiblePhone) {
		this.responsiblePhone = responsiblePhone;
	}

	@Override
	public int compareTo(Local l) {
		return this.getCode().compareTo(l.getCode());
	}
	
	
	
	
}
