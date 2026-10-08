package model;

public enum Category {
    STANDARD("Standard Room", 100.00),
    DELUXE("Deluxe Suite", 200.00),
    SUITE("Executive Luxury Suite", 350.00);

    private final String displayName;
    private final double pricePerNight;

    Category(String displayName, double pricePerNight) {
        this.displayName = displayName;
        this.pricePerNight = pricePerNight;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
}