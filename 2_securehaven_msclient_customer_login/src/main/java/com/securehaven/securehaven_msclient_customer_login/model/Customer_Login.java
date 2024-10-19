package com.securehaven.securehaven_msclient_customer_login.model;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer_Login {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
		
		@NotEmpty
	    private String firstName;
		 
	    @NotEmpty
	    private String middleName;
	    
	    @NotEmpty
	    private String lastName;
	    
	    @NotNull
	    @Min(value = 18, message = "{validation.age.min}")
	    private int age;
	    
	    @NotEmpty
	    private String gender;
	    
	    @NotEmpty
	    private String dob;
	    
	    @NotEmpty
	    @Pattern(regexp = "^[6789][0-9]{9}$", message = "{vaidation.mobileNo.min}")
	    private String mobileNo;
	    
	    @NotEmpty
	    @Email(message = "vaildation.email.min")
	    private String email;
	    
	    @NotEmpty
	    @Pattern(regexp = "^[A-Za-z0-9$#@]{6,10}$",message = "{vaildation.username.min}")
	    private String username;
	    
	    @NotEmpty
	    @Pattern(regexp = "^[a-zA-Z0-9@#$]{5,8}$", message = "{vaildation.password.min}")
	    private String password;
	    
	  
	    @Transient
	    private int otp;
	    
//	    private String randompassword;
	

}
