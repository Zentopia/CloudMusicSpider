package com.utopia.cloudmusicspider.service;

import com.utopia.cloudmusicspider.model.Song;
import com.utopia.cloudmusicspider.model.WebPageModel;
import com.utopia.cloudmusicspider.model.WebPageModel.PageType;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
//            WebPageModel webPageModel = crawlerService.getUnCrawlPage(); // TODO: 更好的退出机制
//            if (webPageModel == null)
//                return; // 拿不到url，说明没有需要爬的url，直接退出
//
//            try {
//                scrachData(webPageModel);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }

    private void scrachData(WebPageModel webPageModel) throws Exception {

    }

    private List<WebPageModel> parsePlayLists(WebPageModel webPageModel) throws IOException {

        int limit = 35;
        int offset = 0;
        int elementNum = 0;
        List<WebPageModel> playLists = new ArrayList<WebPageModel>();

        do {
            String url = String.format("%s&limit=%d&offset=%d", webPageModel.getUrl(), limit, offset);
            Connection.Response response = Jsoup.connect(url).timeout(3000).execute();
            webPageModel.setHtml(response.body());

            if (response.statusCode() / 100 == 2) {
                Document doc = Jsoup.parse(webPageModel.getHtml());
                Elements elements = doc.select("p.dec");
                String playListBaseUrl = "http://music.163.com";
                Elements listenerNums = doc.select("span.nb");
                elementNum = elements.size();

                for (int i = 0; i < elementNum; i++) {
                    Element albumElement = elements.get(i);
                    Element listenerNumsElement = listenerNums.get(i);
                    Element subElement = albumElement.select("a").first();
                    String albumTitle = subElement.attr("title");
                    String playListsUrl = playListBaseUrl + subElement.attr("href");
                    String listenerNum = listenerNumsElement.text();
                    WebPageModel playListWebPageModel = new WebPageModel(playListsUrl, albumTitle, listenerNum, PageType.playList);
                    playLists.add(playListWebPageModel);
                }

                offset = offset + limit;
            }

        } while (elementNum > 0);

        return playLists;
    }

    private List<WebPageModel> parsePlaylist(WebPageModel webPageModel) {
        String url = webPageModel.getUrl();
        Connection.Response response = null;
        try {
            response = Jsoup.connect(url).timeout(3000).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        webPageModel.setHtml(response.body());
        return null;
    }

    private Song parseSong(WebPageModel webPage) throws Exception {
        // 解析歌曲页面
        return null;
    }

}
