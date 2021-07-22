package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class TestWebsite {
	
    Path websitePath;
    Path url1;
    Path url2;
    Path url3;
    
    HTMLDocument html1;
    HTMLDocument html2;
    HTMLDocument html3;
    
    Resource resource1;
    Resource resource2;
    Resource resource3;

    Website w1;
    
    @Before
    public void setUp() throws Exception 
    {
        // Generic placeholder paths
        websitePath = Paths.get("/home/user/testWebsite");
    
        html1 =  new HTMLDocument(Paths.get("/home/user/testDocument1"));
        html2 =  new HTMLDocument(Paths.get("/home/user/testDocument2"));
        html3 =  new HTMLDocument(Paths.get("/home/user/testDocument3"));
    
        resource1 = new Resource(Paths.get("/home/user/testResource1"));
        resource2 = new Resource(Paths.get("/home/user/testResource2"));
        resource3 = new Resource(Paths.get("/home/user/testResource3"));

        url1 = Paths.get("/home/user/testURL1");
        url2 = Paths.get("/home/user/testURL2");
        url3 = Paths.get("/home/user/testURL3");

        w1 = new Website(websitePath);
    }
    
    @Test
    public void testConstructor() 
    {
        assertThat(w1.getLocalDirectory().toString(), 
                containsString("/home/user/testWebsite"));
    }
    
    @Test
    public void testaddPage()
    {
        w1.addPage(html1);
        w1.addPage(html2);
        w1.addPage(html3);
    
        assertTrue(w1.doesPageExist(html1));
        assertTrue(w1.doesPageExist(html2));
        assertTrue(w1.doesPageExist(html3));

        assertThat(w1.getAllPages().size(), is(3));
    }
    
    @Test
    public void testaddResource()
    {
        w1.addResource(resource1);
        w1.addResource(resource2);
        w1.addResource(resource3);

        assertTrue(w1.doesResourceExist(resource1));
        assertTrue(w1.doesResourceExist(resource2));
        assertTrue(w1.doesResourceExist(resource3));

        assertThat(w1.getAllResources().size(), is(3));
    }

    @Test
    public void testaddRemoteURL()
    {
        w1.addRemoteURL(url1);
        w1.addRemoteURL(url2);
        w1.addRemoteURL(url3);

        assertTrue(w1.doesURLExist(url1));
        assertTrue(w1.doesURLExist(url2));
        assertTrue(w1.doesURLExist(url3));

        assertThat(w1.getRemoteURLs().size(), is(3));
    }
}
