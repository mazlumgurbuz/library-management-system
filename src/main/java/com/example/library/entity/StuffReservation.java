package com.example.library.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="stuff_reservation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuffReservation {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate reservedDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dueDate;
	private boolean isActive;
	@ManyToOne
	@JoinColumn(name="stuffId")
	private Stuff stuff;
	@ManyToOne
	@JoinColumn(name="bookId")
	private Book book;
	
}
