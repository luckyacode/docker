package com.praveen.docker;

import lombok.Getter;
import java.time.LocalDate;

interface PaymentStrategy {
    public void pay(PaymentRequest request);
}

//strategy
class CreditCard implements PaymentStrategy {

    @Override
    public void pay(PaymentRequest request) {
        System.out.println("Credit card :" + request.getAmount());
    }
}

class DebitCard implements PaymentStrategy {

    @Override
    public void pay(PaymentRequest request) {
        System.out.println("Dedit card :" + request.getAmount());
    }
}

class UPI implements PaymentStrategy {

    @Override
    public void pay(PaymentRequest request) {
        System.out.println("UPI :" + request.getAmount());
    }
}

//factory
class PaymentFactory {
    public static PaymentStrategy getPaymentStrategy(String type) {
        if (type.equals("debitcard")) return new DebitCard();
        else if (type.equals("creditcard")) return new CreditCard();
        else if (type.equals("upi")) return new UPI();
        else throw new IllegalStateException("invalid payment method");
    }
}

class PaymentService {

    public static void main(String[] args) {
        PaymentRequest paymentRequest = PaymentRequest.builder().paymentBy("creditcard").timestamp(LocalDate.now().toString()).amount(300).build();
        PaymentStrategy factory = PaymentFactory.getPaymentStrategy("debitcard");
        PaymentStrategy factory1 = PaymentFactory.getPaymentStrategy("creditcard");
        PaymentStrategy factory2 = PaymentFactory.getPaymentStrategy("upi");
        factory.pay(paymentRequest);
        factory1.pay(paymentRequest);
        factory2.pay(paymentRequest);
    }
}

//builder pattern
@Getter
class PaymentRequest {
    private int amount;
    private String paymentBy;
    private String timestamp;

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private int amount;
        private String paymentBy;
        private String timestamp;

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder paymentBy(String paymentBy) {
            this.paymentBy = paymentBy;
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public PaymentRequest build() {
            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.amount = this.amount;
            paymentRequest.paymentBy = this.paymentBy;
            paymentRequest.timestamp = timestamp;
            return paymentRequest;
        }
    }
}
