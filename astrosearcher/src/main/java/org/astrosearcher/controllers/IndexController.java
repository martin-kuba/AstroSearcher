package org.astrosearcher.controllers;

import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.enums.SearchType;
import org.astrosearcher.enums.VizierCatalogueSearch;
import org.astrosearcher.models.SearchFormInput;
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
        model.addAttribute("vizierOptions", VizierCatalogueSearch.values());
        model.addAttribute(new SearchFormInput());
        return "index";
    }

    @PostMapping("index")
    public String searchAndLoadData(@RequestParam String searchInput, Model model) {
        model.addAttribute("searchOptions", SearchType.values());
        model.addAttribute("vizierOptions", VizierCatalogueSearch.values());
        return "filled/index";
    }
}
