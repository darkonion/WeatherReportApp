package reader;

import model.Report;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JsonToPOJOConverter {

    private static final String CLEANING_PATTERN = "[\\[\\]{}^\"|\"$]";

    public static Report convert(String weatherString) {

        Report report = new Report();

        String afterCleanup = weatherString.replaceAll(CLEANING_PATTERN, "");

        String[] initialSplit = afterCleanup.split(",");

        List<String[]> lines = Arrays.stream(initialSplit)
                .map(l -> l.split(":"))
                .collect(Collectors.toList());


        //yeah, I know that Jackson and Gson exists... :)
        for (String[] params : lines) {
            if (params[0].equals("main") && params[1].equals("temp")) {
                report.setTemperature(params[2]);
            } else {
                switch (params[0]) {
                    case "main":
                        report.setMainInfo(params[1]);
                        break;
                    case "description":
                        report.setDescription(params[1]);
                        break;
                    case "feels_like":
                        report.setTemperatureFeel(params[1]);
                        break;
                    case "temp_min":
                        report.setTemp_min(params[1]);
                        break;
                    case "temp_max":
                        report.setTemp_max(params[1]);
                        break;
                    case "pressure":
                        report.setPressure(params[1]);
                        break;
                    case "wind":
                        report.setWindSpeed(params[2]);
                        break;
                    case "name":
                        report.setCity(params[1]);
                        break;
                }
            }
        }
        return report;
    }
}
