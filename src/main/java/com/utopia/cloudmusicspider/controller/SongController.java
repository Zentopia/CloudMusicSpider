package com.utopia.cloudmusicspider.controller;

import com.utopia.cloudmusicspider.repository.SongModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SongController {

    @Autowired
    SongModelRepository songRepository;

    @GetMapping({"/songs", ""})
    public String songs(Model model,
                        @PageableDefault(size = 100, sort = "commentCount", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("songs", songRepository.findAll(pageable));
        return "songs";
    }

}