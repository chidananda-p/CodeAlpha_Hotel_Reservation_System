package service;

import model.Category;
import model.Reservation;
import model.Room;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class HotelService {
    private final List<Room> rooms;
    private final List<Reservation> reservations;
    private final FileStorageService storageService;

    public HotelService() {
        this.storageService = new FileStorageService();
        this.rooms = storageService.loadRooms();
        this.reservations = storageService.loadReservations();
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public List<Room> searchAvailableRooms(Category category) {
        return rooms.stream()
                .filter(Room::isAvailable)
                .filter(room -> category == null || room.getCategory() == category)
                .collect(Collectors.toList());
    }

    public Reservation bookRoom(String guestName, int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        Room roomToBook = rooms.stream()
                .filter(r -> r.getRoomNumber() == roomNumber && r.isAvailable())
                .findFirst()
                .orElse(null);

        if (roomToBook == null) {
            return null;
        }

        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (nights <= 0) {
            throw new IllegalArgumentException("Check-out date must be after check-in date.");
        }

        double totalAmount = nights * roomToBook.getCategory().getPricePerNight();
        boolean paymentSuccess = processPaymentSimulation(totalAmount);

        if (!paymentSuccess) {
            System.out.println("❌ Payment failed. Booking canceled.");
            return null;
        }

        String bookingId = "RES-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Reservation reservation = new Reservation(bookingId, guestName, roomNumber, roomToBook.getCategory(), checkIn, checkOut, totalAmount, true);

        roomToBook.setAvailable(false);
        reservations.add(reservation);

        storageService.saveRooms(rooms);
        storageService.saveReservations(reservations);

        return reservation;
    }

    public boolean cancelReservation(String bookingId) {
        Reservation resToCancel = reservations.stream()
                .filter(r -> r.getBookingId().equalsIgnoreCase(bookingId))
                .findFirst()
                .orElse(null);

        if (resToCancel == null) {
            return false;
        }

        reservations.remove(resToCancel);

        rooms.stream()
                .filter(r -> r.getRoomNumber() == resToCancel.getRoomNumber())
                .findFirst()
                .ifPresent(r -> r.setAvailable(true));

        storageService.saveRooms(rooms);
        storageService.saveReservations(reservations);
        return true;
    }

    public Reservation getReservationDetails(String bookingId) {
        return reservations.stream()
                .filter(r -> r.getBookingId().equalsIgnoreCase(bookingId))
                .findFirst()
                .orElse(null);
    }

    private boolean processPaymentSimulation(double amount) {
        System.out.printf("\n[Payment Gateway] Processing transaction of ₹%.2f...\n", amount);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
        System.out.println("[Payment Gateway] Transaction Approved!");
        return true;
    }
}