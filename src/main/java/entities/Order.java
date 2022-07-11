package entities;

import java.sql.Date;

public class Order {

    private long id;
    private long customerId;
    private Date dateOrdered;
    private Date dateDelivered;
    private String status;
    private String targetAddress;
    private String paymentChoice;
    private Date preferredTime;
    private String comment;
    private int totalPrice;

    public Order() {
        throw new NullPointerException("Order is null");
    }

    public Order(long customerId, Date dateOrdered, String status, String targetAddress, String paymentChoice, Date preferredTime, String comment, int totalPrice) {
        setCustomerId(customerId);
        setDateOrdered(dateOrdered);
        setStatus(status);
        setTargetAddress(targetAddress);
        setPaymentChoice(paymentChoice);
        setPreferredTime(preferredTime);
        setComment(comment);
        setTotalPrice(totalPrice);
    }

    public Order(long customerId, Date dateOrdered, Date dateDelivered, String status, String targetAddress, String paymentChoice, Date preferredTime, String comment, int totalPrice) {
        setCustomerId(customerId);
        setDateOrdered(dateOrdered);
        setDateDelivered(dateDelivered);
        setStatus(status);
        setTargetAddress(targetAddress);
        setPaymentChoice(paymentChoice);
        setPreferredTime(preferredTime);
        setComment(comment);
        setTotalPrice(totalPrice);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Date getDateDelivered() {
        return dateDelivered;
    }

    public void setDateDelivered(Date dateDelivered) {
        this.dateDelivered = dateDelivered;
    }

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Date dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getPaymentChoice() {
        return paymentChoice;
    }

    public void setPaymentChoice(String paymentChoice) {
        this.paymentChoice = paymentChoice;
    }

    public Date getPreferredTime() {
        return preferredTime;
    }

    public void setPreferredTime(Date preferredTime) {
        this.preferredTime = preferredTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
