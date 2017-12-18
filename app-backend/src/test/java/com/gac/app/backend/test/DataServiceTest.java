package com.gac.app.backend.test;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;

import com.gac.app.backend.DataService;
import com.gac.app.backend.mock.MockDataService;

/**
 * Simple unit test for the back-end data service.
 */
public class DataServiceTest {

	private DataService service;

	@Before
	public void setUp() throws Exception {
		service = MockDataService.getInstance();
	}

	@Test
	public void testConnection() throws Exception {
		Connection conn = service.getConnection();
		assertTrue("La conexion no es nula", (conn != null));
	}

	@Test
	public void testGetData() throws Exception {
		Connection conn = service.getConnection();
		assertTrue("La conexion no es nula", (conn != null));

		PreparedStatement ps = conn.prepareStatement("Select * from usuarios");
		ResultSet result = ps.executeQuery();

		while (result.next()) {
			System.out.println(" ----------------------- ");
			System.out.println("Nombre: " + result.getString("Nombre"));
			System.out.println("Password: " + result.getString("Password"));
		}

	}
}
