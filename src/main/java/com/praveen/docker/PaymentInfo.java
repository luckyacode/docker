package com.praveen.docker;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentInfo {
    private int amount;
    private String paymentBy;
    private String timestamp;
}
