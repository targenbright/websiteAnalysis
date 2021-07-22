package edu.odu.cs.cs350;

import java.nio.file.Path;
import java.util.LinkedHashSet;

public class Website {
	
	private LinkedHashSet<HTMLDocument> allPages;
	private LinkedHashSet<Resource> allResources;
	private Path localDirectory;
	private LinkedHashSet<Path> remoteURLs;

    /**
	* Non-default constructor
	*/
    public Website(Path p)
    {
    	localDirectory = p;
    	allPages = new LinkedHashSet<HTMLDocument>();
    	allResources = new LinkedHashSet<Resource>();
    	remoteURLs = new LinkedHashSet<Path>();
    }

    /**
	* @param d a HTMLDocument to be added to allPages
	*/
    public void addPage(HTMLDocument d)
    {
    	allPages.add(d);
    }
    
    /**
	* @return a LinkedHashSet of HTMLDocument named allPages
	*/
    public LinkedHashSet<HTMLDocument> getAllPages()
    {
    	return allPages;
    }

    /**
	* @param d a HTMLDocument to search for in allPages
	* @return boolean value for if the given HTMLDocument already exists in allPages
	*/
    public boolean doesPageExist(HTMLDocument d)
    {
    	return allPages.contains(d);
    }

    /**
	* @param r a Resource to be added to allResources
	*/
    public void addResource(Resource r)
    {
    	allResources.add(r);
    }
    
    /**
	* @return a LinkedHashSet of Resource named allResources
	*/
    public LinkedHashSet<Resource> getAllResources()
    {
    	return allResources;
    }

    /**
	* @param r a Resource to search for in allResources
	* @return boolean value for if the given Resource already exists in allResources
	*/
    public boolean doesResourceExist(Resource r)
    {
    	return allResources.contains(r);
    }

    /**
	* @return a Path to the localDirectory
	* @see localDirectory
	*/
    public Path getLocalDirectory()
    {
    	return localDirectory;
    }

    /**
	* @param p a Path to be added to remoteURLs
	*/
    public void addRemoteURL(Path p)
    {
    	remoteURLs.add(p);
    }
    
    /**
	* @return a LinkedHashSet of Path named remoteURLs
	*/
    public LinkedHashSet<Path> getRemoteURLs()
    {
    	return remoteURLs;
    }

    /**
	* @param p a Path to search for in remoteURLs
	* @return boolean value for if the given Path already exists in remoteURLs
	*/
    public boolean doesURLExist(Path p)
    {
    	return remoteURLs.contains(p);
    }
}
