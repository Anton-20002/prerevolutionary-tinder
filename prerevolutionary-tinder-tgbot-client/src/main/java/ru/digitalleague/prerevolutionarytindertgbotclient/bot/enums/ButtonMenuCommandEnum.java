package ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums;

public enum ButtonMenuCommandEnum {

    SEARCH {
        public String getDescription() {
            return "Поиск";
        }
    },
    ACCOUNT {
        public String getDescription() {
            return "Анкета";
        }
    },
    FAVORITES {
        public String getDescription() {
            return "Любимцы";
        }
    };

    public String getDescription() {
        return this.getDescription();
    }
}
