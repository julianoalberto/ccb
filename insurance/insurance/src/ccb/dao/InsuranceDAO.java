package ccb.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ccb.XLSParser;
import ccb.model.Local;
import ccb.model.Person;
import ccb.model.Record;

public class InsuranceDAO {
	
	static final int ROW_INCREMENT = 2;
	static final int ROW_FIRST = 13;
	
	private String inputFileName;
	
	private InputStream inputBytes;
	
	private ArrayList<Record> allRecords;
	
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
				
		for (int i = ROW_FIRST; i <= (sheet.getLastRowNum() - 1); i += ROW_INCREMENT)
		{
			HSSFRow row = sheet.getRow(i);
			allRecords.add(XLSParser.parseRecord(row));			
		}		
		
		workbook.close();
	}
}
