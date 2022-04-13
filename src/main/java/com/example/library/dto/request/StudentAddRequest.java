package com.example.library.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAddRequest {
	
	@NotBlank
	private String faculty;
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String pinCode;
	@NotBlank
	private String name;
	@NotBlank
	private String surname;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Pattern(regexp ="[0-9\\s]{11}")
	private String phone;
	private boolean isActive;
	private int borrowedBookNum;
//	@NotBlank
//	private LocalDate registeredDate = LocalDate.now();
}
