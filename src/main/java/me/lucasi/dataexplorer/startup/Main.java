package me.lucasi.dataexplorer.startup;

import me.lucasi.dataexplorer.camper.AgeTypes;
import me.lucasi.dataexplorer.camper.Camper;
import me.lucasi.dataexplorer.camper.CamperManager;

import java.util.*;

public class Main {

    private static Main instance;
    private CamperManager camperManager;

    private Map<Integer, Integer> amountEachYear;

    public Main() {
        instance = this;
        camperManager = new CamperManager();
        amountEachYear = new HashMap<>();
        printOneTimers();
        printCountryReport();
        printPostalCodes();
        printRaces();
        dropOffAges();
    }

    // Percentage in each year of people that came that year and never came back
    // Main function for Q1

    private void printOneTimers() {

        Map<Integer, List<Camper>> oneTimersYears = new HashMap<>();

        for (int year = 1989; year <= 2019; year++) {
            oneTimersYears.put(year, new ArrayList<>());
            amountEachYear.put(year, camperManager.getCamperAmountInYear(year));
        }

        for (Camper camper : camperManager.getCampers()) {
            List<Integer> years = removeDuplicates(camper.getYearsInHistory());
            if (years.size() == 1) {
                oneTimersYears.get(years.get(0)).add(camper);
            }
        }

        System.out.println("Q1_Percentages:");

        for (Map.Entry<Integer, List<Camper>> entry : oneTimersYears.entrySet()) {
            int percentage = (int) Math.round(((double) entry.getValue().size() / (double) amountEachYear.get(entry.getKey())) * 100);
            System.out.println(entry.getKey() + ": " + percentage + "%");
        }

    }

    // Main function for Q2

    private void printCountryReport() {

        Map<Integer, Integer> countNotFromOntario = new HashMap<>();

        for (Camper camper : camperManager.getCampers()) {
            for (int year : removeDuplicates(camper.getYearsInHistory())) {
                if (!camper.getProvince().equalsIgnoreCase("Ontario")) {
                    if (countNotFromOntario.containsKey(year))
                        countNotFromOntario.put(year, countNotFromOntario.get(year) + 1);
                    else
                        countNotFromOntario.put(year, 1);
                }
            }
        }

        System.out.println("\nQ2_Percentages Not From Ontario: ");

        for (Map.Entry<Integer, Integer> entry : countNotFromOntario.entrySet()) {
            int percentage = (int) Math.round(((double) entry.getValue() / (double) camperManager.getCamperAmountInYear(entry.getKey())) * 100);
            System.out.println(entry.getKey() + ": " + percentage + "%");
        }

    }

    // Main function for Q3
    private void printPostalCodes() {

        Map<Integer, Integer> mPostalCountEachYear = new HashMap<>();

        for (Camper camper : camperManager.getCampers()) {
            for (int year : removeDuplicates(camper.getYearsInHistory())) {
                if (camper.getPcode().startsWith("M")) {
                    if (mPostalCountEachYear.containsKey(year))
                        mPostalCountEachYear.put(year, mPostalCountEachYear.get(year) + 1);
                    else
                        mPostalCountEachYear.put(year, 1);
                }
            }
        }

        System.out.println("\nQ3 Percentages (M) each year:");

        for (Map.Entry<Integer, Integer> entry : mPostalCountEachYear.entrySet()) {
            int percentage = (int) Math.round(((double) entry.getValue() / (double) amountEachYear.get(entry.getKey())) * 100);
            System.out.println(entry.getKey() + ": " + percentage + "%");
        }

    }

    // Main function for Q4

    private void printRaces() {

        Map<Integer, List<Camper>> camperEachYear = new HashMap<>();

        for (Camper camper : camperManager.getCampers()) {
            for (int year = 2015; year <= 2019; year++) {
                if (camper.getYearsInHistory().contains(year)) {
                    if (camperEachYear.containsKey(year)) {
                        camperEachYear.get(year).add(camper);
                    } else {
                        List<Camper> newList = new ArrayList<>();
                        newList.add(camper);
                        camperEachYear.put(year, newList);
                    }
                }
            }
        }

        System.out.println("\nQ4_Percentages:");

        for (Map.Entry<Integer, List<Camper>> entry : camperEachYear.entrySet()) {
            int nonWhites = 0;
            for (Camper camper : entry.getValue()) {
                if (!camper.getRace().equalsIgnoreCase("white")) {
                    nonWhites++;
                }
            }
            int percentage = (int) Math.round(((double) nonWhites / (double) amountEachYear.get(entry.getKey())) * 100);
            System.out.println(entry.getKey() + ": " + percentage + "%");
        }

    }

    private void dropOffAges() {

        Map<Integer, Map<Integer, Integer>> amountDropped = new HashMap<>();

        for (int year = 1989; year <= 2018; year++)
            amountDropped.put(year, new HashMap<>());

        for (Camper camper : camperManager.getCampers()) {
            List<Integer> years = removeDuplicates(camper.getYearsInHistory());
            if (years.size() >= 1) {
                int lastYear = years.get(years.size() - 1);
                if (lastYear != 2019) {
                    int age = getAgeFromGroup(camper.getDropOffAgeGroup());
                    if (age != 0) {
                        Map<Integer, Integer> map = amountDropped.get(lastYear);
                        if (map.containsKey(age))
                            map.put(age, map.get(age) + 1);
                        else
                            map.put(age, 1);
                    }
                }
            }
        }

        System.out.println("\nFormat: Age: Amount Dropped");

        for (int year : amountDropped.keySet()) {
            System.out.println(year + ":");
            for (Map.Entry<Integer, Integer> entrySet : amountDropped.get(year).entrySet()) {
                System.out.println("    " + entrySet.getKey() + ": " + entrySet.getValue());
            }
        }

    }

    private List<Integer> removeDuplicates(List<Integer> list) {
        List<Integer> newList = new ArrayList<>();
        for (int integer : list) {
            if (!newList.contains(integer)) {
                newList.add(integer);
            }
        }
        return newList;
    }

    public int getAgeFromGroup(String group) {
        for (AgeTypes ageType : AgeTypes.values()) {
            if (ageType.name().equalsIgnoreCase(group)) {
                return ageType.getAgeGroup();
            }
        }
        return 0;
    }

    public static Main getInstance() {
        return instance;
    }

}
