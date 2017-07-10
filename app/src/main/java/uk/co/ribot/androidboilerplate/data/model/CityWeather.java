package uk.co.ribot.androidboilerplate.data.model;

/**
 * Created by klim on 10.07.2017.
 */

public class CityWeather {
    public Coord coord;
    public Sys sys;
    public java.util.List<Weather> weather = null;
    public Main main;
    public Integer visibility;
    public Wind wind;
    public Clouds clouds;
    public Integer dt;
    public Integer id;
    public String name;
}
