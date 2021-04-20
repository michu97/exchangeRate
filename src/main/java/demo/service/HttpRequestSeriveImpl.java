package demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

public class HttpRequestSeriveImpl implements HttpRequestService {
	private static final Logger LOGGER = Logger.getLogger(HttpRequestSeriveImpl.class.getName());
	
	@Override
	public JSONObject getResponse(String url) {
		String respone = reciveResponse(url);
		if (respone == null) {
			return new JSONObject();
		}
		return new JSONObject(respone);
	}

	private String reciveResponse(String url) {
		HttpURLConnection connection = checkoutConnection(url);
		if (connection == null) {
			return null;
		}
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputline;
			StringBuffer content = new StringBuffer();
			while ((inputline = in.readLine()) != null) {
				content.append(inputline);
			}
			return content.toString();
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Cannot take body from response: ", e);
			return null;
		} finally {
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
		}
	}
	
	private HttpURLConnection checkoutConnection(String url) {
		try {
			HttpURLConnection connection = getConnection(url);
			int responseCode = connection.getResponseCode();
			if (responseCode == 404)
				throw new ContentNotFoundException("Response code: " + 
						responseCode + "Data not found");
			return connection;
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "No internet connection: ", e);
			return null;
		}
		
	}
	
	private HttpURLConnection getConnection(String url) throws IOException {
		URL urlConnection = getURL(url);
		HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/json");
		return connection;
	}
	
	private URL getURL(String url) {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			LOGGER.log(Level.WARNING, "Cannot parse url: ", e);
			return null;
		}
	}
}
