package demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

public class HttpRequestSeriveImpl implements HttpRequestService {

	@Override
	public JSONObject getResponse(String url) {
		return new JSONObject(reciveResponse(url));
	}

	private String reciveResponse(String url) {
		HttpURLConnection connection = checkoutConnection(url);
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
			e.printStackTrace();
			return null;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			connection.disconnect();
		}
	}
	
	private HttpURLConnection checkoutConnection(String url) {
		try {
			HttpURLConnection connection = getConnection(url);
			int responseCode = connection.getResponseCode();
			if (responseCode == 404)
				throw new ContentNotFoundException("Data not found");
			return connection;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Cannot connecting to external api", e);
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
			e.printStackTrace();
			return null;
		}
	}
}
