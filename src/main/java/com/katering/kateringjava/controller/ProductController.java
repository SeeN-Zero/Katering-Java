package com.katering.kateringjava.controller;

import com.katering.kateringjava.model.Products;
import com.katering.kateringjava.repository.ArticlesRepository;
import com.katering.kateringjava.repository.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Transactional
@Controller
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductsRepository productsRepository;
    private final ArticlesRepository articlesRepository;

    @GetMapping
    public String product(Model model, Principal principal) {
        List<Products> products = productsRepository.findAll();
        Long articlesCount = articlesRepository.count();
        int productsCount = products.size();
        String username = principal.getName();
        model.addAttribute("products", products);
        model.addAttribute("productsCount", productsCount);
        model.addAttribute("articlesCount", articlesCount);
        model.addAttribute("username", username);
        model.addAttribute("product", new Products());
        model.addAttribute("productUpdate", new Products());
        return "product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Products product) {
        productsRepository.save(product);
        return "redirect:/product";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Products productUpdate,
                                @RequestParam(value = "imageUpdate", required = false) MultipartFile newFile) throws IOException {
        Products oldProduct = productsRepository.getReferenceById(id);
        oldProduct.setName(productUpdate.getName());
        oldProduct.setDescription(productUpdate.getDescription());
        if(newFile != null){
            oldProduct.setImage(newFile);
        }
        productsRepository.save(oldProduct);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productsRepository.delete(productsRepository.getReferenceById(id));
        return "redirect:/product";
    }
}
