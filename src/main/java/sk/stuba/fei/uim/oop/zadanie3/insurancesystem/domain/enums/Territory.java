package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain.enums;

public enum Territory {
    Slovakia("Slovakia"),
    World("World"),
    SlovakiaAndWorld("Slovakia+World");

    private final String name;

    Territory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
