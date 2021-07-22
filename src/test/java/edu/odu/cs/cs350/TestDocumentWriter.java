package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class TestDocumentWriter {
	
	Path websitePath;
	Website site;
	DocumentWriter dc;

	@Before
	public void setUp() throws Exception 
	{
		websitePath = Paths.get(".");
		site = new Website(websitePath);
		dc = new DocumentWriter(site);
	}

	@Test
	public void testFormatDate() 
	{
		// "YYYYMMDD" Make sure all are numeric values
		assertThat(Character.isDigit(dc.getFileName().charAt(0)), is(true));
		assertThat(Character.isDigit(dc.getFileName().charAt(1)), is(true));
		assertThat(Character.isDigit(dc.getFileName().charAt(2)), is(true));
		assertThat(Character.isDigit(dc.getFileName().charAt(3)), is(true));
		assertThat(Character.isDigit(dc.getFileName().charAt(4)), is(true));
		assertThat(Character.isDigit(dc.getFileName().charAt(5)), is(true));
		assertThat(Character.isDigit(dc.getFileName().charAt(6)), is(true));
		assertThat(Character.isDigit(dc.getFileName().charAt(7)), is(true));
		
		// "-"
		// 20 is the numeric value for the data type DASH_PUNCTUATION
		assertThat(Character.getType(dc.getFileName().charAt(8)), is(20));
		
		// "hhmmss" Make sure all are numeric values
		assertThat(Character.isDigit(dc.getFileName().charAt(9)), is(true));
		assertThat(Character.isDigit(dc.getFileName().charAt(10)), is(true));
		assertThat(Character.isDigit(dc.getFileName().charAt(11)), is(true));
		assertThat(Character.isDigit(dc.getFileName().charAt(12)), is(true));
		assertThat(Character.isDigit(dc.getFileName().charAt(13)), is(true));
		assertThat(Character.isDigit(dc.getFileName().charAt(14)), is(true));
		
		// "-summary" Make sure all are letter values except first dash
		// 20 is the numeric value for the data type DASH_PUNCTUATION
		assertThat(Character.getType(dc.getFileName().charAt(15)), is(20));
		assertThat(Character.isLetter(dc.getFileName().charAt(16)), is(true));
		assertThat(Character.isLetter(dc.getFileName().charAt(17)), is(true));
		assertThat(Character.isLetter(dc.getFileName().charAt(18)), is(true));
		assertThat(Character.isLetter(dc.getFileName().charAt(19)), is(true));
		assertThat(Character.isLetter(dc.getFileName().charAt(20)), is(true));
		assertThat(Character.isLetter(dc.getFileName().charAt(21)), is(true));
		assertThat(Character.isLetter(dc.getFileName().charAt(22)), is(true));
	}
	
	@Test
	public void testWriteJSONFile() throws IOException
	{
		File outputFile = new File(site.getLocalDirectory().toString() + "/" + dc.getFileName() + ".json");
		dc.writeJSONFile();
		assertThat(outputFile.exists(), is(true));
		outputFile.delete();
	}
	
	@Test
	public void testWriteExcelFile() throws IOException
	{
		File outputFile = new File(site.getLocalDirectory().toString() + "/" + dc.getFileName() + ".xlsx");
		dc.writeExcelFile();
		assertThat(outputFile.exists(), is(true));
		outputFile.delete();
	}
	
	@Test
	public void testWriteTextFile() throws IOException
	{
		File outputFile = new File(site.getLocalDirectory().toString() + "/" + dc.getFileName() + ".txt");
		dc.writeTextFile();
		assertThat(outputFile.exists(), is(true));
		outputFile.delete();
	}
}
