package demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpRequestSeriveImpl implements HttpRequestService {
	
	@Override
	public JSONObject getResponse(String url) {
		String respone;
		try {
			respone = reciveResponse(url);
			return new JSONObject(respone);
		} catch (IOException | JSONException e) {
			return new JSONObject();
		}
	}

	private String reciveResponse(String url) throws IOException {
		HttpURLConnection connection = checkoutConnection(url);
		BufferedReader in = null;
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputline;
			StringBuffer content = new StringBuffer();
			while ((inputline = in.readLine()) != null) {
				content.append(inputline);
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
					connection.disconnect();
			}
			return content.toString();
		}
	
	
	private HttpURLConnection checkoutConnection(String url) throws IOException {
			HttpURLConnection connection = getConnection(url);
			int responseCode = connection.getResponseCode();
			if (responseCode == 404)
				throw new ContentNotFoundException("Response code: " + 
						responseCode + " Data not found");
			return connection;
	}
	
	private HttpURLConnection getConnection(String url) throws IOException {
		URL urlConnection = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/json");
		return connection;
	}
}
