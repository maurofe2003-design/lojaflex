package com.parfum.dto;

import java.util.List;

public class OrderDTO {

    public static class CreateOrderRequest {
        private List<OrderItemRequest> items;
        private String paymentMethod;
        private String deliveryAddress;

        public List<OrderItemRequest> getItems() { return items; }
        public void setItems(List<OrderItemRequest> items) { this.items = items; }
        public String getPaymentMethod() { return paymentMethod; }
        public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
        public String getDeliveryAddress() { return deliveryAddress; }
        public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
    }

    public static class OrderItemRequest {
        private Long perfumeId;
        private Integer quantity;

        public Long getPerfumeId() { return perfumeId; }
        public void setPerfumeId(Long perfumeId) { this.perfumeId = perfumeId; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }

    public static class PaymentRequest {
        private Long orderId;
        private String cardNumber;
        private String cardHolder;
        private String expiryDate;
        private String cvv;
        private String paymentMethod; // CREDIT, DEBIT, PIX, BOLETO

        public Long getOrderId() { return orderId; }
        public void setOrderId(Long orderId) { this.orderId = orderId; }
        public String getCardNumber() { return cardNumber; }
        public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
        public String getCardHolder() { return cardHolder; }
        public void setCardHolder(String cardHolder) { this.cardHolder = cardHolder; }
        public String getExpiryDate() { return expiryDate; }
        public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
        public String getCvv() { return cvv; }
        public void setCvv(String cvv) { this.cvv = cvv; }
        public String getPaymentMethod() { return paymentMethod; }
        public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    }
}