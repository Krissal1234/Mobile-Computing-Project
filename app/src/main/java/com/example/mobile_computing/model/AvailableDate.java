package com.example.mobile_computing.model;

import java.util.Date;
/**
 * The AvailableDate class represents a date range indicating the start and end dates of availability.
 */
public class AvailableDate {
    private Date startDate;
    private Date endDate;

    public AvailableDate(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
