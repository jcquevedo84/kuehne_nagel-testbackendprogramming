package com.jcquevedo84.kuehne_nagel.testbackendprogramming;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

import com.jcquevedo84.kuehne_nagel.exception.SeviceWrapperException;
import com.jcquevedo84.kuehne_nagel.model.InfoBitcoin;
import com.jcquevedo84.kuehne_nagel.proxy.Proxy;
import com.jcquevedo84.kuehne_nagel.utils.Constans;
import com.jcquevedo84.kuehne_nagel.utils.Validations;

public class App {

	public static void main(String[] args) {

		int mainOption;
		String code;

		// show menu and choose an option
		mainOption = handleMainOption();

		if (mainOption == 1) {
			
			code=getCurrencyCode();
			
			LocalDate date = LocalDate.now();
			
			Proxy proxy = new  Proxy(Integer.valueOf(Constans.DEFAULT_BUFFER_SIZE));

			try {
				
				InfoBitcoin rateBitcoinByCode = proxy.getRateBitcoinByCode(code);
				
				 System.out.println("Today " + rateBitcoinByCode.getCode() + " bitcoin rate = " + rateBitcoinByCode.getRate());
				 
				 Map<String, String> custumerBitoins = proxy.getCustumerBitoins(date.minusDays(Constans.DAYSTOSUBSTRACT), date,code);
				 
				 System.out.println("Lowest rate in the last 30 days = " + custumerBitoins.get("lowRate")); 
                 System.out.println("Highest rate in the last 30 days = " +custumerBitoins.get("highRate"));
                 
                 System.out.println("Bye!!");
				 
			} catch (SeviceWrapperException e) {
				
				System.out.println("Code: "+e.getCode()+" Massages: "+e.getMessage());
			} catch (Exception e) {
				
				System.out.println("IO Exception occurs. Please check the input.\nError: "+e.toString());
			}
		}
	}

	static void getMenu() {

		System.out.println("Menu\n");
		System.out.println("1- Get info Bitcoint");
		System.out.println("2- Quit");
		System.out.println("Select an option: \n");
	}

	static int handleMainOption() {

		Scanner scannerInput = new Scanner(System.in);
		String inputOption;
		boolean flag = Boolean.FALSE;

		do {

			getMenu();
			inputOption = scannerInput.nextLine();

			if (!Validations.isNumeric(inputOption)) {

				System.out.println("Please input option numeric\n");
				flag = Boolean.TRUE;
			}
			if (!Validations.isRange(Integer.valueOf(inputOption))) {
				System.out.println("Please input 1 or 2\\n");
				flag = Boolean.TRUE;
			}

			if ("2".equalsIgnoreCase(inputOption)) {
				System.out.println("Good bye!!\\n");
			}

		} while (flag);

		return Integer.valueOf(inputOption);

	}

	static String getCurrencyCode() {

		Scanner scannerInput = new Scanner(System.in);
		String inputOption;
		boolean flag = Boolean.FALSE;

		System.out.println("Inpur currency Code: \n");

		do {
			if (flag) {

				System.out.println("Please input code valid!!\n");
				System.out.println("Inpur currency Code: \n");
			}

			inputOption = scannerInput.nextLine();
			
			if (Validations.isCodeValid(inputOption)) {
				
				flag = Boolean.FALSE;
			}
			else {
				
				flag = Boolean.TRUE;
			}

		} while (flag);
		
		return inputOption;
	}
}
