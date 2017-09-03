package com.utopia.service;

import com.utopia.model.SongModel;
import com.utopia.model.WebPageModel;
import com.utopia.model.WebPageModel.PageType;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
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
            WebPageModel webPageModel = crawlerService.getUnCrawlPage(); // TODO: 更好的退出机制
            if (webPageModel == null)
                return; // 拿不到url，说明没有需要爬的url，直接退出
            try {
                if (fetchHtml(webPageModel))
                    parse(webPageModel); // TODO: 如果获取页面失败，考虑重连机制，这里继续爬下个页面
            } catch (Exception e) { }
        }
    }

    private boolean fetchHtml(WebPageModel webPageModel) throws IOException {
        Connection.Response response = Jsoup.connect(webPageModel.getUrl()).timeout(3000).execute();
        webPageModel.setHtml(response.body());
        return response.statusCode() / 100 == 2;
    }

    private void parse(WebPageModel webPageModel) throws Exception {
        if (PageType.playLists.equals(webPageModel.getType()))
            parsePlaylists(webPageModel).forEach(page -> crawlerService.savePage(page));
        if (PageType.playList.equals(webPageModel.getType()))
            parsePlaylist(webPageModel).forEach(page -> crawlerService.savePage(page));
        if (PageType.song.equals(webPageModel.getType()))
            crawlerService.saveSong(parseSong(webPageModel));
    }

    private List<WebPageModel> parsePlaylists(WebPageModel webPage) {
        // 解析歌单列表页面
        return null;
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
