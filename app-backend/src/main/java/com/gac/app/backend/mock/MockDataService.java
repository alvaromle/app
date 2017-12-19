package com.gac.app.backend.mock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gac.app.backend.DataService;
import com.gac.app.backend.data.Category;
import com.gac.app.backend.data.Product;

/**
 * Mock data model. This implementation has very simplistic locking and does not
 * notify users of modifications.
 */
public class MockDataService extends DataService {

	private static MockDataService INSTANCE;

	private List<Product> products;
	private List<Category> categories;
	private int nextProductId = 0;

	private Connection conn = null;
	private final String url = "jdbc:mysql://localhost:3306/pruebabd?autoReconnect=true&useSSL=false";
	private final String username = "root";
	private final String password = "root";

	private MockDataService() {
		categories = MockDataGenerator.createCategories();
		products = MockDataGenerator.createProducts(categories);
		nextProductId = products.size() + 1;
	}

	public synchronized static DataService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MockDataService();
		}
		return INSTANCE;
	}

	@Override
	public Connection getConnection() {

		if (conn == null) {
			System.out.println("Connecting database... " + url);
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver loaded!");
				conn = DriverManager.getConnection(url, username, password);
				System.out.println("Database connected!");
			} catch (SQLException ex) {
				throw new IllegalStateException("Cannot connect the database!", ex);
			} catch (ClassNotFoundException ex) {
				throw new IllegalStateException("Cannot find the driver in the classpath!", ex);
			}
		}
		return conn;
	}

	@Override
	public boolean isUserAutorizated(String username, String password) {

		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			String query = "Select * from Usuarios where UPPER(Nombre)=? and UPPER(Password)=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, username.toUpperCase());
			ps.setString(2, password.toUpperCase());
			result = ps.executeQuery();
			
			if (result.next()) 
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				result.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public synchronized List<Product> getAllProducts() {
		return products;
	}

	@Override
	public synchronized List<Category> getAllCategories() {
		return categories;
	}

	@Override
	public synchronized void updateProduct(Product p) {
		if (p.getId() < 0) {
			// New product
			p.setId(nextProductId++);
			products.add(p);
			return;
		}
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == p.getId()) {
				products.set(i, p);
				return;
			}
		}

		throw new IllegalArgumentException("No product with id " + p.getId() + " found");
	}

	@Override
	public synchronized Product getProductById(int productId) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == productId) {
				return products.get(i);
			}
		}
		return null;
	}

	@Override
	public synchronized void deleteProduct(int productId) {
		Product p = getProductById(productId);
		if (p == null) {
			throw new IllegalArgumentException("Product with id " + productId + " not found");
		}
		products.remove(p);
	}
}
