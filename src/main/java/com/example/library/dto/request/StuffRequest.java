package com.example.library.dto.request;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.example.library.entity.StuffType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuffRequest {
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
	private String sicilNo;
	@Enumerated
	private StuffType stuffType;
}
