package me.lucasi.dataexplorer.camper;

import me.lucasi.dataexplorer.parser.CSVParser;

import java.util.ArrayList;
import java.util.List;

public class CamperManager {

    private static CamperManager instance;

    private List<Camper> campers;

    public CamperManager() {
        instance = this;
        campers = new ArrayList<>();
        initCampers();
    }

    private void initCampers() {
        String filePath = getClass().getClassLoader().getResource("DATA12.csv").getPath();
        CSVParser csvParser = new CSVParser(filePath);
        csvParser.parseFile().forEach(entry ->
                addCamper(new Camper(entry[0], entry[1], entry[2], entry[3], entry[4], entry[5], entry[6])));
    }

    public void addCamper(Camper camper) {
        campers.add(camper);
    }

    public int getCamperAmountInYear(int yearToFind) {
        int amount = 0;
        for (Camper camper : campers)
            for (int year : camper.getYearsInHistory())
                if (year == yearToFind)
                    amount++;
        return amount;
    }

    public List<Camper> getCampers() {
        return campers;
    }

    public static CamperManager getInstance() {
        return instance;
    }
}
