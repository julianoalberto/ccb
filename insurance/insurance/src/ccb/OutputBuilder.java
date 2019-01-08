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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import ccb.dao.InsuranceDAO;
import ccb.model.Local;
import ccb.model.Person;
import ccb.model.Record;

public class OutputBuilder {
	public static final int LOCAL_NAME_ROW = 1;
	public static final int LOCAL_NAME_COLUMN = 3;
	
	public static final int CNPJ_ROW = 3;
	public static final int CNPJ_COLUMN = 1;
	
	public static final int RESPONSIBLE_ROW = 1;
	public static final int RESPONSIBLE_COLUMN = 9;
	
	public static final int RESPONSIBLE_PHONE_ROW = 1;
	public static final int RESPONSIBLE_PHONE_COLUMN = 15;
	
	public static final int ADMINISTRATION_ROW = 2;
	public static final int ADMINISTRATION_COLUMN = 3;
	
	
	public static final int FIRST_ROW = 6;
	public static final int MONTH_ROW = 3;
	
	public static final int PERSON_COLUMN = 0;
	public static final int BIRTHDAY_COLUMN = 5;
	public static final int CPF_COLUMN = 6;
	public static final int DAY_COLUMN = 8;
	public static final int MONTH_COLUMN = 11;
	
	public static final int PERSON_MAX_AGE = 70;
	
	public static final int FIRST_SHEET = 0;
	public static final int LAST_SHEET = 9;
	public static final int MAX_SHEET = LAST_SHEET + 1;
	
	InsuranceDAO dao;
	int month;
	String templateFileName;
	
	public OutputBuilder(String inputFileName, int month, String templateFileName)
	{
		this.dao = new InsuranceDAO(inputFileName);
		this.month = month;
		this.templateFileName = templateFileName;
	}
	
	
	public OutputBuilder(InputStream inputBytes, int month, String templateFileName)
	{
		this.dao = new InsuranceDAO(inputBytes);
		this.month = month;
		this.templateFileName = templateFileName;
	}
	
	public void buildSpreadSheetOuput(String outputFileName) throws IOException, ParseException
	{
		FileOutputStream out = new FileOutputStream(new File(outputFileName));
		this.buildSpreadSheetOuput(out);
        out.close();
	}
	
	public void buildCompressedSpreadSheetOuput(String outputZipFileName) throws IOException, ParseException
	{
		FileOutputStream out = new FileOutputStream(new File(outputZipFileName));
		this.buildCompressedSpreadSheetOuput(out);
		out.close();
	}
	
	
	public void buildCompressedSpreadSheetOuput(OutputStream outputStream) throws IOException, ParseException
	{
		FileInputStream modelo = new FileInputStream(new File(templateFileName));
		ZipOutputStream out = new ZipOutputStream(outputStream);
		
		dao.load();
		ArrayList<Local> locals = dao.getAllLocals();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		int sheetIndex = FIRST_SHEET;
		int localIndex = 0;
		int filesCount = getFilesCount(locals.size());
		
		for (int i = 0; i < filesCount; i++)
		{
			ZipEntry e = new ZipEntry("Seguro_" + (i + 1) + ".xls");
			out.putNextEntry(e);
						
			HSSFWorkbook workbook = new HSSFWorkbook(modelo);
			HashMap<Person, ArrayList<Record>> byPerson = null; 
			
			for (int j = localIndex; j < locals.size(); j++, localIndex++, sheetIndex++) {
				Local local = locals.get(localIndex);
				byPerson = dao.getByLocal(local, PERSON_MAX_AGE);
				buildSheet(byPerson, workbook, sheetIndex, local, cal);
				
				if (sheetIndex == LAST_SHEET || localIndex == locals.size() - 1)
				{
					sheetIndex = FIRST_SHEET;
					workbook.write(out);
			        workbook.close();
			        localIndex++;
					break;
				}
			}		
			out.closeEntry();
		}
		out.close();
		out.flush();
	}
	
	/*
	 * Calculate the number of XLS files based on the maximum number of sheets per file
	 */
	private int getFilesCount(int size) {
		int a = size / MAX_SHEET;
		if (a == 0)
		{
			return 1;
		}
		
		if (size % MAX_SHEET > 0)
		{
			a++;
		}
		return a;
	}


	public void buildSpreadSheetOuput(OutputStream outputBytes) throws IOException, ParseException
	{
		
		FileInputStream modelo = new FileInputStream(new File(templateFileName));
		HSSFWorkbook workbook = new HSSFWorkbook(modelo);
		HashMap<Person, ArrayList<Record>> byPerson = null; 
				
		dao.load();
		ArrayList<Local> locals = dao.getAllLocals();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		int sheetIndex = FIRST_SHEET;
		
		for (Local local : locals) {
			byPerson = dao.getByLocal(local, PERSON_MAX_AGE);
			buildSheet(byPerson, workbook, sheetIndex, local, cal);
			sheetIndex++;
			if (sheetIndex > LAST_SHEET)
			{
				break;
			}
		}
		
		workbook.write(outputBytes);
        workbook.close();
	}
	
	
	
	private void buildSheet(HashMap<Person, ArrayList<Record>> byPerson, HSSFWorkbook workbook, int sheetIndex, Local local, Calendar cal)
	{
		System.out.println("Local: " + local.getCode());
		System.out.println("sheetIndex: " + sheetIndex);
		
		HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
		Set<Person> keys = byPerson.keySet();
		TreeSet<Person> orderedKeys = new TreeSet<Person>();
		for (Person person : keys) {
			orderedKeys.add(person);
		}
		
		Row monthRow = sheet.getRow(MONTH_ROW);
		Cell monthCell = monthRow.getCell(MONTH_COLUMN);
		monthCell.setCellValue(cal.getTime());
		
		buildHeader(local, sheet);
		
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
	
	private void buildHeader(Local local, HSSFSheet sheet)
	{
		Row rowName = sheet.getRow(LOCAL_NAME_ROW);
		Cell cellName = rowName.getCell(LOCAL_NAME_COLUMN);
		cellName.setCellValue(local.getName());
		
		Row rowAdm = sheet.getRow(ADMINISTRATION_ROW);
		Cell cellAdm = rowAdm.getCell(ADMINISTRATION_COLUMN);
		cellAdm.setCellValue(local.getAdministration()); 
		
		Row rowCnpj = sheet.getRow(CNPJ_ROW);
		Cell cellCnpj = rowCnpj.getCell(CNPJ_COLUMN);
		cellCnpj.setCellValue(local.getCnpj()); 
		
		Row rowResp = sheet.getRow(RESPONSIBLE_ROW);
		Cell cellResp = rowResp.getCell(RESPONSIBLE_COLUMN);
		cellResp.setCellValue(local.getResponsible());
		
		Row rowRespPhone = sheet.getRow(RESPONSIBLE_PHONE_ROW);
		Cell cellRespPhone = rowRespPhone.getCell(RESPONSIBLE_PHONE_COLUMN);
		cellRespPhone.setCellValue(local.getResponsiblePhone());
		
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
