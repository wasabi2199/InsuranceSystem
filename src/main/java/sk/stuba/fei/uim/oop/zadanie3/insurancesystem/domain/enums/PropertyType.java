package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums;

public enum PropertyType {
    Flat("Flat"),
    BrickHouse("Brick House"),
    WoodenHouse("Wooden House");

    private final String name;

    PropertyType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
