package com.gac.app.backend;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Collection;

import com.gac.app.backend.data.Category;
import com.gac.app.backend.data.Product;
import com.gac.app.backend.data.User;
import com.gac.app.backend.mock.MockDataService;

/**
 * Back-end service interface for retrieving and updating product data.
 */
public abstract class DataService implements Serializable {
	
	public abstract Connection getConnection();
	
	public abstract boolean isUserAutorizated(String username, String password);
	
	public abstract User getUserLoged(String username, String password);

    public abstract Collection<Product> getAllProducts();

    public abstract Collection<Category> getAllCategories();

    public abstract void updateProduct(Product p);

    public abstract void deleteProduct(int productId);

    public abstract Product getProductById(int productId);

    public static DataService get() {
        return MockDataService.getInstance();
    }

}
