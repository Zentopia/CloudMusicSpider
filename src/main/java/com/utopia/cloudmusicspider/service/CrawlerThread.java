package com.utopia.cloudmusicspider.service;

import com.utopia.cloudmusicspider.model.SongModel;
import com.utopia.cloudmusicspider.model.WebPageModel;
import com.utopia.cloudmusicspider.model.WebPageModel.CrawledStatus;
import com.utopia.cloudmusicspider.model.WebPageModel.PageType;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            WebPageModel webPageModel = crawlerService.getUnCrawlPage(); // TODO: 更好的退出机制
            if (webPageModel == null)
                return; // 拿不到url，说明没有需要爬的url，直接退出

            try {
                scrachData(webPageModel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void scrachData(WebPageModel webPageModel) throws Exception {
        if (PageType.playLists.equals(webPageModel.getType()))
            parsePlaylists(webPageModel).forEach(page -> crawlerService.savePage(page));
        if (PageType.playList.equals(webPageModel.getType()))
            parsePlaylist(webPageModel).forEach(page -> crawlerService.savePage(page));
        if (PageType.song.equals(webPageModel.getType()))
            crawlerService.saveSongModel(parseSong(webPageModel));
    }

    private List<WebPageModel> parsePlaylists(WebPageModel webPageModel) throws IOException {

        int limit = 35;
        int offset = 0;
        int elementNum = 0;
        List<WebPageModel> playLists = new ArrayList<WebPageModel>();

        do {
            //http://music.163.com/#/discover/playlist/?order=hot&cat=%E5%8D%8E%E8%AF%AD351375
            String url = String.format("%s&limit=%d&offset=", webPageModel.getUrl(), limit, offset);
            Connection.Response response = Jsoup.connect(url).timeout(3000).execute();
            webPageModel.setHtml(response.body());

            if (response.statusCode() / 100 == 2) {
                Document doc = Jsoup.parse(webPageModel.getHtml());
                Elements elements = doc.select("p.dec");
                String playListBaseUrl = "http://music.163.com";

                for (Element element : elements) {
                    Element subElement = element.select("a").first();
                    String title = subElement.attr("title");
                    String playListsUrl = playListBaseUrl + subElement.attr("href");
                    WebPageModel playListWebPageModel = new WebPageModel(playListsUrl, PageType.playList, title);
                    playLists.add(playListWebPageModel);
                }

                elementNum = elements.size();
                offset = offset + limit;
            }

        } while (elementNum > 0);

        return playLists;
    }

    private List<WebPageModel> parsePlaylist(WebPageModel webPage) {
        //解析歌单页面
        return null;
    }

    private SongModel parseSong(WebPageModel webPage) throws Exception {
        // 解析歌曲页面
        return null;
    }

}
