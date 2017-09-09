package com.utopia.cloudmusicspider.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Album extends BaseModel{

    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "album_category_id")
    private AlbumCategory albumCategory;

    private String url;

    //构造方法
    public Album() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
