
# Welcome to Ben's Address Parser

## Intro:
This was created because I work with large data sets that contain addresses and I needed a way to parse them so our system can upload them. As you know, addresses come in all shapes and sizes. This is my attempt to make a parser that is good enough (at least better than my existing one).

Since I work with large data sets I have access to a great series of test data and hope to get this working pretty well.  

NOTE: This is designed for US address only! (Maybe later down the road I'll improve it for international)

NOTE: Streets are super complex and sometimes used on their own so I moved them to their own package

### NOTE: When you use this parser you should only instantiate it once and use the same object. There is some large overhead of map building for initialization. If you let the AddressParser go out of scope it might need to reload the maps and have the load overhead all over again.
Usually I believe in the, don't optimize until you need to but this class might be one of the exceptions to rule ;-)


There is a tag "OPTIMIZE:" used to locate places were the code could be optimized 
either for speed or simplicity

## Requirements:
* This requires Java 1.7 or above


## References:
Address Components Definition:
https://www.fgdc.gov/standards/projects/FGDC-standards-projects/street-address/05-11.2ndDraft.CompleteDoc.pdf

You can get the list of all zip codes from: http://federalgovernmentzipcodes.us/

States to names: http://en.wikipedia.org/wiki/List_of_U.S._state_abbreviations

List of all Cities:
http://www.opengeocode.org/download.php  -- Use the "US State/Cities Dataset" dataset


For GeoCoding... add this much later...
http://results.openaddresses.io/

TODO: The default get value should be:
https://en.wikipedia.org/wiki/Coding_Accuracy_Support_System

Word Abbrivations:
http://pe.usps.gov/text/pub28/28apg.htm

NOT USED YET http://www.semaphorecorp.com/cgi/abbrev.html


## Release Notes

### 0.6.2
* Bug Fix - City Parser was not loading cities. (Null pointer exception)
* Added Address Number Prefix
* re-factored "primaryNumber" to "addressNumber"

### 0.6.1
* Added LICENSE.txt
* Added getValue to ApComponentBase - This changes a lot
* Moved PostDirectional to Street Name (with PreDirectional)

### 0.5.1
* Initial Check-in
Working version - Parses simple addresses




