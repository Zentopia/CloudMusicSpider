package com.utopia.model;

public class WebPageModel {
    public enum PageType{
        song,
        playList,
        playLists;
    }

    private String url;
    private String title;
    private PageType type;
    private Boolean isCrawled;
    private String html;

}
