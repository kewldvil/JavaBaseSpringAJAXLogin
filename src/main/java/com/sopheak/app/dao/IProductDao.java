package com.sopheak.app.dao;

import java.util.List;

import com.sopheak.app.entities.Product;

public interface IProductDao {
	public List<Product> list();
	public Boolean Delete(int id);
}
