package com.jcquevedo84.kuehne_nagel.testbackendprogramming;

import java.time.LocalDate;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.jcquevedo84.kuehne_nagel.exception.SeviceWrapperException;
import com.jcquevedo84.kuehne_nagel.model.InfoBitcoin;
import com.jcquevedo84.kuehne_nagel.proxy.Proxy;
import com.jcquevedo84.kuehne_nagel.utils.Constans;


public class AppTest {

	String code ="USD";
	
	@Test
	public  void testGetRateBitcoinByCode() {

		Proxy proxy = new  Proxy(Integer.valueOf(Constans.DEFAULT_BUFFER_SIZE));
		
		try {
			InfoBitcoin rateBitcoinByCode = proxy.getRateBitcoinByCode(code);
			Assert.assertNotNull(rateBitcoinByCode.getRate());
		} catch (SeviceWrapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@Test
	public  void testGetCustumerBitoins() {

		LocalDate date = LocalDate.now();
		
		Proxy proxy = new  Proxy(Integer.valueOf(Constans.DEFAULT_BUFFER_SIZE));
		
		try {
			
			Map<String, String> custumerBitoins = proxy.getCustumerBitoins(date.minusDays(Constans.DAYSTOSUBSTRACT), date,code);
	        
	        Assert.assertNotNull(custumerBitoins);
	        Assert.assertNotNull(custumerBitoins.get("lowRate"));
	        Assert.assertNotNull(custumerBitoins.get("highRate"));

		} catch (SeviceWrapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
