package org.astrosearcher.controllers;

import org.astrosearcher.enums.SearchType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("searchOptions", SearchType.values());
        return "index";
    }

    @PostMapping("index")
    public String searchAndLoadData(@RequestParam String searchInput, Model model) {
        model.addAttribute("searchOptions", SearchType.values());
        return "filled/index";
    }
}
