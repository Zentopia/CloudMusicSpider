package com.utopia.repository;

import com.sun.webkit.WebPage;
import com.utopia.model.WebPageModel;
import com.utopia.model.WebPageModel.CrawledStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by utopia on 2017/8/30.
 * Copyright © 2017 utopia. All rights reserved.
 */


public interface WebPageRepository extends JpaRepository<WebPageModel, String>{

    WebPageModel findTopByStatus(CrawledStatus status);

    @Modifying
    @Transactional
    @Query("update WebPage w set w.status = ?1")
    void resetStatus(CrawledStatus status);
}
