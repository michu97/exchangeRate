package api;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class NbpDataProvider implements ExchangeDataProvider {

	@Override
	public String getExchangeRate(String source) {
		return getResponse(source);
	}

	private String getResponse(String source) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
							.url(source)
							.build();
		return getBody(client, request);
	}

	private String getBody(OkHttpClient client, Request request) {
		try {
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			return "";
		}
	}
}
