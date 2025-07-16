package com.praveen.docker;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payments", description = "Payment processing APIs")
public class MySwaggerController {

    @Operation(summary = "Make a payment", description = "Processes a new payment",
            requestBody = @io.swagger.v3.oas.annotations.parameters.
                    RequestBody(description = "Payment request details", required = true,
                    content = @Content(schema = @Schema(implementation = PaymentInfo.class))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment successful"),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(schema = @Schema(hidden = true)))})
    @PostMapping
    public ResponseEntity<String> makePayment(@RequestBody PaymentInfo request) {
        return ResponseEntity.ok("Payment of ₹" + request.getAmount() + " made via " + request.getPaymentBy());
    }


    @Operation(summary = "Get payment details", description = "Retrieves a payment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment details retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Payment not found", content = @Content(schema = @Schema(hidden = true)))})
    @GetMapping("/{id}")
    public ResponseEntity<String> getPayment(@Parameter(description = "Payment ID to fetch", required = true) @PathVariable String id) {
        return ResponseEntity.ok("Payment details for ID: " + id);
    }
}