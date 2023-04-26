package com.example.products;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.Data;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Inventory getProductsData() {
		Inventory inventory = new Inventory();
		for (Product product : productRepository.findAll()) {
			if (inventory.containsKey(product.getName().toLowerCase())) {
				InventoryItem productItem = inventory.get(product.getName().toLowerCase());
				if (!Objects.equals(product.getState(), "broken")) {
					productItem.setQty(productItem.getQty() + 1);
					productItem.setTotalPrice(productItem.getTotalPrice() + product.getPrice());
					productItem.productBarcodes += "," + product.getBarcode();
				}
			} else {
				if (!Objects.equals(product.getState(), "broken")) {
					inventory.put(product.getName().toLowerCase(), new InventoryItem(product.getName().toLowerCase(),
							product.getPrice(), 1, product.getBarcode()));
				}
			}
		}
		return inventory;
	}

	public static class Inventory extends HashMap<String, InventoryItem> {
	}

	@Data
	public static class InventoryItem {
		private final String pName;
		private int qty;
		private float totalPrice;
		private String productBarcodes;

		public InventoryItem(String pName, float totalPrice, int qty, String productBarcodes) {
			this.pName = pName;
			this.totalPrice = totalPrice;
			this.qty = qty;
			this.productBarcodes = productBarcodes;
		}

	}
}
