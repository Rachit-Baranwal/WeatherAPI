package com.forecast.api.controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RequestMapping(value = "/gateway")
@RestController
public class ForecastController {

    @Autowired
    Environment env;
    
    @GetMapping(value = "/forecastByName/{cityName}")
    public Response forecastSummary(
    		@RequestHeader String clientId, 
    		@RequestHeader String clientSecret,
    		@PathVariable String cityName) throws IOException {
    	 
        String url=env.getProperty("URL")+cityName+"/summary/";
        
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Key", env.getProperty("KEY"))
                .addHeader("X-RapidAPI-Host", env.getProperty("HOST"))
                .build();
        
        Response response = client.newCall(request).execute();
        System.err.println(response);
        return response;
    }

    @GetMapping(value = "/forecastByNameHourly/{cityName}")
    public Response forecastSummaryHourly(
    		@RequestHeader String clientId, 
    		@RequestHeader String clientSecret, 
    		@PathVariable String cityName) throws IOException {
        
        String url=env.getProperty("URL")+cityName+"/hourly/";
       
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
        		.url(url)
                .get()
                .addHeader("X-RapidAPI-Key", env.getProperty("KEY"))
                .addHeader("X-RapidAPI-Host", env.getProperty("HOST"))
                .build();

        Response response = client.newCall(request).execute();
        System.err.println(response);
        
        return response;
    }
}
