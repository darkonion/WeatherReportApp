package reader;

import model.Report;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserInputReader {

    private final Pattern first = Pattern.compile("[0-9]{2}-[0-9]{3}");
    private final Pattern second = Pattern.compile("[0-9]{5}");

    public void mainLoop() throws IOException {

        try (Scanner reader = new Scanner(System.in)) {
            while (true) {
                String code = getCode(reader);
                String zip = getZip(reader);

                String weatherJson = WeatherStringUploaderApache.getWeatherForecast(zip, code);
                if (weatherJson == null) {
                    System.out.println("Connection Error or non existing zip code, please try again");
                    continue;
                }

                Report report = JsonToPOJOConverter.convert(weatherJson);

                System.out.println(report);

                System.out.println("If you want to quit, please type QUIT");
                if (reader.nextLine().equalsIgnoreCase("QUIT")) {
                    System.out.println("Goodbye");
                    break;
                }
            }
        }
    }

    private String getZip(Scanner reader) {
        while (true) {
            System.out.println("Insert zip code: ");
            String zip = reader.nextLine().trim();
            if (!zip.isEmpty() && checkZip(zip)) {
                return zip;
            }
            System.out.println("Wrong zip code, please try again!");
        }
    }

    private String getCode(Scanner reader) {
        while (true) {
            System.out.println("Insert country code (E.g pl, us, uk, es, ua, fr): ");
            String code = reader.nextLine().toLowerCase().trim();
            if (code.length() == 2 && checkCode(code)) {
                return code;
            }
            System.out.println("Wrong country code, please try again!");
        }
    }

    private boolean checkCode(String in) {
        return Arrays.stream(Locale.getISOCountries())
                .anyMatch(s -> s.equalsIgnoreCase(in));
    }

    private boolean checkZip(String in) {
        return first.matcher(in).matches() || second.matcher(in).matches();
    }
}
