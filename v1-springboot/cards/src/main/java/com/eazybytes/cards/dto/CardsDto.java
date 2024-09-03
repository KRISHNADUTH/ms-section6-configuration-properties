package com.eazybytes.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsDto {

    @NotEmpty(message = "Mobile number cannot be empty or null")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile n. must be 10 digits.")
    private String mobileNumber;

    @NotEmpty(message = "Card number cannot be empty or null")
    @Pattern(regexp = "(^$|[0-9]{12})",message = "Card number must be 12 digits")
    private String cardNumber;

    @NotEmpty(message = "Card Type cannot be empty or null")
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    private int totalLimit;

    @PositiveOrZero(message = "Total amount used should be greater than or equal to zero.")
    private int amountUsed;

    @PositiveOrZero(message = "Total available amount should be greater than or equal to zero")
    private int availableAmount;
}
