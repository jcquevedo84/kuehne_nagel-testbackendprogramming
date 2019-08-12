/**
 * 
 */
package com.jcquevedo84.kuehne_nagel.proxy;

import java.net.URL;
import java.time.LocalDate;
import java.util.Map;

import com.jcquevedo84.kuehne_nagel.exception.SeviceWrapperException;
import com.jcquevedo84.kuehne_nagel.model.InfoBitcoin;
import com.jcquevedo84.kuehne_nagel.utils.Constans;

/**
 * @author VWARZN1
 *
 */
public class Proxy {

	private SeviceWrapperImp seviceWrapperImp;

	
	public Proxy() {
		
	}

	public Proxy(Integer bufferSize) {
		
		seviceWrapperImp= new SeviceWrapperImp(bufferSize);
	}

	public InfoBitcoin getRateBitcoinByCode(String currencyCode) throws SeviceWrapperException, Exception {
		
		
		return seviceWrapperImp.getRateBitcoinByCode(currencyCode, new URL(Constans.CURRENTPRICE_ENDPOINT.replace(".json", String.format("/%s.json", currencyCode))));
	}
	
	public Map<String,String> getCustumerBitoins(LocalDate start, LocalDate end, String currency) throws SeviceWrapperException, Exception {
		
		return seviceWrapperImp.getCustumerBitoins(new URL(String.format("%s?start=%s&end=%s&currency=%s", Constans.HISTORY_ENDPOINT, start, end, currency)));
	}

}
