package com.utopia.cloudmusicspider.repository;

import com.utopia.cloudmusicspider.model.Album;
import com.utopia.cloudmusicspider.model.Album.CrawledStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, String> {

    public Album findTopByStatus(CrawledStatus status);


}
