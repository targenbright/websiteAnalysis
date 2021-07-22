package edu.odu.cs.cs350;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AnalysisDriver {

	/**
	* Main function that runs when the program is executed
	* @throws IOException 
	 * @throws URISyntaxException 
	*/
	public static void main(String[] args) throws IOException, URISyntaxException
	{
		if (args.length != 0)
		{
			// Take the first argument passed as localRoot
			URI rootURI = new URI(args[0]);
			System.out.println("Passed in: " + rootURI);
			Path localRoot = Paths.get(rootURI.getPath().substring(1));
			System.out.println("Translated to: " + localRoot.toString());

			if (localRoot.toFile().exists())
			{
				localRoot.toRealPath();
				// Create a Website using localRoot
				Website site = new Website(localRoot);
				
				// Add all additional URLs or paths
				for (int i = 1; i < args.length; i++)
				{
					Path p = Paths.get(args[i]).toRealPath();

					if (p.toFile().exists())
					{
						site.addRemoteURL(p);
					}
				}
				
				// Analyze the Website
				WebsiteParser wp = new WebsiteParser(site);
				wp.walkLocalPages();
				wp.walkRemotePages();
				wp.extractResources();
				
				// Create, write, and output the files
				DocumentWriter dr = new DocumentWriter(site);
				dr.writeJSONFile();
				dr.writeTextFile();
				dr.writeExcelFile();
			}
			else
			{
				System.out.println("***************~ERROR~***************");
				System.out.println();
				System.out.println("Given rootDirectory does not exist.");
				System.out.println();
				System.out.println("*************************************");
				System.out.println();
				System.out.println("Usage: WebsiteAnalysis rootDirectory "
						+ "additionalOutsideDirectory1 additionalOutsideDirectory2 etc.");
			}
		}
		else
		{
			System.out.println("***************~ERROR~***************");
			System.out.println();
			System.out.println("You must specify a rootDirectory to analyze.");
			System.out.println();
			System.out.println("*************************************");
			System.out.println();
			System.out.println("Usage: WebsiteAnalysis rootDirectory "
					+ "additionalOutsideDirectory1 additionalOutsideDirectory2 etc.");
		}
	}
}
