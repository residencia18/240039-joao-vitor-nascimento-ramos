package br.com.cepedi.vercedoresFormula1.controller;

import br.com.cepedi.vercedoresFormula1.model.WinnerTDO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WinnerTDOReader {


    public static List<WinnerTDO> readWinners() {
        List<WinnerTDO> winners = new ArrayList<>();
        BufferedReader br = null;
        String line;
        String delimiter = ";";
        try {
            br = new BufferedReader(new FileReader("pilotos.csv"));
            br.readLine(); // Ignore header line
            while ((line = br.readLine()) != null) {
                String[] winnerData = line.split(delimiter);
                WinnerTDO winner = new WinnerTDO(winnerData[0], winnerData[1], Integer.parseInt(winnerData[2]));
                winners.add(winner);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return winners;
    }
}
