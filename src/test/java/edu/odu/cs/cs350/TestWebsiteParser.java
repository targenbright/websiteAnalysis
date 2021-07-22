package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;

import org.junit.Before;
import org.junit.Test;

public class TestWebsiteParser {
	
    private String html1;
    private String html2;
    private String html3;

    private Path url1;
    private Path url2;
    private Path url3;
    private 
    private HTMLDocument htmlDoc1;
    private HTMLDocument htmlDoc2;
    private HTMLDocument htmlDoc3;
    private 
    private Resource resource1;
    private Resource resource2;
    private Resource resource3;

    private Path websitePath;
    private Website w1;

    private LinkedHashSet<Path> pathSet;
    private LinkedHashSet<Resource> resourceSet;
    private LinkedHashSet<HTMLDocument> HTMLSet;

    private LinkedHashSet<Path> testPathSet;
    private LinkedHashSet<Resource> testResourceSet;
    private LinkedHashSet<HTMLDocument> testHTMLSet;

    private WebsiteParser webParser;

    @Before
    public void setUp() throws Exception 
    {
        /*
        html1 = "<html><head><title>First parse</title></head>"
        + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc1 = Jsoup.parse(html1);
            			
        html2 = "<html><head><title>First parse</title></head>"
        + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc2 = Jsoup.parse(html2);
            					
        html3 = "<html><head><title>First parse</title></head>"
        + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc3 = Jsoup.parse(html3);
        */

        // These paths are all placeholders
        websitePath = Paths.get("/home/user/testWebsite");
    
        htmlDoc1 =  new HTMLDocument(Paths.get("/home/user/testDocument1"));
        htmlDoc2 =  new HTMLDocument(Paths.get("/home/user/testDocument2"));
        htmlDoc3 =  new HTMLDocument(Paths.get("/home/user/testDocument3"));
    
        resource1 = new Resource(Paths.get("/home/user/testResource1"));
        resource2 = new Resource(Paths.get("/home/user/testResource2"));
        resource3 = new Resource(Paths.get("/home/user/testResource3"));

        url1 = Paths.get("/home/user/testURL1");
        url2 = Paths.get("/home/user/testURL2");
        url3 = Paths.get("/home/user/testURL3");

        w1 = new Website(websitePath);
        w1.addPage(htmlDoc1);
        w1.addPage(htmlDoc2);
        w1.addPage(htmlDoc3);

        w1.addResource(resource1);
        w1.addResource(resource2);
        w1.addResource(resource3);

        w1.addRemoteURL(url1);
        w1.addRemoteURL(url2);
        w1.addRemoteURL(url3);

        testHTMLSet = webParser.getSitePages();
        testPathSet = webParser.getSiteURLs();
        testResourceSet = webParser.getSiteResources();

        HTMLSet = w1.getAllPages();
        pathSet = w1.getRemoteURLs();
        resourceSet = w1.getAllResources(); 

        webParser = new WebsiteParser(w1);
    }
   	
   @Test
   public void testwalkLocalPages() throws IOException 
   {
       webParser.walkLocalPages();
       assertThat(testHTMLSet.size(),
               is(HTMLSet.size()));

       assertTrue(testHTMLSet.contains(htmlDoc1));
       assertTrue(testHTMLSet.contains(htmlDoc2));
       assertTrue(testHTMLSet.contains(htmlDoc3));
   }

   @Test
   public void testwalkRemotePages() throws IOException
   {
       webParser.walkRemotePages();
       assertThat(testHTMLSet.size(), 
               is(pathSet.size()));

       assertTrue(testPathSet.contains(htmlDoc1));
       assertTrue(testPathSet.contains(htmlDoc2));
       assertTrue(testPathSet.contains(htmlDoc3));
   }

   @Test
   public void testextractResources() throws IOException
   {
       webParser.extractResources();
       assertThat(testResourceSet.size(), 
               is(resourceSet.size()));

       assertTrue(testResourceSet.contains(htmlDoc1));
       assertTrue(testResourceSet.contains(htmlDoc2));
       assertTrue(testResourceSet.contains(htmlDoc3));
   }
}
