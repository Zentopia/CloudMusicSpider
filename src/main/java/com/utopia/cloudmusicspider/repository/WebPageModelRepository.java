package com.utopia.cloudmusicspider.repository;

import com.sun.webkit.WebPage;
import com.utopia.cloudmusicspider.model.WebPageModel;
import com.utopia.cloudmusicspider.model.WebPageModel.CrawledStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.transaction.Transactional;

/**
 * Created by utopia on 2017/8/30.
 * Copyright © 2017 utopia. All rights reserved.
 */

@Repository
public interface WebPageModelRepository extends JpaRepository<WebPageModel, String>{

    //通过状态来查询
    public WebPageModel findTopByStatus(CrawledStatus status);

    @Modifying
    @Transactional
    @Query("update WebPageModel w set w.status = ?1")
    void resetStatus(CrawledStatus status);

}
