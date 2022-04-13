package com.example.library.dto.response;

import java.time.LocalDate;

import com.example.library.entity.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationResponse {

    private Long id;
    private Book book;
    private LocalDate reservedDate;
    private LocalDate dueDate;

}
