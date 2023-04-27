package ru.digitalleague.prerevolutionarytinderdatabase.enums;

public enum Gender {
    MALE {
        @Override
        public String getRussianName() {
            return "Сударь";
        }
    },
    FEMALE {
        @Override
        public String getRussianName() {
            return "Сударыня";
        }
    };

    public abstract String getRussianName();
}
