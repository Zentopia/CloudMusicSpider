package com.utopia.cloudmusicspider.repository;

/**
 * Created by utopia on 2017/8/30.
 * Copyright © 2017 utopia. All rights reserved.
 */

import com.utopia.cloudmusicspider.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongModelRepository extends JpaRepository<Song, String>{
    List<Song> findByCommentCountGreaterThan(Long commentCount);
}
