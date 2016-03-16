
# Welcome to Ben's Address Parser

## Intro:
This was created because I work with large data sets that contain addresses and I needed a way to parse them so our system can upload them. As you know, addresses come in all shapes and sizes. This is my attempt to make a parser that is good enough (at least better than my existing one).

Since I work with large data sets I have access to a great series of test data and hope to get this working pretty well.  

NOTE: This is designed for US address only! (Maybe later down the road I'll improve it for international)



### NOTE: When you use this parser you should only instantiate it once and use the same object. There is some large overhead of map building for initialization. If you let the AddressParser go out of scope it might need to reload the maps and have the load overhead all over again.
Usually I believe in the, don't optimize until you need to but this class might be one of the exceptions to rule ;-)


## Requirements:
* This requires Java 1.7 or above

##Release Notes:

### 0.5.1: First Commit to github
* Working version at 99% success rate





## References:
Address Components Defintion:
https://www.fgdc.gov/standards/projects/FGDC-standards-projects/street-address/05-11.2ndDraft.CompleteDoc.pdf

You can get the list of all zip codes from: http://federalgovernmentzipcodes.us/

States to names: http://en.wikipedia.org/wiki/List_of_U.S._state_abbreviations

List of all Cities:
http://www.opengeocode.org/download.php  -- Use the "US State/Cities Dataset" dataset


For GeoCoding... add this much later...
http://results.openaddresses.io/



## TODO:

