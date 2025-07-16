package com.praveen.docker;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentInfo {
    private Integer amount;
    private String paymentBy;
    private String timestamp;
}
