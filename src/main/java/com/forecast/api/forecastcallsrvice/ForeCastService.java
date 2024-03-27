package com.forecast.api.forecastcallsrvice;

public interface ForeCastService {
    Object getForeCastByName(String cityName);
    Object getForeCastByNameHourly(String cityName);
    boolean validateAuth(String auth);


}
