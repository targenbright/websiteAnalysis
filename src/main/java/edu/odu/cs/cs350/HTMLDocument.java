package edu.odu.cs.cs350;

import java.nio.file.Path;
import java.util.LinkedHashSet;

public class HTMLDocument {

    private Path path;
    private LinkedHashSet<Path> allImages;
    private LinkedHashSet<Path> allStyles;
    private LinkedHashSet<Path> allScripts;
    private LinkedHashSet<Path> allAnchors;
    private LinkedHashSet<Path> allVideos;
    private LinkedHashSet<Path> allAudio;
    private int pageLinkCount;
    private int siteLinkCount;
    private int externalLinkCount;


    /**
     * Non-default constructor
     * @param p a Path for the location of the HTMLDocument
     */
    public HTMLDocument(Path p)
    {
        path = p;
        allImages = new LinkedHashSet<Path>();
        allStyles = new LinkedHashSet<Path>();
        allScripts = new LinkedHashSet<Path>();
        allAnchors = new LinkedHashSet<Path>();
    }

    /**
     * @return the HTMLDocument's path
     */
    public Path getPath()
    {
        return path;
    }

    /**
     * Add the Path of an image to allImages
     * @param p a Path to be added to allImages
     */
    public void addImage(Path p)
    {
        allImages.add(p);
    }

    /**
     * @return the LinkedHashSet allImages
     */
    public LinkedHashSet<Path> getAllImages()
    {
        return allImages;
    }

    /**
     * @param p a Path to search for in allImages
     * @return boolean value for if the given Path was found in allImages
     */
    public boolean doesImageExist(Path p)
    {
        return allImages.contains(p);
    }

    /**
     * @param p a Path to add to allStyles
     */
    public void addStyle(Path p)
    {
        allStyles.add(p);
    }

    /**
     * @return the LinkedHashSet allStyles
     */
    public LinkedHashSet<Path> getAllStyles()
    {
        return allStyles;
    }

    /**
     * @param p a Path to search for in allStyles
     * @return boolean value for if the given Path was found in allStyles
     */
    public boolean doesStyleExist(Path p)
    {
        return allStyles.contains(p);
    }

    /**
     * @param p a Path to add to allScripts
     */
    public void addScript(Path p)
    {
        allScripts.add(p);
    }

    /**
     * @return the LinkedHashSet allScripts
     */
    public LinkedHashSet<Path> getAllScripts()
    {
        return allScripts;
    }

    /**
     * @param p a Path to search for in allScripts
     * @return boolean value for if the given Path was found in allScripts
     */
    public boolean doesScriptExist(Path p)
    {
        return allScripts.contains(p);
    }

    /**
     * @param p a Path to add to allAnchors
     */
    public void addAnchor(Path p)
    {
        allAnchors.add(p);
    }

    /**
     * @return the LinkedHashSet allAnchors
     */
    public LinkedHashSet<Path> getAllAnchors()
    {
        return allAnchors;
    }

    /**
     * @param p a Path to search for in allAnchors
     * @return boolean value for if the given Path was found in allAnchors
     */
    public boolean doesAnchorExist(Path p)
    {
        return allAnchors.contains(p);
    }

    /**
     * Increases the pageLinkCount by 1
     */
    public void increasePageLinkCount()
    {
        pageLinkCount++;
    }

    /**
     * @return an int value for pageLinkCount
     */
    public int getPageLinkCount()
    {
        return pageLinkCount;
    }

    /**
     * Increases the siteLinkCount by 1
     */
    public void increaseSiteLinkCount()
    {
        siteLinkCount++;
    }

    /**
     * @return an int value for siteLinkCount
     */
    public int getSiteLinkCount()
    {
        return siteLinkCount;
    }

    /**
     * Increases the pageLinkCount by 1
     */
    public void increaseExternalLinkCount()
    {
        externalLinkCount++;
    }

    /**
     * @return an int value for externalLinkCount
     */
    public int getExternalLinkCount()
    {
        return externalLinkCount;
    }

    /**
     * @return boolean value for if the two HTMLDocuments are equal based on their Path
     */
    public boolean equals()
    {
        //TODO
        return false;
    }

    /**
     * @return an int value for the hashCode
     */
    public int hashCode()
    {
        return -1;
    }

    /**
     * @return a String value for output
     */
    public String toString()
    {
        return "not implemented";
    }

    /**
     * @param p a path to add to allVideos
     */
    public void addVideo(Path p) 
    {
        allVideos.add(p);
    }

    /**
     * @return the {@link LinkedHashSet} allVideos
     */
    public LinkedHashSet<Path> getAllVideos()
    {
        return allVideos;
    }

    /**
     * @param p a path to search for in allVideos
     * @return boolean value for if the given Path was found in allVideos
     */
    public boolean doesVideoExist(Path p)
    {
        return allVideos.contains(p);
    }

    /**
     * @param p a path to add to allAudio
     */
    public void addAudio(Path p)
    {
        allAudio.add(p);
    }

    /**
     * @return the {@link LinkedHashSet} allAudio
     */
    public LinkedHashSet<Path> getAllAudio()
    {
        return allAudio;
    }

    /**
     * @param p a path to search for in allAudio
     * @return boolean value for if the given Path was found in allAnchors
     */
    public boolean doesAudioExist(Path p)
    {
        return allAudio.contains(p);
    }
}
