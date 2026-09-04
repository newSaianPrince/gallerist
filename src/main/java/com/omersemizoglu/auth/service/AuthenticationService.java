package com.omersemizoglu.auth.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.omersemizoglu.dto.auth.AuthRequest;
import com.omersemizoglu.dto.auth.AuthResponse;
import com.omersemizoglu.dto.response.DtoUser;
import com.omersemizoglu.dto.auth.RefreshTokenRequest;
import com.omersemizoglu.exception.BaseException;
import com.omersemizoglu.exception.ErrorMessage;
import com.omersemizoglu.exception.MessageType;
import com.omersemizoglu.auth.jwt.JWTService;
import com.omersemizoglu.auth.model.RefreshToken;
import com.omersemizoglu.auth.model.User;
import com.omersemizoglu.auth.repository.RefreshTokenRepository;
import com.omersemizoglu.auth.repository.UserRepository;
import com.omersemizoglu.mapper.UserMapper;

@Service
public class AuthenticationService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserMapper userMapper;
	
	private User createUser(AuthRequest input) {
		User user = new User();
		user.setCreateTime(new Date());
		user.setUsername(input.getUsername());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		
		return user;
	}
	
	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setCreateTime(new Date());
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setUser(user);
		return refreshToken;
	}
	
	public DtoUser register(AuthRequest input) {
		User savedUser = userRepository.save(createUser(input));
		return userMapper.toDto(savedUser);
	}

	public AuthResponse authenticate(AuthRequest input) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword());
			authenticationProvider.authenticate(authenticationToken);
			
			Optional<User> optUser = userRepository.findByUsername(input.getUsername());
			
			String accessToken = jwtService.generateToken(optUser.get());
			RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optUser.get()));
			
			return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
		}
	}
	
	public boolean isValidRefreshToken(Date expiredDate) {
		return new Date().before(expiredDate);
	}

	public AuthResponse refreshToken(RefreshTokenRequest input) {
		Optional<RefreshToken> optRefreshToken = refreshTokenRepository.findByRefreshToken(input.getRefreshToken());
		if(optRefreshToken.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, input.getRefreshToken()));
		}
		
		if(!isValidRefreshToken(optRefreshToken.get().getExpiredDate())) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, input.getRefreshToken()));
		}
		
		User user = optRefreshToken.get().getUser();
		String accessToken = jwtService.generateToken(user);
		RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(user));
		
		return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
	}
}

