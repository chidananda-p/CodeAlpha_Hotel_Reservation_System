package service;

import model.Category;
import model.Reservation;
import model.Room;

import java.io.*;
import java.util.*;

public class FileStorageService {
    private static final String ROOMS_FILE = "rooms.txt";
    private static final String RESERVATIONS_FILE = "reservations.txt";

    public List<Room> loadRooms() {
        List<Room> rooms = new ArrayList<>();
        File file = new File(ROOMS_FILE);

        if (!file.exists()) {
            return initializeDefaultRooms();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    rooms.add(Room.fromCsv(line));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading rooms data: " + e.getMessage());
        }
        return rooms;
    }

    public void saveRooms(List<Room> rooms) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ROOMS_FILE))) {
            for (Room room : rooms) {
                writer.println(room.toCsv());
            }
        } catch (IOException e) {
            System.err.println("Error saving rooms data: " + e.getMessage());
        }
    }

    public List<Reservation> loadReservations() {
        List<Reservation> reservations = new ArrayList<>();
        File file = new File(RESERVATIONS_FILE);

        if (!file.exists()) return reservations;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    reservations.add(Reservation.fromCsv(line));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading reservations data: " + e.getMessage());
        }
        return reservations;
    }

    public void saveReservations(List<Reservation> reservations) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RESERVATIONS_FILE))) {
            for (Reservation reservation : reservations) {
                writer.println(reservation.toCsv());
            }
        } catch (IOException e) {
            System.err.println("Error saving reservations data: " + e.getMessage());
        }
    }

    private List<Room> initializeDefaultRooms() {
        List<Room> defaultRooms = Arrays.asList(
                new Room(101, Category.STANDARD, true),
                new Room(102, Category.STANDARD, true),
                new Room(103, Category.STANDARD, true),
                new Room(104, Category.STANDARD, true),
                new Room(105, Category.STANDARD, true),
                new Room(201, Category.DELUXE, true),
                new Room(202, Category.DELUXE, true),
                new Room(203, Category.DELUXE, true),
                new Room(204, Category.DELUXE, true),
                new Room(205, Category.DELUXE, true),
                new Room(301, Category.SUITE, true),
                new Room(302, Category.SUITE, true),
                new Room(303, Category.SUITE, true),
                new Room(304, Category.SUITE, true),
                new Room(305, Category.SUITE, true)
        );
        saveRooms(defaultRooms);
        return defaultRooms;
    }
}