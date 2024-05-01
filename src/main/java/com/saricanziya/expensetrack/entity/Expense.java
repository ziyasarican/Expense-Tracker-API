package com.saricanziya.expensetrack.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_expenses")
public class Expense{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "expense_name")
    @NotBlank
    @NotNull(message = "Expense name must not be null")
    @Size(min = 3, message = "Expense name must be at least 3 characters   ")
    private String name;

    private String description;

    @Column(name = "expense_amount")
    @NotNull(message = "Expense amount must not be null")
    @NotBlank
    private BigDecimal amount;

    @NotNull(message = "Expense category must not be null")
    @NotBlank
    private String category;

    @NotNull(message = "Expense date must not be null")
    @NotBlank
    private LocalDate date;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updateddAt;
}
