package com.utopia.cloudmusicspider.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class WebFetcher {

    public String fetch(String url) {

        Connection.Response response = null;

        try {
            response = Jsoup.connect(url).timeout(3000).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.body();
    }

}
