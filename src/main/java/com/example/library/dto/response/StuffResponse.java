package com.example.library.dto.response;

import java.time.LocalDate;

import javax.persistence.Enumerated;

import com.example.library.entity.StuffType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuffResponse {
	private Long id;
	private String username;
	private String pinCode;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private LocalDate registeredDate;
	private int borrowedBookNum;
	private String sicilNo;
	@Enumerated
	private StuffType stuffType;
}
