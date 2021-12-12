package com.mycompany.webapp.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {
	
	public CustomUserDetails(
			String mid, 
			String mpassword, 
			Collection<? extends GrantedAuthority> mauthorities) {
		super(mid, mpassword, true, true, true, true, mauthorities);
	}
}
