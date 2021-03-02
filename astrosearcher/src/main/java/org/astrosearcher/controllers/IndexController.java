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
        System.out.println("PROCESSED!");
//        model.addAttribute("searchOptions", Arrays.stream(SearchType.values()).map(SearchType::toString));
        model.addAttribute("searchOptions", SearchType.values());
        return "index";
    }

    // responds to /index?input=SEARCH_INPUT
//    @PostMapping("index")
//    public String loadSingleInput(@RequestParam String searchInput, Model model) {
////        model.addAttribute("results", SearchEngine.findAll(searchInput));
////        model.addAttribute("input", searchInput);
////        model.addAttribute("input", new MastMashupRequest(searchInput).toJson() + "}");
//        return "testing";
//    }

    @PostMapping("index")
    public String searchAndLoadData(@RequestParam String searchInput, Model model) {
        System.out.println("test");
        model.addAttribute("searchOptions", SearchType.values());
//        return "redirect:templates/index.html";
        return "filled/index";
        /* return "filled/index#results-table"; */
    }
}
