package ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums;

public enum ButtonCommandEnum {

    SUDAR {
        public String getDescription() {
            return "choose SUDAR";
        }
    },
    SUDARINYA {
        public String getDescription() {
            return "choose SUDARINYA";
        }
    },
    ABOUT {
        public String getDescription() {
            return "get about command";
        }
    },
    ALL_PERSON_SEARCH {
        public String getDescription() {
            return "choose ALL_PERSON for search accounts";
        }
    },
    SUDAR_SEARCH {
        public String getDescription() {
            return "choose SUDAR_SEARCH for search accounts";
        }
    },
    SUDARINYA_SEARCH {
        public String getDescription() {
            return "choose SUDARINYA_SEARCH for search accounts";
        }
    };

    public String getDescription() {
        return this.getDescription();
    }
}
