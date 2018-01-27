package com.packt.webstore.controller;

import com.packt.webstore.domain.Product;
import com.packt.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Kamil
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    private static final String PRODUCTS = "products";

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({"/","/all"})
    public String allProducts(Model model) {
        model.addAttribute(PRODUCTS, productService.getAllProducts());
        return PRODUCTS;
    }

    @RequestMapping("/{category}")
    public String getProductsByCategory(Model model, @PathVariable("category") String category) {
        model.addAttribute(PRODUCTS, productService.getProductsByCategory(category));
        return PRODUCTS;
    }

    @RequestMapping("/filter/{ByCriteria}")
    public String getProductsByFilter(
            @MatrixVariable(pathVar = "ByCriteria")Map<String, List<String>> filterParams,
            Model model) {
        model.addAttribute(PRODUCTS, productService.getProductsByFilter(filterParams));
        return PRODUCTS;
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }

    @RequestMapping("/{category}/{price}")
    public String filterProducts(@PathVariable String category,
                                 @MatrixVariable(pathVar = "price") Map<String, String> price,
                                 @RequestParam("manufacturer") String manufacturer,
                                 Model model){

        List<Product> productsByCategory = productService.getProductsByCategory(category);
        List<Product> productsByPrice = productService.getProductsByPriceFilter(price);
        List<Product> productsByManufacturer = productService.getProductsByManufacturer(manufacturer);

        Set<Product> productsSet = new HashSet<>();
        productsSet.addAll(productsByCategory);
        productsSet.addAll(productsByPrice);
        productsSet.addAll(productsByManufacturer);

        model.addAttribute(PRODUCTS, productService.getProductsByFilter(productsSet,productsByCategory,productsByPrice,productsByManufacturer));
        return PRODUCTS;
    }

}
