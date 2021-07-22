package edu.odu.cs.cs350;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;

public class WebsiteParser {
	
	private Website site;
	
	/**
	* Non-default Constructor
	*/
	public WebsiteParser(Website w)
	{
		site = w;
	}
	
	/**
	* Walk through the website's pages beginning at the root directory
	 * @throws IOException 
	*/
	public void walkLocalPages() throws IOException
	{
		Files.walk(site.getLocalDirectory())
			.filter(Files::isRegularFile)
			.forEach(entry -> {
				if (site.getAllPages().size() < 1000)
				{
					Path tempPath = Paths.get(entry.toString());
					HTMLDocument doc = new HTMLDocument(tempPath);
					// Check for HTML content
					
					
					
					// Extract resources from current document
					DocumentParser dp = new DocumentParser(doc, site.getLocalDirectory());
					
					try {
						if (!dp.checkIfErrorPage())
						{
							dp.extractTags();
							
							site.addPage(doc);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
	}
	
	/**
	* Walk through the remote URLs
	*/
	public void walkRemotePages()
	{
		//TODO
	}

    /**
    * @return a LinkedHashSet of HTMLDocument named allPages
    */
    public LinkedHashSet<HTMLDocument> getSitePages()
    {
        return site.getAllPages();
    }

    /**
    * @return a LinkedHashSet of Resource named allResources
    */
    public LinkedHashSet<Resource> getSiteResources()
    {
    	return site.getAllResources();
    }

    /**
    * @return a LinkedHashSet of Path named remoteURLs
    */
    public LinkedHashSet<Path> getSiteURLs()
    {
    	return site.getRemoteURLs();
    }
}
