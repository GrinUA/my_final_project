package ua.nure.uvarov.bean;

import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.entity.OrderStatus;

import java.util.Date;

public class OrderBean implements Bean {
    private int bookId;
    private BookGroup bookGroup;
    private Date expectedDate;
    private double penalty;
    private boolean place;
    private OrderStatus status;
    private String guId;
}
