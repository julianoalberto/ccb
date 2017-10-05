package ccb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import ccb.dao.InsuranceDAO;
import ccb.model.Local;
import ccb.model.Person;
import ccb.model.Record;

public class OutputBuilder {
	public final HashMap<Local, String> sheetNames;
	
	public static final String SHEET_CENTRO = "Bairro 1";
	public static final String SHEET_JD_ANDRADE = "Bairro 2";
	public static final String SHEET_JD_TRIUNFO = "Bairro 3";
	
	public static final int FIRST_ROW = 6;
	public static final int MONTH_ROW = 3;
	
	public static final int PERSON_COLUMN = 0;
	public static final int BIRTHDAY_COLUMN = 5;
	public static final int CPF_COLUMN = 6;
	public static final int DAY_COLUMN = 8;
	public static final int MONTH_COLUMN = 11;
	
	public static final int PERSON_MAX_AGE = 70;
	
	InsuranceDAO dao;
	int month;
	String templateFileName;

	public OutputBuilder(String inputFileName, int month, String templateFileName)
	{
		this.dao = new InsuranceDAO(inputFileName);
		this.month = month;
		this.templateFileName = templateFileName;		
		
		sheetNames = new HashMap<Local, String>();
		sheetNames.put(new Local("22-2413"), SHEET_CENTRO);
		sheetNames.put(new Local("22-1649"), SHEET_JD_ANDRADE);
		sheetNames.put(new Local("22-3696"), SHEET_JD_TRIUNFO);
	}
	
	public OutputBuilder(InputStream inputBytes, int month, String templateFileName)
	{
		this.dao = new InsuranceDAO(inputBytes);
		this.month = month;
		this.templateFileName = templateFileName;		
		
		sheetNames = new HashMap<Local, String>();
		sheetNames.put(new Local("22-2413"), SHEET_CENTRO);
		sheetNames.put(new Local("22-1649"), SHEET_JD_ANDRADE);
		sheetNames.put(new Local("22-3696"), SHEET_JD_TRIUNFO);
	}
	
	public OutputBuilder(InputStream inputBytes, int month, String templateFileName, HashMap<Local, String> sheetNames)
	{
		this.dao = new InsuranceDAO(inputBytes);
		this.month = month;
		this.templateFileName = templateFileName;		
		
		this.sheetNames = sheetNames;
	}

	public void buildSpreadSheetOuput(String outputFileName) throws IOException, ParseException
	{
		
		FileInputStream modelo = new FileInputStream(new File(templateFileName));
		HSSFWorkbook workbook = new HSSFWorkbook(modelo);
		HashMap<Person, ArrayList<Record>> byPerson = null; 
				
		dao.load();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		for (Local local : sheetNames.keySet()) {
			byPerson = dao.getByLocal(local, PERSON_MAX_AGE);
			buildSheet(byPerson, workbook, local, cal);
		}
		
		
		FileOutputStream out = 
                new FileOutputStream(new File(outputFileName));
        workbook.write(out);
        workbook.close();
        out.close();
	}
	
	public void buildSpreadSheetOuput(OutputStream outputBytes) throws IOException, ParseException
	{
		
		FileInputStream modelo = new FileInputStream(new File(templateFileName));
		HSSFWorkbook workbook = new HSSFWorkbook(modelo);
		HashMap<Person, ArrayList<Record>> byPerson = null; 
				
		dao.load();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		for (Local local : sheetNames.keySet()) {
			byPerson = dao.getByLocal(local, PERSON_MAX_AGE);
			buildSheet(byPerson, workbook, local, cal);
		}
		
		
		workbook.write(outputBytes);
        workbook.close();        
	}
	
	private void buildSheet(HashMap<Person, ArrayList<Record>> byPerson, HSSFWorkbook workbook, Local local, Calendar cal)
	{
		HSSFSheet sheet = workbook.getSheet(sheetNames.get(local));
		Set<Person> keys = byPerson.keySet();
		TreeSet<Person> orderedKeys = new TreeSet<Person>();
		for (Person person : keys) {
			orderedKeys.add(person);
		}
		
		Row monthRow = sheet.getRow(MONTH_ROW);
		Cell monthCell = monthRow.getCell(MONTH_COLUMN);
		monthCell.setCellValue(cal.getTime());
		
		int row = FIRST_ROW;
		for (Person person : orderedKeys) {
			ArrayList<Record> records = byPerson.get(person);
			Collections.sort(records);
			
			
			
			Row personRow = sheet.getRow(row);
			Cell nameCell = personRow.getCell(PERSON_COLUMN);
			nameCell.setCellValue(person.getName());
			
			
			Cell birthdayCell = personRow.getCell(BIRTHDAY_COLUMN);
			Date birthday = person.getBirthday();
			if (birthday != null)
			{
				birthdayCell.setCellValue(birthday);
			}
			else
			{
				birthdayCell.setCellValue("-");
			}
			
			Cell cpfCell = personRow.getCell(CPF_COLUMN);
			cpfCell.setCellValue(person.getCpf());
			
			
			String[] days = getArrayOfDays(records, month);
			
			int daysStartColumn = DAY_COLUMN;
			
			Cell dayCell = null;
			for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++)
			{
				dayCell = personRow.getCell(daysStartColumn+i);
				dayCell.setCellValue(days[i]);
				
			}
			
			row++;
			
		}		
	}
	
	private String[] getArrayOfDays(ArrayList<Record> records, int month)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month);
		
		int maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String[] days = new String[maxDaysInMonth];
		
		for (int i = 0; i < maxDaysInMonth; i++)
		{
			days[i] = null;
		}
					
		for (Record record : records) {
			cal.setTime(record.getDate());
			int day =  cal.get(Calendar.DAY_OF_MONTH);
			days[day -1] = "X";				
		}
		
		return days;
	}
}
