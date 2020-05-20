package reader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WeatherStringUploaderApache {


    public static String getWeatherForecast(String zip, String code) throws IOException {

        String fullURL = String.format("http://api.openweathermap.org/data/2.5/" +
                "weather?zip=%s,%s&appid=0dbf67ad9d722d3f5ababc2bbbc90814&units=metric", zip, code);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(fullURL);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200) {
                return null;
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()))
            ) {
                String output;
                while ((output = reader.readLine()) != null) {
                    return output;
                }
            }
        }
        return null;
    }
}
