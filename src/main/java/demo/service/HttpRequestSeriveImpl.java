package demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestSeriveImpl implements HttpRequestService {
	
	@Override
	public String getResponse(String url) {
		try {
			return reciveResponse(url);
		} catch (IOException e) {
			return "";
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
			return connection;
	}
	
	private HttpURLConnection getConnection(String url) throws IOException {
		URL urlConnection = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
		connection.setRequestMethod("GET");
		return connection;
	}
}
