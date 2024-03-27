package com.forecast.api.forecastcallsrvice.impl;

import com.forecast.api.forecastcallsrvice.ForeCastService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Service
public class ForeCastServiceImpl implements ForeCastService {

	@Autowired
	Environment env;

	@Override
	public Object getForeCastByName(String cityName) {
		HttpResponse<String> response = null;
		try {
			String url=env.getProperty("URL") + cityName + "/summary/";
			
			HttpRequest request = 
				HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("X-RapidAPI-Host", env.getProperty("HOST"))
				.header("X-RapidAPI-Key", env.getProperty("KEY"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new JSONObject(response.body()).toMap();
	}

	@Override
	public Object getForeCastByNameHourly(String cityName) {
		HttpResponse<String> response = null;
		try {
			String url=env.getProperty("URL") + cityName + "/hourly/";
		
			HttpRequest request =
				HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("X-RapidAPI-Host", env.getProperty("HOST"))
				.header("X-RapidAPI-Key", env.getProperty("KEY"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();

		
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new JSONObject(response.body()).toMap();
	}

	public boolean validateAuth(String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
			String base64Credentials = authorizationHeader.substring(6);
			String credentials = new String(Base64.getDecoder().decode(base64Credentials));

			String[] parts = credentials.split(":");
			if (parts.length == 2) {
				String clientId = parts[0];
				String clientSecret = parts[1];

				if (clientId.equals(env.getProperty("clientId"))
						&& clientSecret.equals(env.getProperty("clientSecret"))) {
					return true;
				} else {
					// Authentication failed
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
