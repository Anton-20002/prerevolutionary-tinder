package ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums;

public enum ButtonCommandEnum {

    SUDAR {
        public String getDescription() {
            return "/sudar";
        }
    },
    SUDARINYA {
        public String getDescription() {
            return "/sudarinya";
        }
    },
    ABOUT {
        public String getDescription() {
            return "/about";
        }
    },
    ALL_PERSON_SEARCH {
        public String getDescription() {
            return "/all_person_search";
        }
    },
    SUDAR_SEARCH {
        public String getDescription() {
            return "/sudar_search";
        }
    },
    SUDARINYA_SEARCH {
        public String getDescription() {
            return "/sudarinya_search";
        }
    };

    public String getDescription() {
        return this.getDescription();
    }
}
