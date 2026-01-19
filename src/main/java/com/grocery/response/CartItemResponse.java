package com.grocery.response;

public class CartItemResponse {

    private Long productId;

    private String productName;

    private int quantity;

    private double unitPrice;

    private double lineTotal;

    public CartItemResponse(Long productId,

                            String productName,

                            int quantity,

                            double unitPrice,

                            double lineTotal) {

        this.productId = productId;

        this.productName = productName;

        this.quantity = quantity;

        this.unitPrice = unitPrice;

        this.lineTotal = lineTotal;

    }

    public Long getProductId() {

        return productId;

    }

    public String getProductName() {

        return productName;

    }

    public int getQuantity() {

        return quantity;

    }

    public double getUnitPrice() {

        return unitPrice;

    }

    public double getLineTotal() {

        return lineTotal;

    }

}