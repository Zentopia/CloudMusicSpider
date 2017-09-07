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
                if (fetchHtml(webPageModel))
                    parse(webPageModel); // TODO: 如果获取页面失败，考虑重连机制，这里继续爬下个页面
            } catch (Exception e) { }
        }
    }

    //抓取网页
    private boolean fetchHtml(WebPageModel webPageModel) throws IOException {
        Connection.Response response = Jsoup.connect("http://music.163.com/discover/playlist/?cat=00后").timeout(3000).execute();
        webPageModel.setHtml(response.body());
        return response.statusCode() / 100 == 2;
    }

    //解析网页
    private void parse(WebPageModel webPageModel) throws Exception {
        if (PageType.playLists.equals(webPageModel.getType()))
            parsePlaylists(webPageModel).forEach(page -> crawlerService.savePage(page));
        if (PageType.playList.equals(webPageModel.getType()))
            parsePlaylist(webPageModel).forEach(page -> crawlerService.savePage(page));
        if (PageType.song.equals(webPageModel.getType()))
            crawlerService.saveSongModel(parseSong(webPageModel));
    }

    private List<WebPageModel> parsePlaylists(WebPageModel webPage) {

        Document doc = Jsoup.parse(webPage.getHtml());
        Elements elements = doc.select("p.dec");
        String playListBaseUrl = "http://music.163.com";
        List<WebPageModel> playLists = new ArrayList<WebPageModel>();

        for (Element element : elements) {
            Element subElement = element.select("a").first();
            String title = subElement.attr("title");
            String playListsUrl = playListBaseUrl + subElement.attr("href");

            WebPageModel playListWebPageModel = new WebPageModel(playListsUrl, PageType.playList, title);
            playLists.add(playListWebPageModel);
        }

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
