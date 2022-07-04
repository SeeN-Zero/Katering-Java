package com.katering.kateringjava.controller;

import com.katering.kateringjava.model.Articles;
import com.katering.kateringjava.repository.ArticlesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticlesRepository articlesRepository;

    @GetMapping
    public String article(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("articles", articlesRepository.findAll());
        model.addAttribute("username", username);
        model.addAttribute("article", new Articles());
        model.addAttribute("articleUpdate", new Articles());
        return "article";
    }

    @PostMapping("/add")
    public String addArticle(@ModelAttribute Articles article) {
        articlesRepository.save(article);
        return "redirect:/article";
    }

    @PostMapping("/update/{id}")
    public String updateArticle(@PathVariable Long id, @ModelAttribute Articles articleUpdate) {
        Articles oldArticle = articlesRepository.getReferenceById(id);
        oldArticle.setTitle(articleUpdate.getTitle());
        oldArticle.setCaption(articleUpdate.getCaption());
        oldArticle.setContent(articleUpdate.getContent());
        articlesRepository.save(oldArticle);
        return "redirect:/article";
    }

    @GetMapping("/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articlesRepository.delete(articlesRepository.getReferenceById(id));
        return "redirect:/article";
    }
}
