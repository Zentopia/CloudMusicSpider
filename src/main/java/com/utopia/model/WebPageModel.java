package com.utopia.model;

public class WebPageModel {
    public enum PageType{
        song,
        playList,
        playLists;
    }

    public enum CrawledStatus{
        crawled,
        notCrawled;
    }

    private String url;
    private String title;
    private PageType type;
    private CrawledStatus status;
    private String html;

}
