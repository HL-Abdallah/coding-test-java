package com.example.products;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

    public Product(String nom, float price, String barcode, String state) {
    	super();
		this.nom = nom;
		this.price = price;
		this.barcode = barcode;
		this.state = state;
	}
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private float price;
    private String barcode;
    @Nullable private String state;

}