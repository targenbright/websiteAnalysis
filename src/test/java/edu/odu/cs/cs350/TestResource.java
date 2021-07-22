package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class TestResource {
	
	Resource r1;
	Resource r2;
	Resource r3;
	Resource r4;
	Resource r5;
	Path p1;
	Path p2;
	Path p3;
	Path p4;
	Path p5;

	@Before
	public void setUp()
	{
		p1 = Paths.get("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
		r1 = new Resource(p1);
		p2 = Paths.get("/videos/explosion.mp4");
		r2 = new Resource(p2);
		p3 = Paths.get("/audio/audiobook.m4a");
		r3 = new Resource(p3);
		p4 = Paths.get("/archives/largedata.zip");
		r4 = new Resource(p4);
		p5 = Paths.get("/executable/notavirus.exe");
		r5 = new Resource(p5);
	}

	@Test
	public void testConstructor() 
	{
		// Test external image
		assertThat(r1.getPath().toString(), containsString("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"));
		assertThat(r1.getType(), containsString("image"));
		assertThat(r1.getClassification(), containsString("external"));
		assertThat(r1.getSize(), is(0));
		assertThat(r1.getAllHTMLDocuments().size(), is(0));
		
		// Test internal video
		assertThat(r2.getPath().toString(), containsString("/videos/explosion.mp4"));
		assertThat(r2.getType(), containsString("video"));
		assertThat(r2.getClassification(), containsString("internal"));
		assertThat(r2.getSize(), is(0));
		assertThat(r2.getAllHTMLDocuments().size(), is(0));
		
		// Test internal audio
		assertThat(r3.getPath().toString(), containsString("/audio/audiobook.m4a"));
		assertThat(r3.getType(), containsString("audio"));
		assertThat(r3.getClassification(), containsString("internal"));
		assertThat(r3.getSize(), is(0));
		assertThat(r3.getAllHTMLDocuments().size(), is(0));

		// Test internal archive
		assertThat(r4.getPath().toString(), containsString("/archives/largedata.zip"));
		assertThat(r4.getType(), containsString("archive"));
		assertThat(r4.getClassification(), containsString("internal"));
		assertThat(r4.getSize(), is(0));
		assertThat(r4.getAllHTMLDocuments().size(), is(0));
		
		// Test internal other
		assertThat(r5.getPath().toString(), containsString("/executable/notavirus.exe"));
		assertThat(r5.getType(), containsString("other"));
		assertThat(r5.getClassification(), containsString("internal"));
		assertThat(r5.getSize(), is(0));
		assertThat(r5.getAllHTMLDocuments().size(), is(0));
		
		// Test equals, hashCode, toString
		assertThat(r1, is(not(equalTo(r2))));
		assertThat(r1.hashCode(), is(not(equalTo(r2.hashCode()))));
		// Is toString needed for this class?
	}

	// analyzePath() is used in the constructor. testConstructor tests this.
	/*
	@Test
	public void testAnalyzePath() 
	{
		r1.analyzePath();
		
		assertThat(r1.getPath().toString(), containsString("/images/smile.jpg"));
		assertThat(r1.getType(), containsString("jpg"));
		assertThat(r1.getClassification(), containsString("internal"));
		assertThat(r1.getSize(), is(0));
		assertThat(r1.getAllHTMLDocuments().size(), is(1));
	}
	*/
	
	@Test
	public void testAddHTMLDocument() 
	{
		Path page = Paths.get("/index.html");
		HTMLDocument document = new HTMLDocument(page);
		r2.addHTMLDocument(document);
		
		assertThat(r2.getPath().toString(), containsString("/videos/explosion.mp4"));
		assertThat(r2.getType(), containsString("video"));
		assertThat(r2.getClassification(), containsString("internal"));
		assertThat(r2.getSize(), is(0));
		assertThat(r2.getAllHTMLDocuments().size(), is(1));
		
		assertThat(r2, is(not(equalTo(r1))));
		assertThat(r2.hashCode(), is(not(equalTo(r1.hashCode()))));
	}
}
