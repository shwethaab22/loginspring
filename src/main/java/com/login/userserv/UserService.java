package com.login.userserv;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.login.entity.user;
import com.login.userRepo.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JavaMailSender mailsender;
    
    public user getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public String otp(String toEmail)
    {
    	SimpleMailMessage message=new SimpleMailMessage();
    	message.setTo(toEmail);
		Random rand=new Random();
		int x=111111;
		int y=999999;
		int z=rand.nextInt(y-x+1)+x;
		message.setText(String.valueOf(z));
		message.setSubject("Otp for your Login process");
		message.setFrom("carswayinventory123@gmail.com");
		mailsender.send(message);
		String ret = String.valueOf(z);
		return ret;
    }
    
    public user loginvalidation(String email,String password)
    {
    	return userRepository.loginvalidation(email, password);
    }
    
    
    
    // Other methods for user management
}
