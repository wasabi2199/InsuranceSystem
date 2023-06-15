package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums;

public enum TravelPurpose {
    Work("Work"),
    Recreation("Recreation"),
    Sport("Sport");
    private final String name;

    TravelPurpose(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
