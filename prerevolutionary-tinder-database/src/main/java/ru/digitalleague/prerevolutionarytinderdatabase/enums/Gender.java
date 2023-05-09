package ru.digitalleague.prerevolutionarytinderdatabase.enums;

import java.util.List;

public enum Gender {
    MALE {
        @Override
        public String getRussianName() {
            return "Сударь";
        }

        @Override
        public List<String> getPartnersOrientations() {
            return List.of(Orientation.MALE_SEARCH.name(), Orientation.ALL_SEARCH.name());
        }
    },
    FEMALE {
        @Override
        public String getRussianName() {
            return "Сударыня";
        }

        @Override
        public List<String> getPartnersOrientations() {
            return List.of(Orientation.FEMALE_SEARCH.name(), Orientation.ALL_SEARCH.name());
        }
    };

    public abstract String getRussianName();
    public abstract List<String> getPartnersOrientations();
}
