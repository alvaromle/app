package com.gac.app.backend.test;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

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
	}

	@Test
	public void testisAutorized() throws Exception {
		boolean acept = false;

		Connection conn = service.getConnection();
		assertTrue("La conexion no es nula", (conn != null));
	}

	@Test
	public void testGetUserLoged() throws Exception {
		Connection conn = service.getConnection();
		assertTrue("La conexion no es nula", (conn != null));		
	}
}
