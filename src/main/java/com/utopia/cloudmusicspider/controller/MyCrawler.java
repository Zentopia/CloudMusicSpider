package com.utopia.cloudmusicspider.controller;

import com.utopia.cloudmusicspider.model.SongModel;
import com.utopia.cloudmusicspider.model.WebPageModel;

import java.util.ArrayList;
import java.util.List;

public class MyCrawler implements Crawler {

    public List<WebPageModel> crawlerList;

    @Override
    public void initCrawlerList() {
        crawlerList = new ArrayList<WebPageModel>();
        crawlerList.add(new WebPageModel("http://music.163.com/discover/playlist/?order=hot&cat=%E5%85%A8%E9%83%A8&limit=35&offset=0", WebPageModel.PageType.playLists));
    }

    @Override
    public WebPageModel getUnCrawlPage() {
        return null;
    }

    @Override
    public List<WebPageModel> addToCrawlList(List<WebPageModel> webPages) {
        return null;
    }

    @Override
    public SongModel saveSong(SongModel song) {
        return null;
    }

    @Override
    public List<SongModel> getSongs() {
        return null;
    }

    @Override
    public void doRun() {
        WebPageModel webPageModel;
        while ((webPageModel = getUnCrawlPage()) != null) {
            // your code here
        }
    }

    @Override
    public void run() {

    }
}
