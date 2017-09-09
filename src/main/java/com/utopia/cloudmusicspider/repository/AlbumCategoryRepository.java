package com.utopia.cloudmusicspider.repository;

import com.utopia.cloudmusicspider.model.AlbumCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.utopia.cloudmusicspider.model.AlbumCategory.CrawledStatus;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AlbumCategoryRepository extends JpaRepository <AlbumCategory, String>{
    //通过状态来查询
    public AlbumCategory findTopByStatus(CrawledStatus status);

    @Modifying
    @Transactional
    @Query("update AlbumCategory w set w.status = ?1")
    void resetStatus(CrawledStatus status);

}
