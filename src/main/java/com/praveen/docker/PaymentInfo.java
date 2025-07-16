package com.praveen.docker;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentInfo {

    @Schema(description = "Payment amount", example = "500")
    @NotNull(message = "Amount is required")
    private Integer amount;

    @Schema(description = "Payer's name", example = "John Doe")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Payment description", example = "Online order #123")
    private String description;
}
