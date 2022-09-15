package com.VasianinIlia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static String getWeatherFromWeb(String webURL) throws IOException {
        URL url = new URL(webURL);
        URLConnection connection= url.openConnection();
        InputStream is =  connection.getInputStream();
        String result = "";
        try( BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains("<div id=\"wtemp\" class=\"pos float\">")){
                        String plusCleaner = line.substring(63, 64);
                        if (plusCleaner.equals("+")){
                            result = line.substring(64, 68);
                        }else {
                            result = line.substring(63, 68);
                        }
                }
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new MalformedURLException("URL is malformed!!");
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        }
        return result;
    }

    public static String getTime(){
        LocalTime localTime = LocalTime.now();
        StringBuffer lt = new StringBuffer(localTime + "");
        String result = ""+ lt.delete(8, 18);
        return result;
    }
    public static String getDate(){
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        String date = year + "-" + month + "-" + day;
        return date;
    }

    public static void main(String[] args) throws IOException {
        String url = "http://www.meteo.nw.ru/";
        // строка с температурой "<div id="wtemp" class="pos float">"
        System.out.println(MySqlQuery.selectAll());
        System.out.println(getWeatherFromWeb(url));
        System.out.println(getTime());
        System.out.println(getDate());
    }
}