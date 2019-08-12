package com.jcquevedo84.kuehne_nagel.model;

public class InfoBitcoin {
	
	private String rate;
	private String code;

	public InfoBitcoin() {

	}

	public InfoBitcoin(String rate, String code) {
		this.rate = rate;
		this.code = code;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
