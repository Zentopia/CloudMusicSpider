package com.utopia.repository;

/**
 * Created by utopia on 2017/8/30.
 * Copyright © 2017 utopia. All rights reserved.
 */

import com.utopia.model.SongModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SongRepository extends JpaRepository<SongModel, String>{
    List<SongModel> findByCommentCountGreaterThan(Long commentCount);
}
