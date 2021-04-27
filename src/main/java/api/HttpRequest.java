package api;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class HttpRequest {
	
	public String getResponse(String source) {
		return getBody(source);
	}

	private String getBody(String source) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(source)
				.build();
		return getResponseBody(client, request);
	}

	private String getResponseBody(OkHttpClient client, Request request) {
		try {
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			return "";
		}
	}
}
