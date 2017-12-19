package com.gac.app.backend.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;

import com.gac.app.backend.DataService;
import com.gac.app.backend.data.User;
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
		assertTrue("Usuario no autorizado", !acept);

		acept = service.isUserAutorizated("Joaquin", "00000");
		assertTrue("Usuario no autorizado", !acept);

		acept = service.isUserAutorizated("Rus", "rus");
		assertTrue("Usuario no autorizado", !acept);

		acept = service.isUserAutorizated("evelyn", "dsqs");
		assertTrue("Usuario no autorizado", !acept);

		acept = service.isUserAutorizated("carlos", "00000");
		assertTrue("Usuario autorizado", acept);
	}

	@Test
	public void testGetUserLoged() throws Exception {

		Connection conn = service.getConnection();
		assertTrue("La conexion no es nula", (conn != null));

		boolean acept = service.isUserAutorizated("Alvaro", "00000");
		assertTrue("Usuario autorizado", acept);

		String usuario = "Alvaro";
		String pass = "00000";
		User user = service.getUserLoged(usuario, pass);
		assertTrue("El objecto usuario no es null", (user != null));
		assertTrue("El nombre tiene que ser igual", user.getUser().toUpperCase().compareTo(usuario.toUpperCase()) == 0);
		assertTrue("El pass tiene que ser igual", user.getPassword().toUpperCase().compareTo(pass.toUpperCase()) == 0);
		
		String user1 = "Jose";
		String pass1 = "00000";
		User obj1 = service.getUserLoged(user1, pass1);
		assertTrue("El objecto obj1 no es null", (obj1 != null));
		assertTrue("El nombre tiene que ser igual", obj1.getUser().toUpperCase().compareTo(user1.toUpperCase()) == 0);
		assertTrue("El pass tiene que ser igual", obj1.getPassword().toUpperCase().compareTo(pass1.toUpperCase()) == 0);
		
		String user2 = "carlos";
		String pass2 = "00000";
		User obj2 = service.getUserLoged(user2, pass2);
		assertTrue("El objecto obj1 no es null", (obj2 != null));
		assertTrue("El nombre tiene que ser igual", obj2.getUser().toUpperCase().compareTo(user2.toUpperCase()) == 0);
		assertTrue("El pass tiene que ser igual", obj2.getPassword().toUpperCase().compareTo(pass2.toUpperCase()) == 0);
		
		String user3 = "ruso";
		String pass3 = "ruso";
		User obj3 = service.getUserLoged(user3, pass3);
		assertNull("El usuario no existe. El objeto tiene que ser nulo", obj3);
		
		String user4 = "aaaa";
		String pass4 = "";
		User obj4 = service.getUserLoged(user4, pass4);
		assertNull("El usuario no existe. El objeto tiene que ser nulo", obj4);
		
		String user5 = "";
		String pass5 = "";
		User obj5 = service.getUserLoged(user5, pass5);
		assertNull("El usuario no existe. El objeto tiene que ser nulo", obj5);
		
	}
}
