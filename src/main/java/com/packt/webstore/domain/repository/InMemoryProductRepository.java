package com.packt.webstore.domain.repository;

import com.packt.webstore.domain.Product;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Kamil
 */
@Repository
public class InMemoryProductRepository implements ProductRepository {

    private List<Product> listOfProducts = new ArrayList<>();

    public InMemoryProductRepository() {

        Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
        iphone.setDescription("Apple iPhone 5s, smartfon z 4-calowym wyświetlaczem o rozdzielczości 640x1136 oraz 8-megapikselowym aparatem");
        iphone.setCategory("Smart Phone");
        iphone.setManufacturer("Apple");
        iphone.setUnitsInStock(1000);

        Product laptopDell = new Product("P1235", "Dell Inspiron", new BigDecimal(700));
        laptopDell.setDescription("Dell Inspiron, 14-calowy laptop (czarny) z procesorem Intel Core 7. generacji");
        laptopDell.setCategory("Laptop");
        laptopDell.setManufacturer("Dell");
        laptopDell.setUnitsInStock(1000);

        Product tabletNexus = new Product("P1236", "Nexus 7", new BigDecimal(300));
        tabletNexus.setDescription("Google Nexus 7 jest najlżejszym 7-caolwym tabletem z 4-rdzeniowym procesorem Qualcomm Snapdragon® S4 Pro");
        tabletNexus.setCategory("Tablet");
        tabletNexus.setManufacturer("Google");
        tabletNexus.setUnitsInStock(1000);

        listOfProducts.add(iphone);
        listOfProducts.add(laptopDell);
        listOfProducts.add(tabletNexus);

    }
    public List<Product> getAllProducts() {
        return listOfProducts;
    }

    public Product getProductById(String productId) {

        Product productById = listOfProducts.stream().filter(product -> product!=null && product.getProductId()!= null && product.getProductId().equals(productId)).findFirst().orElse(null);

        if(productById == null) {
            throw new IllegalArgumentException("Brak produktu o wskazanym id: " + productId);
        }
        return productById;
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> productsByCategory = new ArrayList<>();
        listOfProducts.stream().filter(product -> product.getCategory().equalsIgnoreCase(category)).forEach(productsByCategory::add);
        return productsByCategory;
    }

    @Override
    public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        Set<Product> productsByBrand = new HashSet<>();
        Set<Product> productsByCategory = new HashSet<>();

        Set<String> criterias = filterParams.keySet();

        if(criterias.contains("brand")) {
            for(String brandName : filterParams.get("brand")) {
                for(Product product : listOfProducts) {
                    if(brandName.equalsIgnoreCase(product.getManufacturer())) {
                        productsByBrand.add(product);
                    }
                }
            }
        }

        if(criterias.contains("category")) {
            for(String category : filterParams.get("category")) {
                productsByCategory.addAll(this.getProductsByCategory(category));
            }
        }
        productsByCategory.retainAll(productsByBrand);
        return productsByBrand;
    }

    @Override
    public List<Product> getProductsByManufacturer(String manufacturer) {
        List<Product> productsByManufacturer = new ArrayList<>();
        listOfProducts.stream().filter(product -> product.getManufacturer().equalsIgnoreCase(manufacturer)).forEach(productsByManufacturer::add);
        return productsByManufacturer;
    }

    @Override
    public List<Product> getProductsByPriceFilter(Map<String, String> price) {
        List<Product> productsByPrice = new ArrayList<>();

        Integer low = Integer.parseInt(price.get("low"));
        Integer high = Integer.parseInt(price.get("high"));

        listOfProducts.stream()
                .filter(product ->
                        product.getUnitPrice().compareTo(new BigDecimal(low)) >= 0)
                .filter(product ->
                        product.getUnitPrice().compareTo(new BigDecimal(high)) <=0)
        .forEach(productsByPrice::add);

        return productsByPrice;
    }
}
