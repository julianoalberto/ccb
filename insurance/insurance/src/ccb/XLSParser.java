package ccb;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import ccb.model.Local;
import ccb.model.Person;
import ccb.model.Record;

public class XLSParser {
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	
	public static final int COLUMN_LOCAL = 0;
	public static final int COLUMN_NAME = 7;
	public static final int COLUMN_BIRTHDAY = 9;
	public static final int COLUMN_CPF = 8;
	public static final int COLUMN_DATE = 12;
	
	private static Person parsePerson(HSSFRow row)
	{
		Person person = new Person();
		
		HSSFCell cell = row.getCell(COLUMN_NAME);
		String name = cell.getStringCellValue();
		
		cell = row.getCell(COLUMN_BIRTHDAY);
		String birthday = cell.getStringCellValue();
		
		cell = row.getCell(COLUMN_CPF);
		String cpf = cell.getStringCellValue();
		
		person.setName(name.trim());
		try {
			person.setBirthday(dateFormat.parse(birthday.trim()));
		} catch (ParseException e) {
			person.setBirthday(null);			
		}
		person.setCpf(cpf.trim());		
		
		return person;
	}
	
	private static Local parseLocal(HSSFRow row)
	{
		HSSFCell cell = row.getCell(COLUMN_LOCAL);
		String localString = cell.getStringCellValue();
		Local local = null;
		
		
		if (Validator.isValidLocal(localString))
		{
			if (localString.startsWith(Validator.PATTERN_LOCAL)) //BR 22-1649 - JARDIM ANDRADE
			{
				String[] parts = localString.split(" ");
				String code = parts[1];
				local = new Local(code.trim());
			}
			else if (localString.startsWith(Validator.PATTERN_ADM)) //ADM - PEDREIRA
			{
				local = new Local(localString.trim());
			}
			else if (localString.startsWith(Validator.PATTERN_DL)) //DL - PEDREIRA
			{
				local = new Local(localString.trim());
			}
			else if (localString.startsWith(Validator.PATTERN_PIA)) //PIA - AMPARO
			{
				local = new Local(localString.trim());
			}
		}
		
		return local;
	}
	
	public static Record parseRecord(HSSFRow row) throws ParseException
	{
		Record record = null;
		if (validateRow(row))
		{
			record = new Record();
			Person person = parsePerson(row);
			record.setPerson(person);
			Local local = parseLocal(row);
			record.setLocal(local);
			
			HSSFCell cell = row.getCell(COLUMN_DATE);
			String date = cell.getStringCellValue();
			record.setDate(dateFormat.parse(date.trim()));
		}
		else
		{
			System.out.println("Invalid row: \n" + row.toString());
		}
	
		return record;
	}
	
	private static boolean validateRow(HSSFRow row)
	{
		boolean valid = false;
		
		HSSFCell cell = row.getCell(COLUMN_LOCAL);
		String localString = cell.getStringCellValue();
		valid = Validator.isValidLocal(localString);
		
		return valid;
	}
}
