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

    @ManyToOne
    @JoinColumn(name = "album_category_id")
    private AlbumCategory albumCategory;


    //构造方法
    public Album() {

    }

    public Album(String id, String name, String url, String audienceNum, AlbumCategory albumCategory) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.audienceNum = audienceNum;
        this.albumCategory = albumCategory;
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

    public AlbumCategory getAlbumCategory() {
        return albumCategory;
    }

    public void setAlbumCategory(AlbumCategory albumCategory) {
        this.albumCategory = albumCategory;
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
