package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    String[] a = new String[0];

    public Main() {
        readCoordinates();

        while (true) {
            startLoopCoordinates();
        }
    }

    public void readCoordinates() {
        File f = new File("C:\\Users\\Gurra\\DokumentsGitub\\CurlScript\\src\\main\\java\\com\\company\\coordinates.txt");

        try {
            Scanner sc = new Scanner(f);
            String x = sc.next();
            a = x.split(",");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void startLoopCoordinates() {
        for (int i = 0; i < a.length; i+=2) {
            Coordinate c = new Coordinate(Double.parseDouble(a[i]), Double.parseDouble(a[i+1]));
            postLatitude(c.getLat());
            postLongitude(c.getLon());

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Coordinates posted.. wave " + i + " of " + a.length);
        }
    }
    
    public void postLatitude(Double lat) {
        String command = "curl -v -X POST -d \"{\\\"latitude\\\": "+lat+"}\" https://webiot.ibmcloud.iioote.io//api/v1/IHD6NQeTFa4PvXLKtxzb/telemetry --header \"Content-Type:application/json\"";

        try {
            Process p = Runtime.getRuntime().exec(command);
            p.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void postLongitude(Double lon) {
        String command = "curl -v -X POST -d \"{\\\"longitude\\\": "+lon+"}\" https://webiot.ibmcloud.iioote.io//api/v1/IHD6NQeTFa4PvXLKtxzb/telemetry --header \"Content-Type:application/json\"";

        try {
            Process p = Runtime.getRuntime().exec(command);
            p.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
