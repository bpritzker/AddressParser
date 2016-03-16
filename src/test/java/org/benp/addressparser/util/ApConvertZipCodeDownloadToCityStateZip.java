package org.benp.addressparser.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

/**
 * This class is used to convert the downloaded zip codes file into a smaller version the program can use.
 * 
 * Download from Site: https://www.census.gov/econ/cbp/download/
 * You want "Complete ZIP Code Totals File"
 * URL: ftp://ftp.census.gov/econ2013/CBP_CSV/zbp13totals.zip
 * 
 * @author Ben Pritzker
 *
 */
public class ApConvertZipCodeDownloadToCityStateZip {

	public static void main(String[] args) {
		System.out.println("\n\nStarting....\n\n");
		ApConvertZipCodeDownloadToCityStateZip convertZipCodeDownloadToCityStateZip = new ApConvertZipCodeDownloadToCityStateZip();
		try {
			convertZipCodeDownloadToCityStateZip.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\n\nDone.\n\n");

	}

	private void run() {
		File zipCodeDownloadedFile = new File("C:/Temp/AddressParser/ZipCodes.txt");
		File convertedFile = new File("C:/Temp/AddressParser/AddressParser_CityStateZipCodes.csv");

		CsvListReader reader = null;
		CsvListWriter writer = null;
		try {
			reader = new CsvListReader(new FileReader(zipCodeDownloadedFile), CsvPreference.STANDARD_PREFERENCE);
			writer = new CsvListWriter(new FileWriter(convertedFile), CsvPreference.STANDARD_PREFERENCE);
			readAndWrite(reader, writer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(reader);
			IOUtils.closeQuietly(writer);
		}
		
	}

	
	
	private void readAndWrite(CsvListReader reader, CsvListWriter writer) throws IOException {
	
//		List<String> readerHeaders = reader.read();
		reader.read(); // Read in the headers
		List<String> writerHeaders = buildWriterHeaders();
		
		// write out the headers
		writer.write(writerHeaders);
		
		List<String> currLine = reader.read();
		int lineCount = 2;
		while (currLine != null) {
			List<String> tempWriterValues = new ArrayList<>();
			String cityName = currLine.get(10);
			String stateAbbr = currLine.get(11);
			String zipCodeString = currLine.get(0);
			
			if (cityName == null || stateAbbr == null || zipCodeString == null) {
				System.out.println("Error converting city/state/zip at line: " + lineCount);
			} else {
				tempWriterValues.add(cityName.toUpperCase()); // city name
				tempWriterValues.add(stateAbbr.toUpperCase()); // state abbrivation
				tempWriterValues.add(zipCodeString); // zip code
				writer.write(tempWriterValues);
			}
			
			currLine = reader.read();
			lineCount++;
			if (lineCount % 10000 == 0) {
				System.out.println("Processing Line: " + lineCount);
			}
		}
		
		
	}

	
	
	
	
	
	
	
	
	private List<String> buildWriterHeaders() {
		List<String> resultWriterHeaders = new ArrayList<>();
		resultWriterHeaders.add("City");
		resultWriterHeaders.add("State");
		resultWriterHeaders.add("Zip");
		return resultWriterHeaders;
	}

}
