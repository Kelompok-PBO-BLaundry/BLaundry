/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

public class Order {
    private int orderId;
    private Date orderDate;
    private String orderStatus;
    private String serviceType;
    private double totalWeight;
    private double totalItem;
    private String Duration;
    private double totalAmount;
    private String deliveryAddress;
    private int customerId;
    private String customerName;

    public Order() {
    }

    public Order(Date orderDate, String orderStatus, String serviceType, double totalWeight, double totalItem, String Duration, double totalAmount, String deliveryAddress, int customerId) {
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.serviceType = serviceType;
        this.totalWeight = totalWeight;
        this.totalItem = totalItem;
        this.Duration = Duration;
        this.totalAmount = totalAmount;
        this.deliveryAddress = deliveryAddress;
        this.customerId = customerId;
    }

    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    
    public void setTotalWeight(double totalWeight){
        this.totalWeight = totalWeight;
    }
    
    public double gettTotalWeight(){
        return totalWeight;
    }
    
    public void setTotalItem(double totalItem){
        this.totalItem = totalItem;
    }
    
    public double gettTotalItem(){
        return totalItem;
    }
    
    public void setDuration(String Duration){
        this.Duration = Duration;
    }
    
    public String getDuration(){
        return Duration;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
