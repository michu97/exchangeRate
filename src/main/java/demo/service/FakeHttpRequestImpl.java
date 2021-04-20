package demo.service;

import org.json.JSONObject;

public class FakeHttpRequestImpl implements HttpRequestService {
	private boolean isInternetConnection;
	private static final String content = "{\"table\":\"A\",\"currency\":\"dolar amerykañski\",\"code\":\"USD\",\"rates\":[{\"no\":\"074/A/NBP/2021\",\"effectiveDate\":\"2021-04-19\",\"mid\":3.7816}]}";
	
	
	public FakeHttpRequestImpl(boolean isInternetConnection) {
		this.isInternetConnection = isInternetConnection;
	}

	@Override
	public JSONObject getResponse(String url) {
		if (!isInternetConnection)
			return new JSONObject();
		return new JSONObject(content);
	}


	public void setInternetConnection(boolean isInternetConnection) {
		this.isInternetConnection = isInternetConnection;
	}

}
