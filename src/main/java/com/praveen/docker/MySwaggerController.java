package com.praveen.docker;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payments")
@Tag(name = "Payments", description = "Payment processing APIs")
public class MySwaggerController {

    @PostMapping
    @Operation(summary = "Make a payment", description = "Initiates a new payment",
            requestBody = @io.swagger.v3.oas.annotations.parameters.
            RequestBody(description = "Payment request details", required = true,
            content = @Content(schema = @Schema(implementation = PaymentInfo.class))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment successful",
            content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid payment details")
    })
    public ResponseEntity<?> makePayment(
            @Parameter(description = "Payment details", required = true)
            @Valid @RequestBody PaymentInfo paymentInfo) {
        return ResponseEntity.ok(ResponseDTO.builder().data("Payment made").message("success").status(HttpStatus.OK.value()).build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get payment details", description = "Retrieves a payment by ID")
    @ApiResponse(responseCode = "200", description = "Payment details retrieved")
    public ResponseEntity<String> getPayment(
            @Parameter(description = "Payment ID to fetch", required = true)
            @PathVariable String id) {
        return ResponseEntity.ok("Payment details for ID: " + id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update payment", description = "Updates a payment by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment updated"),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    public ResponseEntity<String> updatePayment(
            @Parameter(description = "Payment ID to update") @PathVariable String id,
            @Valid @RequestBody PaymentInfo paymentInfo) {
        return ResponseEntity.ok("Payment updated for ID: " + id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete payment", description = "Deletes a payment by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Payment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    public ResponseEntity<Void> deletePayment(
            @Parameter(description = "ID of the payment to delete") @PathVariable String id) {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update payment status", description = "Partially update payment status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status updated"),
            @ApiResponse(responseCode = "400", description = "Invalid status value")
    })
    public ResponseEntity<String> updatePaymentStatus(
            @PathVariable String id,
            @RequestBody Map<String, String> statusUpdate) {
        String newStatus = statusUpdate.get("status");
        return ResponseEntity.ok("Payment status updated to: " + newStatus);
    }
}



//@RestController
//@RequestMapping("/api/payments")
//@Tag(name = "Payments", description = "Payment processing APIs")
//public class MySwaggerController {
//
//    @Operation(summary = "Make a payment", description = "Processes a new payment",
//            requestBody = @io.swagger.v3.oas.annotations.parameters.
//                    RequestBody(description = "Payment request details", required = true,
//                    content = @Content(schema = @Schema(implementation = PaymentInfo.class))))
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Payment successful"),
//            @ApiResponse(responseCode = "400", description = "Invalid request",
//                    content = @Content(schema = @Schema(hidden = true)))})
//    @PostMapping
//    public ResponseEntity<String> makePayment(@RequestBody PaymentInfo request) {
//        return ResponseEntity.ok("Payment of ₹" + request.getAmount() + " made via " + request.getPaymentBy());
//    }
//
//
//    @Operation(summary = "Get payment details", description = "Retrieves a payment by ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Payment details retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
//            @ApiResponse(responseCode = "404", description = "Payment not found", content = @Content(schema = @Schema(hidden = true)))})
//    @GetMapping("/{id}")
//    public ResponseEntity<String> getPayment(@Parameter(description = "Payment ID to fetch", required = true) @PathVariable String id) {
//        return ResponseEntity.ok("Payment details for ID: " + id);
//    }
//}