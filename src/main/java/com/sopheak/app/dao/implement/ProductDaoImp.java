package com.sopheak.app.dao.implement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sopheak.app.dao.IProductDao;
import com.sopheak.app.entities.Product;


@Repository
public class ProductDaoImp implements IProductDao {


	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> list() {
		Session session = sessionFactory.getCurrentSession();
		List<Product> productList = session.createQuery("from product").list();
		return productList;
	}

	@Override
	public Boolean Delete(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Product pro = (Product) session.load(Product.class,new Integer(id));
		if(pro !=null) session.delete(pro);
		return true;
	}

}
