package com.example.products;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

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
        System.out.println(productRepository.findAll());
        for(Product product: productRepository.findAll()) {
            products.add(product);
        }
        return products;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> find(@PathVariable int id) throws Exception {
    	    Product product = this.productRepository.findById(id)
    	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    	    return ResponseEntity.ok(product);
    }

    @PostMapping("/products")
    public Product save(@RequestBody Product product) {
        return this.productRepository.save(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (!this.productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        this.productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products/inventory")
    public ProductService.Inventory getInventory() {
        return this.productService.getProductsData();
    }
    
    /**
     * it is perhaps better to use a controller advice and AOP techniques, but for this small project's purposes, this will suffice.
     * @param ex ResponseStatusException
     * @return ResponseEntity
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(ex.getMessage());
    }
}
