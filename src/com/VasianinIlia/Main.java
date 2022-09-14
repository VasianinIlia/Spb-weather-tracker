package com.VasianinIlia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void getWeatherFromWeb(String webURL) throws IOException {
        URL url = new URL(webURL);
        URLConnection connection= url.openConnection();
        InputStream is =  connection.getInputStream();
        try( BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains("<div id=\"wtemp\" class=\"pos float\">")){
                    System.out.println(line.substring(63,68));
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

    }

    public static void main(String[] args) throws IOException {
        String url = "http://www.meteo.nw.ru/";
        // строка с температурой "<div id="wtemp" class="pos float">"

        getWeatherFromWeb(url);
    }
}