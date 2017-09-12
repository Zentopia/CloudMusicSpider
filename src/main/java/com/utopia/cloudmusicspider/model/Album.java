package com.utopia.cloudmusicspider.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Album extends BaseModel{
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String name;
    private String url;
    private String audienceNum;
    private int songsNum;
    private int playNum;
    private int collectionNum;

    public enum CrawledStatus {
        crawled,
        notCrawled
    }

    @Enumerated(EnumType.STRING)
    private CrawledStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "album_join_category", joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "url"))
    private Set<AlbumCategory> albumCategories;

    //构造方法
    public Album() {

    }

    public Album(String id, String name, String url, String audienceNum, Set<AlbumCategory> albumCategories) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.audienceNum = audienceNum;
        this.albumCategories = albumCategories;
        this.status = CrawledStatus.notCrawled;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AlbumCategory> getAlbumCategories() {
        return albumCategories;
    }

    public void setAlbumCategories(Set<AlbumCategory> albumCategories) {
        this.albumCategories = albumCategories;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAudienceNum() {
        return audienceNum;
    }

    public void setAudienceNum(String audienceNum) {
        this.audienceNum = audienceNum;
    }
}
