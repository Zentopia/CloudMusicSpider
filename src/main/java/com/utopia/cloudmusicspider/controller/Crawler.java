package com.utopia.cloudmusicspider.controller;

import com.utopia.cloudmusicspider.model.SongModel;
import com.utopia.cloudmusicspider.model.WebPageModel;

import java.util.List;

public interface Crawler {

    /**
     * 初始化爬虫队列
     */
    void initCrawlerList();

    /**
     * 获取一个未爬取页面，并将其标记为已爬
     * @return
     */
    WebPageModel getUnCrawlPage();

    /**
     * 添加页面至爬虫列表
     */
    List<WebPageModel> addToCrawlList(List<WebPageModel> webPages);

    /**
     * 添加歌曲至已爬歌曲列表
     */
    SongModel saveSong(SongModel song);

    /**
     * 获取所有已爬歌曲
     */
    List<SongModel> getSongs();

    /**
     * 获取未爬页面->获取html->解析html并对结果进行处理->标记页面
     * 即流程图右下角黑框部分
     */
    void doRun();

    /**
     * 运行爬虫整体流程
     */
    default void run() {
        initCrawlerList();
        doRun();
    }
}
