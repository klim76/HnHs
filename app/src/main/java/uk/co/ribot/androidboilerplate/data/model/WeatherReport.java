package uk.co.ribot.androidboilerplate.data.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by klim on 10.07.2017.
 */

public class WeatherReport {
    SimpleDateFormat dateFormat = new SimpleDateFormat("d-е MMMM");

    Coord coord;
    List<Weather> weather;
    String base;
    Main main;
    Wind wind;
    Rain rain;
    Clouds clouds;
    Long dt;
    Sys sys;
    Long id;
    String name;
    Integer code;

    public String getWeatherReport(){
        return ("По состоянию на " + dateFormat.format(new Date(dt*1000)) + " в городе " + name + " " + weather.get(0).description + " температура воздуха " + main.temp);
    }
}
