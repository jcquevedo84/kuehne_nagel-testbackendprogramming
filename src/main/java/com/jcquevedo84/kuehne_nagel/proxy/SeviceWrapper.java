package com.jcquevedo84.kuehne_nagel.proxy;

import java.net.URL;
import java.util.Map;

import com.jcquevedo84.kuehne_nagel.exception.SeviceWrapperException;
import com.jcquevedo84.kuehne_nagel.model.InfoBitcoin;

public interface SeviceWrapper {
	
	public InfoBitcoin getRateBitcoinByCode(String currencyCode, URL url) throws SeviceWrapperException,Exception;
	
	public Map<String,String> getCustumerBitoins(URL url)throws SeviceWrapperException, Exception;

}
