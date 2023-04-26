package com.example.products;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.Data;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Inventory getProductsData() {
        Inventory inventory = new Inventory();
        for(Product p: productRepository.findAll()) {
            if (inventory.containsKey(p.getName())) {
                InventoryItem productItem = inventory.get(p.getName());
                if (!p.getState().equals("broken")) {
                    productItem.setQty(productItem.getQty() + 1);
                    productItem.setTotalPrice(productItem.getTotalPrice() + p.getPrice());
                    productItem.productBarcodes += "," + p.getBarcode();
                }
            }
            else {
                inventory.put(p.getName(), new InventoryItem(p.getName().toLowerCase(), p.getPrice(), 1));
            }
        }
        return inventory;
    }

    public static class Inventory extends HashMap<String, InventoryItem> { }

    @Data
    public static class InventoryItem {
        private final String pName;
        private int qty;
        private float totalPrice;
        private String productBarcodes;

        public InventoryItem(String pName, float totalPrice, int qty) {
            this.pName = pName;
            this.totalPrice = totalPrice;
            this.qty = qty;
        }

        // tous les getters and setters sont implémentés
    }
}
