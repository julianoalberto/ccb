package ccb.model;

import java.util.Date;

public class Record implements Comparable<Record> 
{
	private Person person;
	private Local local;
	private Date date;
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Local getLocal() {
		return local;
	}
	
	public void setLocal(Local local) {
		this.local = local;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Record [person=" + person + ", local=" + local + ", date="
				+ date + "]";
	}

	@Override
	public int compareTo(Record record) {
		return this.getDate().compareTo(record.getDate());
	}
}
