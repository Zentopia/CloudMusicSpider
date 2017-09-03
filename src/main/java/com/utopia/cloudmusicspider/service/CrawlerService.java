package com.utopia.cloudmusicspider.service;

import com.utopia.cloudmusicspider.model.SongModel;
import com.utopia.cloudmusicspider.model.WebPageModel;
import com.utopia.cloudmusicspider.model.WebPageModel.CrawledStatus;
import com.utopia.cloudmusicspider.repository.WebPageModelRepository;
import com.utopia.cloudmusicspider.repository.SongModelRepository;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by utopia on 2017/8/30.
 * Copyright © 2017 utopia. All rights reserved.
 */


@Service
public class CrawlerService {

    private final CacheManager cacheManager;
    private final String cacheName = "com.tianmaying.SongModels";
    public static final Integer MAX_THREADS = 20;
    @Autowired SongModelRepository SongModelRepository;
    @Autowired WebPageModelRepository WebPageModelRepository;

    public CrawlerService() {
        cacheManager = CacheManager.getInstance();
    }

    public WebPageModel savePage(WebPageModel WebPageModel) {
        WebPageModel result = WebPageModelRepository.findOne(WebPageModel.getUrl());
        return result == null ? WebPageModelRepository.saveAndFlush(WebPageModel) : result;
    }

    public SongModel saveSongModel(SongModel SongModel) {
        SongModel result = SongModelRepository.findOne(SongModel.getUrl());
        if(result == null) {
            result = SongModelRepository.saveAndFlush(SongModel);
        } else {
            result.setCommentCount(SongModel.getCommentCount());
            result = SongModelRepository.saveAndFlush(result);
        }
        return result;
    }

    public WebPageModel update(WebPageModel WebPageModel) {
        return WebPageModelRepository.save(WebPageModel);
    }

    public void reset() {
        WebPageModelRepository.resetStatus(CrawledStatus.notCrawled);
    }

    public synchronized WebPageModel getUnCrawlPage() {
        // 返回未爬页面
        return null;
    }

    private void init(String catalog) {
        // 根据catalog初始化爬虫队列
    }

    @Async
    public void init() {
        WebPageModelRepository.deleteAll();
        init("全部");
        init("华语");
        init("欧美");
        init("日语");
        init("韩语");
        init("粤语");
        init("小语种");
        init("流行");
        init("摇滚");
        init("民谣");
        init("电子");
        init("舞曲");
        init("说唱");
        init("轻音乐");
        init("爵士");
        init("乡村");
        init("R&B/Soul");
        init("古典");
        init("民族");
        init("英伦");
        init("金属");
        init("朋克");
        init("蓝调");
        init("雷鬼");
        init("世界音乐");
        init("拉丁");
        init("另类/独立");
        init("New Age");
        init("古风");
        init("后摇");
        init("Bossa Nova");
        init("清晨");
        init("夜晚");
        init("学习");
        init("工作");
        init("午休");
        init("下午茶");
        init("地铁");
        init("驾车");
        init("运动");
        init("旅行");
        init("散步");
        init("酒吧");
        init("怀旧");
        init("清新");
        init("浪漫");
        init("性感");
        init("伤感");
        init("治愈");
        init("放松");
        init("孤独");
        init("感动");
        init("兴奋");
        init("快乐");
        init("安静");
        init("思念");
        init("影视原声");
        init("ACG");
        init("校园");
        init("游戏");
        init("70后");
        init("80后");
        init("90后");
        init("网络歌曲");
        init("KTV");
        init("经典");
        init("翻唱");
        init("吉他");
        init("钢琴");
        init("器乐");
        init("儿童");
        init("榜单");
        init("00后");
    }

    @Async
    public void crawl() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        for(int i = 0; i < MAX_THREADS; i++) {
            executorService.execute(new CrawlerThread(this));
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        Ehcache ehcache = cacheManager.getEhcache(cacheName);
        ehcache.removeAll();
    }

    @Async
    public void update() throws InterruptedException {
        List<SongModel> WebPageModels = SongModelRepository.findByCommentCountGreaterThan(5000L);
        WebPageModels.forEach(s -> {
            WebPageModel p = WebPageModelRepository.findOne(s.getUrl());
            p.setStatus(CrawledStatus.notCrawled);
            WebPageModelRepository.save(p);
        });
        crawl();
    }

}
