package edu.odu.cs.cs350;

import java.io.File;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Objects;

public class Resource {
	
	private Path path;
	private File file;
	private String type;
	private String classification;
	private long size;
	private LinkedHashSet<HTMLDocument> allHTMLDocuments;
	
	/**
	* Non-default constructor
	* @param p a Path to initialize a Resource
	*/
	public Resource(Path p)
	{
		path = p;
		file = p.toFile();
		this.analyzePath();
		allHTMLDocuments = new LinkedHashSet<HTMLDocument>();
	}
	
	/**
	* @return the Path data value path
	*/
	public Path getPath()
	{
		return path;
	}
	
	/**
	* @return the File data value path
	*/
	public File getFile()
	{
		return file;
	}
	
	/**
	* @return the String data value type
	*/
	public String getType()
	{
		return type;
	}
	
	/**
	* @return the String data value classification
	*/
	public String getClassification()
	{
		return classification;
	}
	
	/**
	* @return the int data value size
	*/
	public long getSize()
	{
		return size;
	}
	
	/**
	* Analyze the path to set the values of type, classification, and size
	*/
	public void analyzePath()
	{
		//TODO
		size = file.length();
	}
	
	/**
	* @param i a int to set the data value size equal to
	*/
	public LinkedHashSet<HTMLDocument> getAllHTMLDocuments()
	{
		return allHTMLDocuments;
	}
	
	/**
	* @param d add a HTMLDocument to allHTMLDocuments
	*/
	public void addHTMLDocument(HTMLDocument d)
	{
		allHTMLDocuments.add(d);
	}
	
	/**
	* @return the LinkedHashSet of allHTMLDocuments
	*/
	public LinkedHashSet<HTMLDocument> getHTMLDocumentExist(HTMLDocument d)
	{
		return allHTMLDocuments;
	}
	
	/**
	* @return boolean value for if the given HTMLDocument is found in allHTMLDocuments
	*/
	public boolean doesHTMLDocumentExist(HTMLDocument d)
	{
		return allHTMLDocuments.contains(d);
	}
	
	/**
	* @return boolean value for if the two Resources are equal based on their Path
	*/
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Resource))
		{
			return false;
		}
		else
		{
			Resource r = (Resource) o;
			
			return path == r.path;
		}
	}
	
	/**
	* @return an int value for the hashCode
	*/
	public int hashCode()
	{
		//TODO
		return Objects.hash(path);
	}
	
	/**
	* @return a String value for output
	*/
	public String toString()
	{
		//TODO
		return "not implemented";
	}
}
