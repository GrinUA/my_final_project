package ua.nure.uvarov.bean;

import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.entity.OrderStatus;
import ua.nure.uvarov.entity.User;

import java.util.Date;

public class OrderBean implements Bean {
    public BookGroup getBookGroup() {
        return bookGroup;
    }

    public void setBookGroup(BookGroup bookGroup) {
        this.bookGroup = bookGroup;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public boolean isPlace() {
        return place;
    }

    public void setPlace(boolean place) {
        this.place = place;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getGuId() {
        return guId;
    }

    public void setGuId(String guId) {
        this.guId = guId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    private User user;
    private BookGroup bookGroup;
    private Date borrowDate;
    private Date expectedDate;
    private Date orderDate;
    private Date closeDate;
    private double penalty;
    private boolean place;
    private OrderStatus status;
    private String guId;
}
