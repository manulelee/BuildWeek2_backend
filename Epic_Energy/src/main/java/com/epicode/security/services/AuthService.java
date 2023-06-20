package com.epicode.security.services;

import com.epicode.security.payloads.LoginDto;
import com.epicode.security.payloads.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
