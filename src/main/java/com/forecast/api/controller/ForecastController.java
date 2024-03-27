package com.forecast.api.controller;
import com.forecast.api.forecastcallsrvice.ForeCastService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RequestMapping(value = "/gateway")
@RestController
public class ForecastController {
    public static final Logger log = LogManager.getLogger(ForecastController.class);

    @Autowired
    ForeCastService foreCastService;
    @GetMapping(value = "/forecastByName/{cityName}")
    public Object forecastSummary(@RequestHeader String Authorization, @PathVariable String cityName) {
        try{
            if(foreCastService.validateAuth(Authorization)) {
                return foreCastService.getForeCastByName(cityName);
            } else {
                return new JSONObject().put("message", "Invalid Authorization").toMap();
            }
        } catch (NullPointerException e) {
            log.error("Null Pointer Exception while calling Api   =======>: {} {}",e.getMessage(), e);
        }
        catch (Exception e) {
            log.error("Exception while calling Api  =======>: {} ",e.getMessage());
        }
        return null;
    }

    @GetMapping(value = "/forecastByNameHourly/{cityName}")
    public Object forecastSummaryHourly(@RequestHeader String Authorization, @PathVariable String cityName) throws IOException {
        try{
            if(foreCastService.validateAuth(Authorization)) {
                return foreCastService.getForeCastByNameHourly(cityName);
            } else {
                return new JSONObject().put("message", "Invalid Authorization").toMap();
            }        } catch (NullPointerException e) {
            log.error("Null Pointer Exception while calling Api   =======>: {} {}",e.getMessage(), e);
        }
        catch (Exception e) {
            log.error("Exception while calling Api  =======>: {} ",e.getMessage());
        }
        return null;
    }
}
