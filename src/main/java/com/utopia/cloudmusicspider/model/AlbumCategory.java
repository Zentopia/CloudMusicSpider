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

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "albumCategories")
    private Set<Album> albums;

    @Enumerated(EnumType.STRING)
    private CrawledStatus status;

    @Transient //不序列化
    private String html;

    //初始化方法
    public AlbumCategory(){
        super();
    }

    public AlbumCategory(String url, String name) {
        this.url = url;
        this.name = name;
        this.status = CrawledStatus.notCrawled;
    }

    //getter & setter
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
