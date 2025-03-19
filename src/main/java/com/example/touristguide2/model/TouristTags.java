package com.example.touristguide2.model;

    public enum TouristTags {
        FORLYSTELSER("Forlystelser"),
        HISTORIE("Historie"),
        UNDERHOLDNING("Underholdning"),
        GUIDED_TUR("GUIDED_TUR"),
        KULTUR("Kultur"),
        SEJLTUR("Sejltur"),
        STATUE("Statue"),
        MILJOE_VENLIG("MILJOE_VENLIG"),
        BYVANDRING("Byvandring"),
        HAVN("HAVN"),
        ALTERNATIV("Alternativ"),
        KREATIV("Kreativ"),
        SAMFUND("Samfund"),
        KONGELIG("Kongelig"),
        MUSEUM("Museum"),
        SLOT("Slot"),
        CAFÉER("Caféer");




        private final String displayName;

        TouristTags(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
        public static TouristTags fromDisplayName(String displayName) {
            for (TouristTags tag : TouristTags.values()) {
                if (tag.displayName.equalsIgnoreCase(displayName)) {
                    return tag;
                }
            }
            throw new IllegalArgumentException("Ukendt tag: " + displayName);
        }
    }

