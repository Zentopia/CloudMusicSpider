package com.utopia.service;

import com.sun.webkit.WebPage;
import com.utopia.model.WebPageModel;
import com.utopia.repository.SongRepository;
import com.utopia.repository.WebPageRepository;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by utopia on 2017/8/30.
 * Copyright © 2017 utopia. All rights reserved.
 */


@Service
public class CrawlerService {

    private final CacheManager cacheManager;
    private final String cacheName = "com.utopia.Songs";
    //最大线程数
    public static final Integer MAX_THREADS = 20;
    @Autowired
    SongRepository songRepository;
    @Autowired
    WebPageRepository webPageRepository;

    public CrawlerService(){
        cacheManager = CacheManager.getInstance();
    }

    public WebPageModel savePage(WebPageModel webPageModel){
        WebPage result = webPageRepository.findOne(webPageModel.getUrl());

    }

}
