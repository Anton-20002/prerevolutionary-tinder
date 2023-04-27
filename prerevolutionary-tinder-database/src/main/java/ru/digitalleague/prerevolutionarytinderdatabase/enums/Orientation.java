package ru.digitalleague.prerevolutionarytinderdatabase.enums;

public enum Orientation {
    MALE {
        @Override
        public String getRussianName() {
            return "Судари";
        }
    },
    FEMALE {
        @Override
        public String getRussianName() {
            return "Сударыни";
        }
    },
    ALL {
        @Override
        public String getRussianName() {
            return "Все";
        }
    };

    public abstract String getRussianName();
}
