package org.benp.addressparser;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.Pair;


/**
 * This is an exception class with an easy way to add name value pairs. 
 * When you throw this exception it makes it easy to add all the parameters
 * 
 * @author Ben P
 *
 */
public class ApException extends Exception {

	private static final long serialVersionUID = 1L;
	
    List<Pair<String, String>> nameValuePairs = new ArrayList<Pair<String, String>>();
    private String additionalMessage;

    
	
    public ApException(String inAdditionalMessage, Object... additionalPairs)
    {
		super(inAdditionalMessage,null);
		additionalMessage = inAdditionalMessage;
		nameValuePairs = arrayToPairs(additionalPairs);
    }

	public ApException(String inAdditionalMessage, Exception e)
    {
        super(inAdditionalMessage, e);
        additionalMessage = inAdditionalMessage;
    }
	
	public ApException(String inAdditionalMessage, Exception e, String... additionalPairs)
    {
        super(inAdditionalMessage, e);
        additionalMessage = inAdditionalMessage;
        nameValuePairs = arrayToPairs(additionalPairs);
    }
	
    protected List<Pair<String, String>> arrayToPairs(Object[] parameters) {
		List<Pair<String,String>> resultNameValuePairs = new ArrayList<>();
		int paramCounter = 0;
		String tempName = null;
		String tempValue = null;
		
    	if (parameters != null) {
    		for (Object currParam : parameters) {
	    		if (paramCounter % 2 == 0) {
	    			if (paramCounter != 0) {
	    				Pair<String, String> tempPair = Pair.of(tempName, tempValue);
	    				resultNameValuePairs.add(tempPair);
	    			}
	    			tempName = currParam.toString();
	    			tempValue = null;
	    		} else {
	    			tempValue = currParam.toString();
	    		}
    		paramCounter++;	
    		}
    	}
    	
    	// if the tempName is null then there were no parameters passed
    	if (tempName != null && tempValue == null) {
			Pair<String, String> tempPair = Pair.of(tempName, "No value was given");
			resultNameValuePairs.add(tempPair);
    	} else if (tempName != null){
			Pair<String, String> tempPair = Pair.of(tempName, tempValue);
			resultNameValuePairs.add(tempPair);
    	}

    	return resultNameValuePairs;
	}		
	
    /**
     * Use this to print ALL information about the Stacktrace to the screen.
     * It will print the additional Message, all the pairs and the actual stack trace to stderr
     */
    public void printFullApExceptionToStdErr() {
    	System.err.println(getFullExceptionForDisply());
    }
    
    
    
    public String getStringOfMessageAndParams() {
    	StringBuilder resultSb = new StringBuilder();
    	resultSb.append(
    			System.getProperty("line.separator") 
    			+ "  *** Message ***: " 
    			+ additionalMessage 
    			+ System.getProperty("line.separator") 
    			+ System.getProperty("line.separator"));
		if (nameValuePairs != null) { // Always super extra cautious for the error handling
	    	int paramCount = 1;
	    	int maxNameStringLengh = getMaxNameStringLength();
	    	for (Pair<String, String> currentPair : nameValuePairs)
	    	{
	    		resultSb.append("  Param (" + paramCount + ") Name: \"" + currentPair.getKey() + "\" ");
	    		// We NEVER want this to crash, so check everything!
	    		int currKeyLength = 0;
	    		if (currentPair.getKey() != null) {
	    			currKeyLength = currentPair.getKey().length();
	    		}
	    		for (int i=0; i < maxNameStringLengh - currKeyLength; i++) {
		    		resultSb.append(" ");
	    		}
	    		resultSb.append("-- Value: \"" + currentPair.getValue() + "\"" + System.getProperty("line.separator"));
	    		paramCount++;
	    	}
		}
    	return resultSb.toString();
    }
    
    
    private int getMaxNameStringLength() {
    	int resultMaxLength = 1;
    	for (Pair<String, String> currentPair : nameValuePairs) {
    		int currKeyLength = 0;
    		if (currentPair.getKey() != null) {
    			currKeyLength = currentPair.getKey().length();
    		}
    		if (currKeyLength > resultMaxLength) {
    			resultMaxLength = currentPair.getKey().length();
    		}
    	}
    	return resultMaxLength;
	}

	public String getFullExceptionForDisply() {
    	StringBuilder resultSb = new StringBuilder();
    	resultSb.append(System.getProperty("line.separator") 
    			+ System.getProperty("line.separator")
    			+ ">>>>>>>>>>>>>>>>>>>>>>>>> BEGIN " + getHeaderMessageForDisplay() + "! >>>>>>>>>>>>>>>>>>>>>>>>>"
    			+ System.getProperty("line.separator"));
    	resultSb.append(getStringOfMessageAndParams());
    	
		resultSb.append(System.getProperty("line.separator"));
		
		resultSb.append(getSubclassMessage());
		
		resultSb.append(System.getProperty("line.separator") 
				+ System.getProperty("line.separator")); // extra line before exception
		resultSb.append(ExceptionUtils.getStackTrace(this));
		resultSb.append(System.getProperty("line.separator") 
				+ "<<<<<<<<<<<<<<<<<<<<<<<<<< END " + getHeaderMessageForDisplay() + "! <<<<<<<<<<<<<<<<<<<<<<<<<<"
				+ System.getProperty("line.separator"));
		return resultSb.toString();
    }

	
	/**
	 * Sub classes, you can overwrite this to get your message to look good in the getFullException of display
	 */
	protected Object getSubclassMessage() {
		return "";
	}

	public String getAdditionalMessage() {
		return additionalMessage;
	}
    
	
	public String getStacktraceAsString() {
		return ExceptionUtils.getStackTrace(this);
	}
	
	/**
	 * If you extend this exception then override this to help indicate it's a different type of exception 
	 */
	protected String getHeaderMessageForDisplay() {
		return "AP *GENERIC* EXCEPTION";
	}
	

}
