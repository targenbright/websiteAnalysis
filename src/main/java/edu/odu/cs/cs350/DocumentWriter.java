package edu.odu.cs.cs350;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DocumentWriter {
	
    private Website site;
    private String fileName;

	/**
	* Default constructor
	*/
    public DocumentWriter(Website w)
    {
    	site = w;
    	this.formatDate();
    }
    
    public String getFileName()
    {
    	return fileName;
    }
    
    /**
	* Uses LocalDateTime to pull current time
	* and then formats it for the file name
	*/
    public void formatDate()
    {
    	LocalDateTime time = LocalDateTime.now();
    	String timeString = new String("");
		Scanner scan = new Scanner(time.toString());
		scan.useDelimiter("[^0-9]");
		for (int i = 0; i < 6; i++)
		{
			if (i == 2)
			{
				timeString = timeString + scan.next() + "-";
			}
			else if (i == 5)
			{
				timeString = timeString + scan.next() + "-summary";
			}
			else
			{
				timeString = timeString + scan.next();
			}
		}
		
		fileName = timeString;
    }

    /**
	* Create a JSON file for output
	*/
    public void writeJSONFile() throws IOException {
        File file = new File(site.getLocalDirectory().toString() + File.separator + fileName + ".json");

        if(file.exists())
        {
        	System.out.println(file.getName() + " already exists.");
        }
        else 
        {
            file.createNewFile();
            System.out.println(file.getName() + " has been created in " + site.getLocalDirectory().toString() + " (" + site.getLocalDirectory().toRealPath().toString() +")");
        }
    }

    /**
	* Create Excel file for output
	*/
    public void writeExcelFile() throws IOException 
    {    	
    	// Create Excel workbook and spreadsheet
    	XSSFWorkbook workbook = new XSSFWorkbook();
    	XSSFSheet sheet = workbook.createSheet(getFileName());
    	
    	// Create a map of the data to insert
    	Map<String, Object[]> data = new TreeMap<String, Object[]>();
    	
    	// First row of data are headers
    	data.put("1", new Object[] {
    			"Page", 
    			"# Images", 
    			"# CSS", 
    			"# Scripts", 
    			"# Links (Intra-Page)", 
    			"# Links (Internal)", 
    			"# Links (External)"});
    	
    	// Range based for loop to take all of the counts from each HTMLDocument
    	int i = 2;
		for (HTMLDocument singleDoc : site.getAllPages()) {
			data.put(Integer.toString(i), new Object[] {
					// The path is incorrect
					// TODO fix pathing output
					singleDoc.getPath().toRealPath().toString(),
					singleDoc.getAllImages().size(),
					singleDoc.getAllStyles().size(),
					singleDoc.getAllScripts().size(),
					singleDoc.getPageLinkCount(),
					singleDoc.getSiteLinkCount(),
					singleDoc.getExternalLinkCount()
			});
			i++;
		}
    	
    	Set<String> keyset = data.keySet();
    	int rownum = 0;
    	for (String key : keyset)
    	{
    		Row row = sheet.createRow(rownum++);
    		Object[] objArr = data.get(key);
    		int cellNum = 0;
    		for (Object obj : objArr)
    		{
    			Cell cell = row.createCell(cellNum++);
    			if (obj instanceof String)
    			{
    				cell.setCellValue((String)obj);
    			}
    			else if (obj instanceof Integer)
    			{
    				cell.setCellValue((Integer)obj);
    			}
    		}
    	}
    	try
    	{
    		FileOutputStream out = new FileOutputStream(new File(site.getLocalDirectory().toString() + File.separator + fileName + ".xlsx"));
    		workbook.write(out);
    		out.close();
    		System.out.println(fileName + ".xlsx has been created in " + site.getLocalDirectory().toString() + " (" + site.getLocalDirectory().toRealPath().toString() +")");
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    }

    /**
	* Create text file for output
	*/
    public void writeTextFile() throws IOException 
    {
    	Path path = Paths.get(site.getLocalDirectory().toString() + File.separator + fileName + ".txt");
    	String data = "SIZE\t\tPATH\n";
        
        // Range based for loop to find the sum of the size of all images in each document
		for (HTMLDocument singleDoc : site.getAllPages()) {
			
			long size = 0;
			
			for (Path eachImg : singleDoc.getAllImages())
			{
				File img = eachImg.toFile();
				size += img.length();
			}
			
			// Format the size to MiB
			double formattedSize = 0;
			formattedSize = ((size / 8) / 1024) / 1024;
			
			// Add the size in MiB first, two tabs, file path, next line
			data.concat(formattedSize + " MiB\t\t" + singleDoc.getPath().toString() + "\n");
		}

        if(path.toFile().exists())
        {
        	System.out.println(path.toString() + " already exists.");
        }
        else 
        {
            Files.write(path, data.getBytes("UTF-8"));
            System.out.println(path.toFile().getName() + " has been created in " + site.getLocalDirectory().toString() + " (" + site.getLocalDirectory().toRealPath().toString() +")");
        }
    }
}
