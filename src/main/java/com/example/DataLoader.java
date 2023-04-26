package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.products.Product;
import com.example.products.ProductRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        Product p1 = new Product("Pen", (float) 3.52, "AXD2MDD", null);
        Product p2 = new Product("Pen", (float) 3.16, "AFG2MDD", null);
        Product p3 = new Product("box", (float) 7.00, "AXD45DD", "broken");
        Product p4 = new Product("box", (float) 4.50, "AX645DD", "reconditioned");
        Product p5 = new Product("Box", (float) 7.00, "AXD45XX", "ok");
        Product p6 = new Product("book", (float) 12.70, "AXD478D", null);
        Product p7 = new Product("Game", (float) 8.90, "542478D", "broken");

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);
        productRepository.save(p5);
        productRepository.save(p6);
        productRepository.save(p7);

        System.out.println("Dummy data loaded successfully!");
    }
}
