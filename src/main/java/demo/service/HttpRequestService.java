package demo.service;

import org.json.JSONObject;

public interface HttpRequestService {
	JSONObject getResponse(String url);
}
