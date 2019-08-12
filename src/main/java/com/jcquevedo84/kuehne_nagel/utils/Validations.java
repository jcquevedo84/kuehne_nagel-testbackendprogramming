package com.jcquevedo84.kuehne_nagel.utils;

public final class Validations {

	public static boolean isNumeric(String text) {

		boolean resul;

		try {
			Integer.parseInt(text);
			resul = true;
		} catch (NumberFormatException excepcion) {
			resul = false;
		}

		return resul;
	}

	public static boolean isRange(int option) {

		if (option >= 1 && option <= 2) {
			return true;
		} else {
			return false;
		}

	}
	
	public static boolean isCodeValid(String code) {

		//here can i get all code from a service and do the validation
		if (Constans.USD.equalsIgnoreCase(code)
				|| Constans.EUR.equalsIgnoreCase(code)
				||Constans.GBP.equalsIgnoreCase(code)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

}
