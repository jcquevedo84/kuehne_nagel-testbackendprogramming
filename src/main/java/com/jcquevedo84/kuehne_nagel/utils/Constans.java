/**
 * 
 */
package com.jcquevedo84.kuehne_nagel.utils;

/**
 * @author VWARZN1
 *
 */
public final class Constans {
	
	public final static String URL = "https://api.coindesk.com/v1/bpi";
	
	public static final String CURRENTPRICE_ENDPOINT = URL + "/currentprice.json";
	
	public static final String HISTORY_ENDPOINT = URL + "/historical/close.json";
	
	public final static String USD="USD";
	
	public final static String EUR="EUR";
	
	public final static String GBP="GBP"; 
	
	public final static String ERROR_1_CODE="001";
	public final static String ERROR_1_VALUE="No Json data found.";
	
	public final static String ERROR_2_CODE="002";
	
	public static final int DEFAULT_BUFFER_SIZE = 1024;
	
	public static final int DAYSTOSUBSTRACT = 30;
}
