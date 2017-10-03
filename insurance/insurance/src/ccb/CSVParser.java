package ccb;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import ccb.model.Local;
import ccb.model.Person;
import ccb.model.Record;

public class CSVParser {
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	
	public static final String DELIMITER = ",";
	
	public static final int TOTAL_COLUMNS = 5;
	
	public static final int COLUMN_LOCAL = 0;
	public static final int COLUMN_NAME = 2;
	public static final int COLUMN_BIRTHDAY = 4;
	public static final int COLUMN_CPF = 3;
	public static final int COLUMN_DATE = 6;
	
	
	
	private static Person parsePerson(String[] columns)
	{
		Person person = new Person();
		
		String name = columns[COLUMN_NAME];
		String birthday = columns[COLUMN_BIRTHDAY];
		String cpf = columns[COLUMN_CPF];
		
		person.setName(name.trim());
		try {
			person.setBirthday(dateFormat.parse(birthday.trim()));
		} catch (ParseException e) {
			person.setBirthday(null);			
		}
		person.setCpf(cpf.trim());		
		
		return person;
	}
	
	private static Local parseLocal(String[] columns)
	{
		String localString = columns[COLUMN_LOCAL];
		
		//BR 22-1649 - JARDIM ANDRADE
		String[] parts = localString.split(" ");
		
		String code = parts[1];
				
		Local local = new Local(code.trim());
				
		return local;
	}
	
	public static Record parseRecord(String csvLine) throws ParseException
	{
		Record record = new Record();
		String[] columns = csvLine.split(DELIMITER);
		// BR 22-1649 - JARDIM ANDRADE, MARIA APARECIDA DOS SANTOS CARDOSO, 04/12/52, 849.952.548-20, 08/04/17
		Person person = parsePerson(columns);
		record.setPerson(person);
		Local local = parseLocal(columns);
		record.setLocal(local);
		
		String date = columns[COLUMN_DATE];
		record.setDate(dateFormat.parse(date.trim()));			
	
		return record;
	}

}
