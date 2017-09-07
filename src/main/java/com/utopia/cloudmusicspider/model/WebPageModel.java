package com.utopia.cloudmusicspider.model;

import javax.persistence.*;

@Entity
public class WebPageModel {

    public enum PageType{
        song,
        playList,
        playLists
    }

    public enum CrawledStatus{
        crawled,
        notCrawled
    }

    @Id
    private String url;
    private String title;
    private String listenerNum;

    public String getListenerNum() {
        return listenerNum;
    }

    public void setListenerNum(String listenerNum) {
        this.listenerNum = listenerNum;
    }

    @Enumerated(EnumType.STRING)
    private PageType type;

    @Enumerated(EnumType.STRING)
    private CrawledStatus status;

    @Transient //不序列化
    private String html;

    public WebPageModel(){
        super();
    }

    public WebPageModel(String url, PageType type){
        super();
        this.url = url;
        this.type = type;
        this.status = CrawledStatus.notCrawled;
    }

    public WebPageModel(String url, PageType type, String title){
        super();
        this.url = url;
        this.type = type;
        this.title = title;
        this.status = CrawledStatus.notCrawled;
    }

    public WebPageModel(String url, String title, String listenerNum, PageType type) {
        this.url = url;
        this.title = title;
        this.listenerNum = listenerNum;
        this.type = type;
        this.status = CrawledStatus.crawled;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PageType getType() {
        return type;
    }

    public void setType(PageType type) {
        this.type = type;
    }

    public CrawledStatus getStatus() {
        return status;
    }

    public void setStatus(CrawledStatus status) {
        this.status = status;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return "WebPage [url=" + url + ", status=" + status + "]";
    }
}
