package ru.digitalleague.prerevolutionarytinderdatabase.enums;

public enum RomanceStatus {
    YOU_LIKE {
        @Override
        public String getRussianName() {
            return "Любим вами";
        }
    },
    YOU_LIKED {
        @Override
        public String getRussianName() {
            return "Вы любимы";
        }
    },
    MUTUALLY_LIKED {
        @Override
        public String getRussianName() {
            return "Взаимность";
        }
    };

    public abstract String getRussianName();
}
