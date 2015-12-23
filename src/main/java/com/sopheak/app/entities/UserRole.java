package com.sopheak.app.entities;

import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority{
	private static final long serialVersionUID = 1L;
	
	private int roleId;
	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	private String roleName;
	

	@Override
	public String getAuthority() {
		return roleName;
	}

}
