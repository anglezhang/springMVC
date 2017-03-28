package com.cyw.mammoth.auth;

import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.util.ByteSource;

public class CustomPasswordService implements PasswordService{

	@Override
	public String encryptPassword(Object paramObject) throws IllegalArgumentException {
		
		return paramObject.toString();
	}

	@Override
	public boolean passwordsMatch(Object plaintext, String saved) {
		return ByteSource.Util.bytes(saved.trim().getBytes()).toBase64().equals(ByteSource.Util.bytes(plaintext).toBase64());
	}

}
