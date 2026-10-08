package model;

public class Room {
    private final int roomNumber;
    private final Category category;
    private boolean isAvailable;

    public Room(int roomNumber, Category category, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String toCsv() {
        return roomNumber + "," + category.name() + "," + isAvailable;
    }

    public static Room fromCsv(String line) {
        String[] parts = line.split(",");
        int roomNumber = Integer.parseInt(parts[0]);
        Category category = Category.valueOf(parts[1]);
        boolean isAvailable = Boolean.parseBoolean(parts[2]);
        return new Room(roomNumber, category, isAvailable);
    }

    @Override
    public String toString() {
        return String.format("Room #%d | Category: %-15s | Price/Night: Rs.%.2f | Status: %s",
                roomNumber, category.getDisplayName(), category.getPricePerNight(),
                isAvailable ? "Available" : "Occupied");
    }
}