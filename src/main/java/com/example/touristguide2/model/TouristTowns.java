package com.example.touristguide2.model;
public enum TouristTowns {
    KOBENHAVN("Kobenhavn"),
    AARHUS("Aarhus"),
    ODENSE("Odense"),
    AALBORG("Aalborg"),
    ESBJERG("Esbjerg"),
    RANDERS("Randers"),
    KOLDING("Kolding"),
    HORSENS("Horsens"),
    VEJLE("Vejle"),
    ROSKILDE("Roskilde"),
    BORNHOLM("Bornholm"),
    HERNING("Herning"),
    HELSINGOR("Helsingør"),
    NAESTVED("Næstved"),
    SILKEBORG("Silkeborg"),
    FREDERICIA("Fredericia"),
    SOENDERBORG("Sønderborg"); // Fix: Brug "SOENDERBORG" i stedet for "SØNDERBORG"

    private final String displayName;

    TouristTowns(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static TouristTowns fromDisplayName(String displayName) {
        for (TouristTowns town : TouristTowns.values()) {
            if (town.getDisplayName().equalsIgnoreCase(displayName)) {
                return town;
            }
        }
        throw new IllegalArgumentException("No enum constant for displayName: " + displayName);
    }
}
