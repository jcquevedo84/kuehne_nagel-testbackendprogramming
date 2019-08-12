package com.jcquevedo84.kuehne_nagel.proxy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import com.jcquevedo84.kuehne_nagel.exception.SeviceWrapperException;
import com.jcquevedo84.kuehne_nagel.model.InfoBitcoin;
import com.jcquevedo84.kuehne_nagel.utils.Constans;
import com.jcquevedo84.kuehne_nagel.utils.Validations;

public class SeviceWrapperImp implements SeviceWrapper {

	private static final String NEWLINE = System.getProperty("line.separator");
	private HttpURLConnection httpUrlConnection;
	private BufferedReader bufferedReader;
	private final StringBuilder stringBuilder;

	protected SeviceWrapperImp(int initialCapacity) {
		stringBuilder = new StringBuilder(initialCapacity);
	}

	@Override
	public InfoBitcoin getRateBitcoinByCode(String currencyCode, URL url) throws SeviceWrapperException, Exception {

		String line, jsonResult;

		try {

			httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setRequestMethod("GET");
			httpUrlConnection.setRequestProperty("accept", "application/json");
			httpUrlConnection.setDoOutput(true);

			bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));

			while ((line = bufferedReader.readLine()) != null) {

				stringBuilder.append(line).append(NEWLINE);
			}

			jsonResult = stringBuilder.toString();

			if (!Validations.isEmpty(jsonResult)) {

				JSONObject mainJsonObject = new JSONObject(jsonResult);
				JSONObject bpiObject = mainJsonObject.getJSONObject("bpi");
				JSONObject gbp = bpiObject.getJSONObject(currencyCode.toUpperCase());

				InfoBitcoin resul = new InfoBitcoin(gbp.getString("rate"), gbp.getString("code"));
				return resul;

			} else {

				throw new SeviceWrapperException(Constans.ERROR_1_CODE, Constans.ERROR_1_VALUE);
			}
		} catch (Exception ex) {

			throw ex;
		}
	}

	@Override
	public Map<String, String> getCustumerBitoins(URL url) throws SeviceWrapperException, Exception {

		Map<String, String> result = new HashMap<String, String>();

		String line, jsonResult;
		float currentRate=0, lowRate = 0, highRate = 0;
		int iCount = 0;

		try {

			httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setRequestMethod("GET");
			httpUrlConnection.setRequestProperty("accept", "application/json");
			httpUrlConnection.setDoOutput(true);

			bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));

			while ((line = bufferedReader.readLine()) != null) {

				stringBuilder.append(line).append(NEWLINE);
			}

			jsonResult = stringBuilder.toString();

			if (!Validations.isEmpty(jsonResult)) {

				JSONObject mainJsonObject = new JSONObject(jsonResult);
				JSONObject bpiObject = mainJsonObject.getJSONObject("bpi");
				Iterator<String> iterator = bpiObject.keys();
				JSONObject gbp = null;

				while (iterator.hasNext()) {

					String key = iterator.next();
					try {
						 gbp = bpiObject.getJSONObject(key);
						 currentRate = Float.parseFloat(gbp.getString("rate").replace(",", ""));
					} catch (Exception e) {
						// TODO: handle exception
					}

					if (iCount == 0)
						lowRate = currentRate;
					if (currentRate > highRate)
						highRate = currentRate;
					if (currentRate < lowRate)
						lowRate = currentRate;
					iCount += 1;
				}

				result.put("lowRate", String.valueOf(lowRate));
				result.put("highRate", String.valueOf(highRate));

				return result;

			} else {

				throw new SeviceWrapperException(Constans.ERROR_1_CODE, Constans.ERROR_1_VALUE);
			}
		} catch (Exception ex) {

			throw ex;
		}
	}

}
