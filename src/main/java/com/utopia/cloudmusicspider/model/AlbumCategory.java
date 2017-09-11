package com.utopia.cloudmusicspider.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AlbumCategory extends BaseModel{

    public enum CrawledStatus{
        crawled,
        notCrawled
    }

    @Id
    private String url;

    private String title;

    @OneToMany(mappedBy = "albumCategory", cascade = CascadeType.ALL)
    private Set<Album> albums;

    @Enumerated(EnumType.STRING)
    private CrawledStatus status;

    @Transient //不序列化
    private String html;

    //初始化方法
    public AlbumCategory(){
        super();
    }

    public AlbumCategory(String url, String title) {
        this.url = url;
        this.title = title;
        this.status = CrawledStatus.notCrawled;
    }

    //getter & setter
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

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "AlbumCategory [url=" + url + ", status=" + status + "]";
    }
}
