package com.gac.app.backend.test;

import static org.junit.Assert.assertFalse;
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
		
		int count = 0;

		while (result.next()) {
			count += 1;
		}
		
		assertTrue("Numero total de registros en la tabla usuarios 4", (count == 4));
		assertFalse(count < 4 && count > 4);
	}
	
	@Test
	public void testisAutorized() throws Exception {
		boolean acept = false;
		
		Connection conn = service.getConnection();
		assertTrue("La conexion no es nula", (conn != null));
		
		acept = service.isUserAutorizated("Alvaro", "00000");		
		assertTrue("Usuario autorizado", acept);	
		
		acept = service.isUserAutorizated("ALVARO", "00000");		
		assertTrue("Usuario autorizado", acept);	
		
		acept = service.isUserAutorizated("alvaro", "00000");		
		assertTrue("Usuario autorizado", acept);	
		
		acept = service.isUserAutorizated("Joseba", "00000");		
		assertTrue("Usuario autorizado", !acept);	
		
		acept = service.isUserAutorizated("Joaquin", "00000");		
		assertTrue("Usuario autorizado", !acept);
		
		acept = service.isUserAutorizated("Rus", "rus");		
		assertTrue("Usuario autorizado", !acept);	
		
		acept = service.isUserAutorizated("evelyn", "dsqs");		
		assertTrue("Usuario autorizado", !acept);
		
		acept = service.isUserAutorizated("carlos", "00000");		
		assertTrue("Usuario autorizado", acept);	
		
		
	}
}
