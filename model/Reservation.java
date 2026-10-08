package model;

import java.time.LocalDate;

public class Reservation {
    private final String bookingId;
    private final String guestName;
    private final int roomNumber;
    private final Category category;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final double totalAmount;
    private final boolean paymentStatus;

    public Reservation(String bookingId, String guestName, int roomNumber, Category category,
                       LocalDate checkInDate, LocalDate checkOutDate, double totalAmount, boolean paymentStatus) {
        this.bookingId = bookingId;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.category = category;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
    }

    public String getBookingId() { return bookingId; }
    public String getGuestName() { return guestName; }
    public int getRoomNumber() { return roomNumber; }
    public Category getCategory() { return category; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public double getTotalAmount() { return totalAmount; }
    public boolean isPaymentStatus() { return paymentStatus; }

    public String toCsv() {
        return String.format("%s,%s,%d,%s,%s,%s,%.2f,%b",
                bookingId, guestName, roomNumber, category.name(),
                checkInDate, checkOutDate, totalAmount, paymentStatus);
    }

    public static Reservation fromCsv(String line) {
        String[] parts = line.split(",");
        return new Reservation(
                parts[0],
                parts[1],
                Integer.parseInt(parts[2]),
                Category.valueOf(parts[3]),
                LocalDate.parse(parts[4]),
                LocalDate.parse(parts[5]),
                Double.parseDouble(parts[6]),
                Boolean.parseBoolean(parts[7])
        );
    }

    @Override
    public String toString() {
        return String.format("======================================\n" +
                        " BOOKING CONFIRMATION - ID: %s\n" +
                        "======================================\n" +
                        " Guest Name   : %s\n" +
                        " Room Number  : %d (%s)\n" +
                        " Check-in     : %s\n" +
                        " Check-out    : %s\n" +
                        " Total Paid   : $%.2f\n" +
                        " Payment      : %s\n" +
                        "======================================",
                bookingId, guestName, roomNumber, category.getDisplayName(),
                checkInDate, checkOutDate, totalAmount, paymentStatus ? "SUCCESSFUL" : "PENDING");
    }
}