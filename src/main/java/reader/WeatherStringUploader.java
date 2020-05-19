package reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherStringUploader {


    public static String getWeatherForecast(String zip, String code) {

        String fullURL = String.format("http://api.openweathermap.org/data/2.5/" +
                "weather?zip=%s,%s&appid=0dbf67ad9d722d3f5ababc2bbbc90814&units=metric", zip, code);

        try {
            URL url = new URL(fullURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                return null;
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String output;
                while ((output = reader.readLine()) != null) {
                    return output;
                }
            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
