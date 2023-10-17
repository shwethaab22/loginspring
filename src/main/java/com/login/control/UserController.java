package com.login.control;

import org.apache.catalina.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.entity.user;
import com.login.userRepo.UserRepository;
import com.login.userserv.UserService;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    static String otp="";
    
    @GetMapping("/forgot/{mail}")
    public String getuser(@PathVariable String mail) {
    	//String.valueOf(userService.otp(mail));
    			
    	
//    	if(otp!="")
//    	{
//    		return userService.otp(mail);
//    	}
//    	else
//    	{
//    		return JSONObject.quote("otp not sent");
//    	}
    	System.out.println("hello");
    	return userService.otp(mail);

    }
    
    @GetMapping("/getotpdb")
    public String getotpbd()
    {
    	return String.valueOf(otp);
    }
    
    @GetMapping("/login/{email}/{password}")
    public String login(@PathVariable("email") String email,@PathVariable("password") String password) {
    	
    	if(userService.loginvalidation(email, password)!=null)
    	{
    		return JSONObject.quote("Login Successfull");
    	}else
    	{
//    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    		return JSONObject.quote("Login Failed");


    	}
    	
        // Validate user credentials
//        user userr = userService.getUserByEmail(loginRequest.getEmail());
//        if (userr != null && userr.getPassword().equals(loginRequest.getPassword())) {
//            // Authentication successful
//            return ResponseEntity.ok("Login successful");
//        } else {
//            // Authentication failed
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
//        }
    }
    
    @PostMapping("/signup")
    public user signup(@RequestBody user loginRequest) {
    	return userRepository.save(loginRequest);
    }
    
    @PutMapping("/updatepassword/{email}/{password}")
    public String passwordupdate(@PathVariable("email") String email,@PathVariable("password") String password)
    {
    	user user1=userRepository.findByEmail(email);
    	user1.setPassword(password);
    	userRepository.save(user1);
    	return JSONObject.quote("password updated");
    }
    
    
    
}

