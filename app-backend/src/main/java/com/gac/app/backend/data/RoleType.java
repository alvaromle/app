package com.gac.app.backend.data;

public enum RoleType {

	USUARIO("Usuario"), TECNICO("Tecnico"), ADMIN("Admin");

	private final String rol;

	private RoleType(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return this.name();
	}
}