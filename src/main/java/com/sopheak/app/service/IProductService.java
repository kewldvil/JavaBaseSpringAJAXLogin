package com.sopheak.app.service;

import java.util.List;

import com.sopheak.app.entities.Product;

public interface IProductService {
	public List<Product> listAllProduct();
	public boolean deleteProductById(int id);
}
