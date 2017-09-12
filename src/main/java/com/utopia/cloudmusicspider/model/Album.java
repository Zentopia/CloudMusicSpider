package com.utopia.cloudmusicspider.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Album extends BaseModel{

    public enum CrawledStatus {
        crawled,
        notCrawled
    }

    @Id
    private String id;

    private String name;
    private String url;
    private String audienceNum;
    private int songsNum;
    private int playNum;
    private int collectionNum;

    @Transient
    private String html;

    @Enumerated(EnumType.STRING)
    private CrawledStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "album_join_category", joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "url"))
    private Set<AlbumCategory> albumCategories;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "albums")
    private Set<Song> songs;

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

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public int getSongsNum() {
        return songsNum;
    }

    public void setSongsNum(int songsNum) {
        this.songsNum = songsNum;
    }

    public int getPlayNum() {
        return playNum;
    }

    public void setPlayNum(int playNum) {
        this.playNum = playNum;
    }

    public int getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
    }

    public CrawledStatus getStatus() {
        return status;
    }

    public void setStatus(CrawledStatus status) {
        this.status = status;
    }
}
