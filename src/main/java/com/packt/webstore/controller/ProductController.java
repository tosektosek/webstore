package com.packt.webstore.controller;

import com.packt.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kamil
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    private static final String products = "products";

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping()
    public String list(Model model) {
        model.addAttribute(products, productService.getAllProducts());
        return products;
    }

    @RequestMapping("/all")
    public String allProducts(Model model) {
        model.addAttribute(products, productService.getAllProducts());
        return products;
    }

    @RequestMapping("/{category}")
    public String getProductsByCategory(Model model, @PathVariable("category") String category) {
        model.addAttribute("products", productService.getProductsByCategory(category));
        return products;
    }


}
