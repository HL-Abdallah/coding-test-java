package com.example.products;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class ProductController {

    private final ProductRepository productRepository;

    private final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        for(Product product: productRepository.findAll()) {
            products.add(product);
        }
        return products;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> find(@PathVariable int id) throws Exception {
        return ResponseEntity.ok(this.productRepository.findById(id).orElseThrow(() -> new Exception("Product not found")));
    }

    @PostMapping("/products/")
    public Product save(@RequestBody Product product) {
        return this.productRepository.save(product);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable int id) {
        this.productRepository.deleteById(id);
    }

    @GetMapping("/products/inventory")
    public ProductService.Inventory getInventory() {
        return this.productService.getProductsData();
    }
}
