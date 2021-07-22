# WebsiteAnalysis
This program will analyze a local directory for specific website content. The directory must contain between 1 and 1000 web pages. An analysis will be done to help web developers determine how much data transfer is happening with each page. Images, videos, audio, and other types of files embedded in each page will be recorded and displayed in the three output files listed below.

## Usage
The __WebsiteAnalysis__ package can be downloaded as a .zip file and run on Windows, Linux, or OS/X by installing Java JRE 8.

At the end there will be three different output files as follows:

### 1. JSON
* One entry per page detailing:
    * Number of local images
    * Number of external images
    * Number of scripts referenced
    * Number of stylesheets utilized
    * Listing of images
    * Listing of scripts
    * Listing of stylesheets
    * Number of intra-page links
    * Number of intra-site links
    * Number of external links
* One entry per image detailing:
    * Number of pages on which it is displayed
    * Listing of pages on which it is displayed
* One entry per archive file detailing:
    * File size
    * Path to resource (relative to local site root)
* One entry per video file detailing:
    * File size
    * Path to resource (relative to local site root)
* One entry per audio file detailing:
    * File size
    * Path to resource (relative to local site root)
* One entry per non-categorized file detailing:
    * File size
    * Path to resource (relative to local site root)
### 2. Text File
* All local pages sorted lexicographically by directory starting with the site root
* Each line will take the form:
    * __*size page*__
        * _size_ - is equal to the total size of all images on the page
        * _page_ - is the local file path
* The final line will be the total size of all pages in the local site
### 3. Excel File
* The Excel document will consist of a count of all files sorted by type for each page analyzed.

~|A|B|C|D|E|F|G
-|-|-|-|-|-|-|-
1|Page|#Images|#CSS|#Scripts|#Links (Intra-page)|#Links (Internal)|#Links (External)
2|...|...|...|...|...|...|...
3|...|...|...|...|...|...|...
4|...|...|...|...|...|...|...

### 4. Output
The files mentioned above will be saved to the local root directory of the website and saved in the format `YYYYMMDD-hhmmss-summary.{json,txt,xlsx}` where:
* YYYY is the 4-digit year - 20xx
* MM is the 2-digit month (zero padded) - 01 to 12
* DD is the 2-digit day (zero padded) - 01 to 31
* hh is the 2-digit hour (zero padded) - 00 to 23
* mm is the 2-digit minute (zero padded) - 00 to 59
* ss is the 2-digit second (zero padded) - 00 to 59