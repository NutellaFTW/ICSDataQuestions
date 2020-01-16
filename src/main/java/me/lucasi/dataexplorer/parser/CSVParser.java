package me.lucasi.dataexplorer.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    private String filePath;

    public CSVParser(String filePath) {
        this.filePath = filePath;
    }

    public List<String[]> parseFile() {

        BufferedReader bufferedReader = null;
        String line;
        String seperator = ",";

        List<String[]> dataParsed = new ArrayList<>();

        try {

            bufferedReader = new BufferedReader(new FileReader(filePath));

            while ((line = bufferedReader.readLine()) != null) {
                dataParsed.add(line.split(seperator));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return dataParsed;

    }

}
