package ccb.model;

import java.util.Calendar;
import java.util.Date;

public class Person implements Comparable<Person> {
	private String name;
	private Date birthday;
	private String cpf;
	
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) 
	{
		this.birthday = birthday;
	}
	
	public String getCpf() 
	{
		return cpf;
	}
	
	public void setCpf(String cpf) 
	{
		this.cpf = cpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
		{
			return true;
		}
		
		if (obj == null)
		{
			return false;
		}
		
		if (getClass() != obj.getClass())
		{
			return false;
		}
		
		Person other = (Person) obj;
		if (cpf == null) 
		{
			if (other.cpf != null)
			{
				return false;
			}
		} 
		else if (!cpf.equals(other.cpf))
		{
			return false;
		}
		return true;
	}
	
	public int getAge()
	{
		if (birthday == null)
		{
			return -1;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(birthday);
		
		int birthYear = cal.get(Calendar.YEAR);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		return currentYear - birthYear;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}

	@Override
	public int compareTo(Person person) {
		return this.getName().compareTo(person.getName());
	}
	
	
	
	
}
