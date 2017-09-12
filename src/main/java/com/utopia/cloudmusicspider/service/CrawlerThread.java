package com.utopia.cloudmusicspider.service;

import com.utopia.cloudmusicspider.model.*;
import com.utopia.cloudmusicspider.model.WebPageModel.PageType;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CrawlerThread implements Runnable{

    public static final String BASE_URL = "http://music.163.com/";
    public static final String text = "{\"username\": \"\", \"rememberLogin\": \"true\", \"password\": \"\"}";
    private CrawlerService crawlerService;

    public CrawlerThread() {
        super();
    }

    public CrawlerThread(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    @Override
    public void run() {
        while (true) {
            BaseModel webPageModel = crawlerService.getUnCrawlPage(); // TODO: 更好的退出机制
            if (webPageModel == null)
                return; // 拿不到url，说明没有需要爬的url，直接退出

            try {
                scrachData(webPageModel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void scrachData(BaseModel webPageModel) throws Exception {
        if (webPageModel instanceof AlbumCategory) {
            parseAlbumCategory((AlbumCategory) webPageModel);
        } else {
            if (webPageModel instanceof Album) {
                parseAlbum((Album) webPageModel);
            }
        }
    }

    private List<Album> parseAlbumCategory(AlbumCategory albumCategory) throws IOException {

        int limit = 35;
        int offset = 0;
        int elementNum = 0;
        List<Album> albums = new ArrayList<Album>();

        do {
            String url = String.format("%s&limit=%d&offset=%d", albumCategory.getUrl(), limit, offset);
            Connection.Response response = null;

            try {
                response = Jsoup.connect(url).timeout(3000).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            albumCategory.setHtml(response.body());

            if (response.statusCode() / 100 == 2) {
                Document doc = Jsoup.parse(albumCategory.getHtml());
                Elements elements = doc.select("p.dec");
                String playListBaseUrl = "http://music.163.com";
                Elements listenerNums = doc.select("span.nb");
                elementNum = elements.size();
                Elements bottomElements = doc.select("a.icon-play.f-fr");

                for (int i = 0; i < elementNum; i++) {

                    Element albumElement = elements.get(i);
                    Element listenerNumsElement = listenerNums.get(i);
                    Element bottomElement = bottomElements.get(i);
                    String albumID = bottomElement.attr("data-res-id");
                    Element subElement = albumElement.select("a").first();
                    String albumName = subElement.attr("title");
                    String albumUrl = playListBaseUrl + subElement.attr("href");
                    String audienceNum = listenerNumsElement.text();

                    Set<AlbumCategory> albumCategories = new HashSet<AlbumCategory>();
                    albumCategories.add(albumCategory);

                    Album album = new Album(albumID, albumName, albumUrl, audienceNum, albumCategories);
                    crawlerService.saveAlbum(album);
                    albums.add(album);
                }

                offset = offset + limit;
            }

        } while (elementNum > 0);

        return albums;
    }

    private List<WebPageModel> parseAlbum(Album album) {
        String url = album.getUrl();
        Connection.Response response = null;
        try {
            response = Jsoup.connect(url).timeout(3000).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        album.setHtml(response.body());

        if (response.statusCode() / 100 == 2) {
            Document doc = Jsoup.parse(album.getHtml());
            Elements songElements = doc.select("ul.f-hide");
        }

        return null;
    }

    private Song parseSong(WebPageModel webPage) throws Exception {
        // 解析歌曲页面
        return null;
    }

}
