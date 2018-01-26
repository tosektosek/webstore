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
        model.addAttribute(products, productService.getProductsByCategory(category));
        return products;
    }

    @RequestMapping("/filter/{ByCriteria}")
    public String getProductsByFilter(
            @MatrixVariable(pathVar = "ByCriteria")Map<String, List<String>> filterParams,
            Model model) {
        model.addAttribute("products", productService.getProductsByFilter(filterParams));
        return products;
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

        model.addAttribute("products", productService.getProductsByFilter(productsSet,productsByCategory,productsByPrice,productsByManufacturer));
        return products;
    }

}
