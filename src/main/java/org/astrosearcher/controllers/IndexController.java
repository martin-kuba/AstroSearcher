package org.astrosearcher.controllers;

import org.astrosearcher.enums.SearchType;
import org.astrosearcher.enums.VizierCatalogueSearch;
import org.astrosearcher.models.SearchFormInput;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NumberFormatException.class)
    public String handleError(final ServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("errorMSG", "Status code: ");
        return "error";
    }
}
