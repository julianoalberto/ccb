package ccb.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ccb.XLSParser;
import ccb.model.Local;
import ccb.model.Person;
import ccb.model.Record;

public class InsuranceDAO {
	
	private String inputFileName;
	
	private InputStream inputBytes;
	
	private ArrayList<Record> allRecords;
	
	private HashMap<String, Local> allLocals;
	
	public InsuranceDAO(String inputFileName)
	{
		this.inputFileName = inputFileName;
	}
	
	public InsuranceDAO(InputStream inputBytes)
	{
		this.inputBytes = inputBytes;
	}
	
	public HashMap<Person, ArrayList<Record>> getByLocal(Local local)
	{
		return this.getByLocal(local, -1);
	}
	
	public HashMap<Person, ArrayList<Record>> getByLocal(Local local, int personMaxAge)
	{
		HashMap<Person, ArrayList<Record>> byLocal = new HashMap<Person, ArrayList<Record>>();
		ArrayList<Record> records = null;
		
		for (Record record : allRecords) {
			Person person = record.getPerson();
			
			if (person.getAge() <= personMaxAge)
			{
				records = byLocal.get(person);
				
				if (record.getLocal().equals(local))
				{
					if (records == null)
					{
						records = new ArrayList<Record>();
						byLocal.put(person, records);
					}
					records.add(record);
				}
			}
			
		}
		return byLocal;
	}
	
	public void load() throws IOException, ParseException
	{
		allRecords = new ArrayList<Record>();
		allLocals = new HashMap<String, Local>();
		InputStream data = null;
		
		if (inputFileName != null)
		{
			data = new FileInputStream(new File(this.inputFileName));
		}
		else
		{
			data = this.inputBytes;
		}
		
		HSSFWorkbook workbook = new HSSFWorkbook(data);
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		for (int i = 0; i <= (sheet.getLastRowNum() - 1); i++)
		{
			HSSFRow row = sheet.getRow(i);
			Record record = XLSParser.parseRecord(row); 
			
			if (record != null)
			{
				allRecords.add(record);
				allLocals.put(record.getLocal().getCode(), record.getLocal());
			}
		}		
		
		workbook.close();
	}
	
	public ArrayList<Local> getAllLocals()
	{
		ArrayList<Local> locals = new ArrayList<Local>();
		
		Set<String> keys = allLocals.keySet();
		
		for (String key : keys) {
			locals.add(allLocals.get(key));
		}
		
		Collections.sort(locals);
		
		return locals;
	}
}
