package ru.digitalleague.prerevolutionarytinderdatabase.enums;

import java.util.List;

public enum Orientation {
    MALE {
        @Override
        public String getRussianName() {
            return "Судари";
        }

        @Override
        public List<String> getPartnersGenders() {
            return List.of(Gender.MALE.name());
        }
    },
    FEMALE {
        @Override
        public String getRussianName() {
            return "Сударыни";
        }

        @Override
        public List<String> getPartnersGenders() {
            return List.of(Gender.FEMALE.name());
        }
    },
    ALL {
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
