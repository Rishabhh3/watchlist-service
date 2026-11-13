package com.rishabh.watchlistservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "watchlist_items")
public class WatchListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Symbol is required")
    @Size(max = 20, message = "Symbol cannot be longer than 20 characters")
    @Column(nullable = false, unique = true)
    private String symbol;

    @Size(max = 100)
    private String companyname;

    @Positive(message = "Target Price must be greater than zero")
    private BigDecimal targetprice;
    // anything representing money is BigDecimal because arithemtic is clean here

    private String notes;

    private LocalDateTime addedate;


}
