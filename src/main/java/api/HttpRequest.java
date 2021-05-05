package api;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpRequest {
	
	public String getResponse(String source) {
		return getBody(source);
	}

	private String getBody(String source) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(source)
				.build();
		return getBody(client, request);
	}

	private String getBody(OkHttpClient client, Request request) {
		Response response = null;
		try {
			response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			return "";
		} finally {
			if (response != null) {
				response.body().close();
			}
		}
	}
}
