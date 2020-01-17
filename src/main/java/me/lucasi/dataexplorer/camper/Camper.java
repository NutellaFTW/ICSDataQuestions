package me.lucasi.dataexplorer.camper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Camper {

    private String dateOfBirth, city, province, pcode, race, country, history;

    public Camper(String dateOfBirth, String city, String province, String pcode, String country, String race, String history) {
        this.dateOfBirth = dateOfBirth;
        this.city = city;
        this.province = province;
        this.pcode = pcode;
        this.country = country;
        this.race = race;
        this.history = history;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPcode() {
        return pcode;
    }

    public String getRace() {
        return race;
    }

    public String getCountry() {
        return country;
    }

    public String getHistory() {
        return history;
    }

    public List<Integer> getYearsInHistory() {
        String[] splitHistory = history.split(" ");
        List<Integer> years = new ArrayList<>();
        for (String history : splitHistory)
            if (isInteger(history) && history.length() == 4)
                years.add(Integer.parseInt(history));
        return years;
    }

    public String getDropOffAgeGroup() {
        String[] splitHistory = history.split(" ");
        return splitHistory[1].replaceAll("[0-9]","");
    }

    private boolean isInteger(String integer) {
        try {
            Integer.parseInt(integer);
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

}
