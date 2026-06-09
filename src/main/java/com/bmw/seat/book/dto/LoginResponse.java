package com.bmw.seat.book.dto;

public class LoginResponse {

	public boolean success;
	public boolean firstLogin;

	public LoginResponse(boolean success, boolean firstLogin) {
		this.success = success;
		this.firstLogin = firstLogin;
	}

}
