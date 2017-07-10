package uk.co.ribot.androidboilerplate.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import uk.co.ribot.androidboilerplate.data.model.WeatherReport;
import uk.co.ribot.androidboilerplate.util.MyGsonTypeAdapterFactory;

/**
 * Created by klim on 10.07.2017.
 * класс-интерфейс для АПИ openweathermap.org
 */

public interface WeatherService {

    String MY_APPLICATION_KEY = "b4f19f89f50999823dbee7bf3727d44a";
    String METRIC_SYSTEM = "metric";
    long SARANSK_ID =  498698;

    String ENDPOINT = "http://api.openweathermap.org";
    @GET("/data/2.5/weather")
    Observable<WeatherReport> getWeather(@Query("id") Long id, @Query("units") String units, @Query("appid") String appid);

    class Creator {

        public static WeatherService newWeatherService() {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
                    //.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(WeatherService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(WeatherService.class);
        }
    }
}
