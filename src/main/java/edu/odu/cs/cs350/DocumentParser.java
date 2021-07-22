package edu.odu.cs.cs350;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.lang.model.util.Elements;

import org.jsoup.Jsoup;

public class DocumentParser {

	private HTMLDocument page;
	private Path websiteRoot;
	private Iterable<? extends Element> videoTags;

	/**
	 * Non-default constructor
	 */
	public DocumentParser(HTMLDocument d, Path r)
	{
		page = d;
		websiteRoot = Paths.get(r.toString());
	}

	public boolean checkIfErrorPage() throws IOException
	{
		Document doc = Jsoup.parse(page.getPath().toFile(), "UTF-8");

		if (doc.title().contains("404"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void extractTags() throws IOException, URISyntaxException
	{
		Document doc = Jsoup.parse(page.getPath().toFile(), "UTF-8");
		Elements sourceTags = doc.select("[src]");
		Elements hrefTags = doc.select("[href]");

		for (Element src : sourceTags)
		{
			Path currentSRC = Paths.get(src.attr("src"));
			String pathString = page.getPath().toString()
					.substring(0, page.getPath().toString().lastIndexOf(File.separator) + 1) 
					+ currentSRC.toString();
			Path relativePath = Paths.get(pathString).toRealPath();
			
			System.out.println("Searching: " + page.getPath());
			if(src.tag().getName().contains("img"))
			{
				System.out.println("\tFound: " + currentSRC);
				page.addImage(relativePath);
				System.out.println("\t\tAdding image: " + relativePath);
			}

			else if(src.tag().getName().contains("script"))
			{
				System.out.println("\tFound: " + currentSRC);
				page.addScript(relativePath);
				System.out.println("\t\tAdding script: " + relativePath);
			}
		}
		
		// Anchor tag processing
		for (Element href : hrefTags)
		{
			// Take current tag link
			Path currentHREF = Paths.get(href.attr("href"));
			String pathString;
			Path relativePath;
			
			System.out.println("Searching: " + page.getPath());
			if (href.tag().getName().contains("a")) 
			{
				
				URI currentURI = new URI(href.attr("href"));
				System.out.println("\tFound: " + currentURI);
				
				// External Link
				// ------------------------------------------------------------------
				if (currentHREF.startsWith("http") 
						|| currentHREF.toString().contains("www")
						|| currentHREF.toString().contains(".com"))
				{
					page.addAnchor(currentHREF);
					page.increaseExternalLinkCount();
					System.out.println("\t\tAdding external anchor: " + currentHREF);
				}
				
				// Intra-Page Links
				// ------------------------------------------------------------------
				else if (Paths.get(href.attr("href")).toString().contains("#"))
				{
					// This is intra-page
					if (currentHREF.toString().startsWith("#"))
					{
						// Take the file name and the path, and append the tag link
						pathString = page.getPath().toString() + currentHREF.toString();
						// Resolve any relative pathing
						relativePath = Paths.get(pathString);
						page.addAnchor(relativePath);
						page.increasePageLinkCount();
						System.out.println("\t\tAdding page anchor: " + relativePath);
					}
					// This is intra-page of an internal site page
					else
					{
						pathString = page.getPath().toString()
								.substring(0, page.getPath().toString().lastIndexOf(File.separator) + 1)
								+ currentHREF.toString().substring(0, currentHREF.toString().indexOf("#"));
						relativePath = Paths.get(pathString).toRealPath();
						pathString = relativePath.toString() 
								+ currentHREF.toString().substring(currentHREF.toString().indexOf("#") + 1);
						relativePath = Paths.get(pathString);
						page.addAnchor(relativePath);
						page.increaseSiteLinkCount();
						System.out.println("\t\tAdding site anchor: " + relativePath);
					}
				}
				
				// Internal Site Links
				// ------------------------------------------------------------------
				else if ((Paths.get(page.getPath().toString()
						.substring(0, page.getPath().toString().lastIndexOf(File.separator) + 1)
						+ currentHREF.toString()).toRealPath()).toFile().exists())
				{
					// Take the file name and the path, and append the tag link
					pathString = page.getPath().toString()
							.substring(0, page.getPath().toString().lastIndexOf(File.separator) + 1)
							+ currentHREF.toString();
					// Resolve any relative pathing
					relativePath = Paths.get(pathString).toRealPath();
					page.addAnchor(relativePath);
					page.increaseSiteLinkCount();;
					System.out.println("\t\tAdding site anchor: " + relativePath);
				}
			} 
			else if (href.tag().getName().contains("link")) 
			{
				System.out.println("\tFound: " + currentHREF);
				page.addStyle(Paths.get(currentHREF.toUri()));
				System.out.println("\t\tAdding style: " + currentHREF);
			}
		}

		for (Element src : videoTags)
		{
			if(src.tag().getName().contains("video"))
			{
				page.addVideo(Paths.get(src.attr("src")));
				System.out.println("Adding " + Paths.get(src.attr("src")));
			}

			else if(src.tag().getName().contains("audio"))
			{
				page.addAudio(Paths.get(src.attr("src")));
				System.out.println("Adding " + Paths.get(src.attr("src")));
			}
	        }
        }
}
