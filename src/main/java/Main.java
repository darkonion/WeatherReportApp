import model.Report;
import reader.JsonToPOJOConverter;
import reader.WeatherStringUploader;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner reader = new Scanner(System.in)) {
            mainProgramLoop(reader);
        }
    }

    private static void mainProgramLoop(Scanner reader) {
        while (true) {
            System.out.println("Insert country code (E.g pl, us, uk, es, ua, fr): ");
            String code = reader.nextLine().toLowerCase().trim();
            if (code.length() != 2 || !checkCode(code)) {
                System.out.println("Wrong country code, please try again!");
                continue;
            }
            System.out.println("Insert zip code: ");
            String zip = reader.nextLine().trim();
            if (zip.isEmpty() || !checkZip(zip)) {
                System.out.println("Wrong zip code, please try again!");
                continue;
            }
            String weatherJson = WeatherStringUploader.getWeatherForecast(zip, code);
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

    private static boolean checkCode(String in) {
        boolean exists = false;
        String[] locales = Locale.getISOCountries();
        for (String s : locales) {
            if (s.toLowerCase().equals(in)) {
                exists = true;
            }
        }
        return exists;
    }

    private static boolean checkZip(String in) {
        if(in.matches("[0-9]{2}-[0-9]{3}") || in.matches("[0-9]{5}")) {
            return true;
        }
        return false;
    }
}
