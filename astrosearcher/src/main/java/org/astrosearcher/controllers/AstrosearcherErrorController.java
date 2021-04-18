//package org.astrosearcher.controllers;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Controller
//public class AstrosearcherErrorController implements ErrorController {
//
//    @RequestMapping("/error")
//    public String handleError(final ServletRequest request, HttpServletResponse response, Model model) {
//        model.addAttribute("errorMSG", "Status code: ");
//        return "index";
//    }
//
//
//
//    @Override
//    public String getErrorPath() {
//        return null;
//    }
//}
