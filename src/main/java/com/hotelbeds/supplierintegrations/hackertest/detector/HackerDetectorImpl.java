package com.hotelbeds.supplierintegrations.hackertest.detector;

public class HackerDetectorImpl implements HackerDetector {

    @Override
    public String parseLine(String line) {

        //TODO: Convert received line into multiple vars and Create object for it
        String[] splitLines = line.split(",");
        String ip = splitLines[0];
        String epoch = splitLines[1];
        String signInStatus = splitLines[2];
        String name = splitLines[3];

        return null;
    }
}
