package com.example.library.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="books")
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Book {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message="name can not be null")
	@NotBlank(message="name can not be blank")
	private String  isbn;
	private String author;
	private String category;
	private String publisher;

	private String title;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate pressDate;

	@Enumerated
	private BookType bookType;
	private boolean isBorrowed;
	private boolean isReserved;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "book")
	List<StudentBorrow> studentBorrows;
	
	@JsonIgnore
	@OneToMany(mappedBy = "book")
	List<StudentReservation> studentReservations;
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(id, other.id);
	}
}
