package model;

public class Report {

    private String city;
    private String mainInfo;
    private String description;
    private String temperature;
    private String temperatureFeel;
    private String temp_min;
    private String temp_max;
    private String pressure;
    private String windSpeed;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMainInfo() {
        return mainInfo;
    }

    public void setMainInfo(String mainInfo) {
        this.mainInfo = mainInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperatureFeel() {
        return temperatureFeel;
    }

    public void setTemperatureFeel(String temperatureFeel) {
        this.temperatureFeel = temperatureFeel;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String processDescription(String description) {
        if (description == null) {
            return "<no data>";
        }
        return description.substring(0, 1).toUpperCase() + description.substring(1);
    }

    @Override
    public String toString() {
        return "\n" + "Weather Report: " + city + "\n" +
                "\tGeneral Weather: " + mainInfo + "\n" +
                "\tDescription: " + processDescription(this.description) + "\n" +
                "\tTemperature: " + temperature + " °C" + "\n" +
                "\tTemperature Feel: " + temperatureFeel + " °C" + "\n" +
                "\tMin Temperature: " + temp_min + " °C"+ "\n" +
                "\tMax Temperature: " + temp_max + " °C" + "\n" +
                "\tPressure: " + pressure + " hPa" + "\n" +
                "\tWind Speed: " + windSpeed + " km/h" +  "\n\n";
    }
}
