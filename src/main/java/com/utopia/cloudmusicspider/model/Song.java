package com.utopia.cloudmusicspider.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Song {

    public enum CrawledStatus {
        crawled,
        notCrawled
    }

    @Id
    private String id;

    private String name;
    private String url;
    private Long commentCount;

    @Enumerated(EnumType.STRING)
    private CrawledStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "song_album", joinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"))
    private Set<Album> albums;

    public Song(){
        super();
    }

    public Song(String id, String name, String url, Long commentCount) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.commentCount = commentCount;
        this.status = CrawledStatus.notCrawled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "Song [url=" + url + ", title=" + name + ", commentCount=" + commentCount + "]";
    }

}
