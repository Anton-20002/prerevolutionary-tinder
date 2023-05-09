package ru.digitalleague.prerevolutionarytinderdatabase.enums;

import java.util.List;

public enum Orientation {
    MALE_SEARCH {
        @Override
        public String getRussianName() {
            return "Судари";
        }

        @Override
        public List<String> getPartnersGenders() {
            return List.of(Gender.MALE.name());
        }
    },
    FEMALE_SEARCH {
        @Override
        public String getRussianName() {
            return "Сударыни";
        }

        @Override
        public List<String> getPartnersGenders() {
            return List.of(Gender.FEMALE.name());
        }
    },
    ALL_SEARCH {
        @Override
        public String getRussianName() {
            return "Все";
        }

        @Override
        public List<String> getPartnersGenders() {
            return List.of(Gender.MALE.name(), Gender.FEMALE.name());
        }
    };

    public abstract String getRussianName();
    public abstract List<String> getPartnersGenders();
}
