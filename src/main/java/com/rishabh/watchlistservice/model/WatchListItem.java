package com.rishabh.watchlistservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Down to the database — @Entity makes Hibernate build a table from it
//Inside your code — you pass it between service and repository as a normal Java object
//Out to the API — Spring converts it to JSON on the way back
//So one class, three uses. Without it you’d be writing the field list three times:
// once as CREATE TABLE, once as a Java class, once as JSON parsing.
// The annotations let one definition serve all three.

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

    private LocalDateTime AddedAt;

   public String getSymbol(){
       return symbol;
   }
   public void setSymbol(String symbol){
       this.symbol = symbol;
   }
   public void setAddedAt(LocalDateTime x){
       this.AddedAt = x;
   }
   public void setCompanyName(String companyname) {
        this.companyname = companyname;
    }

    public void setTargetPrice(BigDecimal targetprice) {
        this.targetprice = targetprice;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public @Positive(message = "Target Price must be greater than zero") BigDecimal getTargetprice() {
        return targetprice;
    }

    public @Size(max = 100) String getCompanyname() {
        return companyname;
    }

    public String getNotes() {
        return notes;
    }
}
