package com.msinuk.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.msinuk.main.model.LoginResponse;
import com.msinuk.main.model.User;
import com.msinuk.main.model.UserDTO;
import com.msinuk.main.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private PasswordEncoder encoder;


	public LoginResponse addUser(String user2) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
	    mapper.enable(SerializationFeature.INDENT_OUTPUT);
		UserDTO userdto = mapper.readValue(user2, UserDTO.class);
		User user = new User(userdto.getId(), userdto.getFirstName(), userdto.getLastName(), 
				userdto.getUserName(), this.encoder.encode(userdto.getPassword()),
				userdto.getLastVisited(), userdto.getWishlist());
		User user1 = this.repo.findByUserName(user.getUserName());
		if(user1 != null) {
			return new LoginResponse("UserName Already exits", false, null);
		}
		User newUser= this.repo.save(user);
		if(newUser!=null) {
			return new LoginResponse("User Resgistration Successful", true, newUser);
		}
		else {
			return new LoginResponse("User Resgistration Failed", false, null);
		}
	}


	public LoginResponse getUser(String username, String password) {
        User user = this.repo.findByUserName(username);
        if (user != null) {
            String encodedPassword = user.getPassword();
            Boolean isPwdRight = encoder.matches(password, encodedPassword);
            if (isPwdRight) {
                return new LoginResponse("Login Success", true, user);
                
            } else {
                return new LoginResponse("password Not Match", false, null);
            }
        }else {
            return new LoginResponse("UserName not exits", false, null);
        }
	}


	public LoginResponse updateUser(String user) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
	    mapper.enable(SerializationFeature.INDENT_OUTPUT);
		UserDTO userdto = mapper.readValue(user, UserDTO.class);
		User updateUser = new User(userdto.getId(), userdto.getFirstName(), userdto.getLastName(), 
				userdto.getUserName(), this.encoder.encode(userdto.getPassword()),
				userdto.getLastVisited(), userdto.getWishlist());
		User checkuser = this.repo.findByUserName(updateUser.getUserName());
		if(checkuser == null) {
			return new LoginResponse("UserName does not exits", false, null);
		}
		this.repo.setUserInfoById(updateUser.getLastVisited(),updateUser.getWishlist(),updateUser.getId());
			return new LoginResponse("User Update Successful", true, null);

	}
	
}
