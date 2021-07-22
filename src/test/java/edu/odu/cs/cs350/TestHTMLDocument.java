package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class TestHTMLDocument {
	
	HTMLDocument d1;
	HTMLDocument d2;
	Path p1;
	Path p2;

	@Before
	public void setUp()
	{
		p1 = Paths.get("index.html");
		d1 = new HTMLDocument(p1);
		p2 = Paths.get("http://www.google.com");
		d2 = new HTMLDocument(p2);
	}

	@Test
	public void testConstructor()
	{
		assertThat(d1.getPath().toString(), containsString("index.html"));
		assertThat(d1.getAllImages().size(), is(0));
		assertThat(d1.getAllStyles().size(), is(0));
		assertThat(d1.getAllScripts().size(), is(0));
		assertThat(d1.getAllAnchors().size(), is(0));
		assertTrue(d1.getAllImages().isEmpty());
		assertTrue(d1.getAllStyles().isEmpty());
		assertTrue(d1.getAllScripts().isEmpty());
		assertTrue(d1.getAllAnchors().isEmpty());
		assertThat(d1.getPageLinkCount(), is(0));
		assertThat(d1.getSiteLinkCount(), is(0));
		assertThat(d1.getExternalLinkCount(), is(0));
		
		assertThat(d2.getPath().toString(), containsString("http://www.google.com"));
		assertThat(d2.getAllImages().size(), is(0));
		assertThat(d2.getAllStyles().size(), is(0));
		assertThat(d2.getAllScripts().size(), is(0));
		assertThat(d2.getAllAnchors().size(), is(0));
		assertTrue(d2.getAllImages().isEmpty());
		assertTrue(d2.getAllStyles().isEmpty());
		assertTrue(d2.getAllScripts().isEmpty());
		assertTrue(d2.getAllAnchors().isEmpty());
		assertThat(d2.getPageLinkCount(), is(0));
		assertThat(d2.getSiteLinkCount(), is(0));
		assertThat(d2.getExternalLinkCount(), is(0));
	}
	
	@Test
	public void testAddImage()
	{
		Path imgPath = Paths.get("image.jpg");
		d1.addImage(imgPath);
		
		assertThat(d1.getPath().toString(), containsString("index.html"));
		
		assertThat(d1.getAllImages().size(), is(1));
		assertThat(d1.getAllStyles().size(), is(0));
		assertThat(d1.getAllScripts().size(), is(0));
		assertThat(d1.getAllAnchors().size(), is(0));
		
		assertTrue(d1.getAllImages().contains(imgPath));
		assertTrue(d1.getAllStyles().isEmpty());
		assertTrue(d1.getAllScripts().isEmpty());
		assertTrue(d1.getAllAnchors().isEmpty());
		
		assertThat(d1.getPageLinkCount(), is(0));
		assertThat(d1.getSiteLinkCount(), is(0));
		assertThat(d1.getExternalLinkCount(), is(0));
	}
	
	@Test
	public void testAddStyle()
	{
		Path cssPath = Paths.get("stylesheet.css");
		d1.addStyle(cssPath);
		
		assertThat(d1.getPath().toString(), containsString("index.html"));
		
		assertThat(d1.getAllImages().size(), is(0));
		assertThat(d1.getAllStyles().size(), is(1));
		assertThat(d1.getAllScripts().size(), is(0));
		assertThat(d1.getAllAnchors().size(), is(0));
		
		assertTrue(d1.getAllImages().isEmpty());
		assertTrue(d1.getAllStyles().contains(cssPath));
		assertTrue(d1.getAllScripts().isEmpty());
		assertTrue(d1.getAllAnchors().isEmpty());
		
		assertThat(d1.getPageLinkCount(), is(0));
		assertThat(d1.getSiteLinkCount(), is(0));
		assertThat(d1.getExternalLinkCount(), is(0));
	}

	@Test
	public void testAddScript()
	{
		Path scriptPath = Paths.get("script.js");
		d1.addScript(scriptPath);
		
		assertThat(d1.getPath().toString(), containsString("index.html"));
		
		assertThat(d1.getAllImages().size(), is(0));
		assertThat(d1.getAllStyles().size(), is(0));
		assertThat(d1.getAllScripts().size(), is(1));
		assertThat(d1.getAllAnchors().size(), is(0));

		assertTrue(d1.getAllImages().isEmpty());
		assertTrue(d1.getAllStyles().isEmpty());
		assertTrue(d1.getAllScripts().contains(scriptPath));
		assertTrue(d1.getAllAnchors().isEmpty());
		
		assertThat(d1.getPageLinkCount(), is(0));
		assertThat(d1.getSiteLinkCount(), is(0));
		assertThat(d1.getExternalLinkCount(), is(0));
	}
	
	@Test
	public void testAddAnchor()
	{
		Path anchorPath = Paths.get("aboutMe.html");
		d1.addAnchor(anchorPath);
		
		assertThat(d1.getPath().toString(), containsString("index.html"));
		
		assertThat(d1.getAllImages().size(), is(0));
		assertThat(d1.getAllStyles().size(), is(0));
		assertThat(d1.getAllScripts().size(), is(0));
		assertThat(d1.getAllAnchors().size(), is(1));

		assertTrue(d1.getAllImages().isEmpty());
		assertTrue(d1.getAllStyles().isEmpty());
		assertTrue(d1.getAllScripts().isEmpty());
		assertTrue(d1.getAllAnchors().contains(anchorPath));
		
		assertThat(d1.getPageLinkCount(), is(0));
		assertThat(d1.getSiteLinkCount(), is(0));
		assertThat(d1.getExternalLinkCount(), is(0));
	}
	
	@Test
	public void testIncreasePageLinkCount()
	{
		d1.increasePageLinkCount();
		
		assertThat(d1.getPath().toString(), containsString("index.html"));
		
		assertThat(d1.getAllImages().size(), is(0));
		assertThat(d1.getAllStyles().size(), is(0));
		assertThat(d1.getAllScripts().size(), is(0));
		assertThat(d1.getAllAnchors().size(), is(0));
		
		assertTrue(d1.getAllImages().isEmpty());
		assertTrue(d1.getAllStyles().isEmpty());
		assertTrue(d1.getAllScripts().isEmpty());
		assertTrue(d1.getAllAnchors().isEmpty());
		
		assertThat(d1.getPageLinkCount(), is(1));
		assertThat(d1.getSiteLinkCount(), is(0));
		assertThat(d1.getExternalLinkCount(), is(0));
	}
	
	@Test
	public void testIncreaseSiteLinkCount()
	{
		d1.increaseSiteLinkCount();
		
		assertThat(d1.getPath().toString(), containsString("index.html"));
		
		assertThat(d1.getAllImages().size(), is(0));
		assertThat(d1.getAllStyles().size(), is(0));
		assertThat(d1.getAllScripts().size(), is(0));
		assertThat(d1.getAllAnchors().size(), is(0));

		assertTrue(d1.getAllImages().isEmpty());
		assertTrue(d1.getAllStyles().isEmpty());
		assertTrue(d1.getAllScripts().isEmpty());
		assertTrue(d1.getAllAnchors().isEmpty());
		
		assertThat(d1.getPageLinkCount(), is(0));
		assertThat(d1.getSiteLinkCount(), is(1));
		assertThat(d1.getExternalLinkCount(), is(0));
	}
	
	@Test
	public void testIncreaseExternalLinkCount()
	{
		d1.increaseExternalLinkCount();
		
		assertThat(d1.getPath().toString(), containsString("index.html"));
		
		assertThat(d1.getAllImages().size(), is(0));
		assertThat(d1.getAllStyles().size(), is(0));
		assertThat(d1.getAllScripts().size(), is(0));
		assertThat(d1.getAllAnchors().size(), is(0));

		assertTrue(d1.getAllImages().isEmpty());
		assertTrue(d1.getAllStyles().isEmpty());
		assertTrue(d1.getAllScripts().isEmpty());
		assertTrue(d1.getAllAnchors().isEmpty());
		
		assertThat(d1.getPageLinkCount(), is(0));
		assertThat(d1.getSiteLinkCount(), is(0));
		assertThat(d1.getExternalLinkCount(), is(1));
	}
}
