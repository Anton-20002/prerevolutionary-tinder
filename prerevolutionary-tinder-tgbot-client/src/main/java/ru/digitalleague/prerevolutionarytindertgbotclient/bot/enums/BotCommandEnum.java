package ru.digitalleague.prerevolutionarytindertgbotclient.bot.enums;

public enum BotCommandEnum {
    START {
        public String getDescription() {
            return "get a welcome message";
        }
    },
    HELP{
        public String getDescription() {
            return "info about commands";
        }
    };


    public String getDescription() {
        return this.getDescription();
    }
}
