package io.github.sindrets.jchalk;

public enum AnsiStyle {

    RESET("\u001b[0m", "\u001b[0m"),
    BOLD("\u001b[1m", "\u001b[22m"),
    DIM("\u001b[2m", "\u001b[22m"),
    ITALIC("\u001b[3m", "\u001b[23m"),
    UNDERLINE("\u001b[4m", "\u001b[24m"),
    REVERSE("\u001b[7m", "\u001b[27m"),
    HIDDEN("\u001b[8m", "\u001b[28m"),
    STRIKETHROUGH("\u001b[9m", "\u001b[29m"),
    BLACK("\u001b[30m", "\u001b[39m"),
    RED("\u001b[31m", "\u001b[39m"),
    GREEN("\u001b[32m", "\u001b[39m"),
    YELLOW("\u001b[33m", "\u001b[39m"),
    BLUE("\u001b[34m", "\u001b[39m"),
    MAGENTA("\u001b[35m", "\u001b[39m"),
    CYAN("\u001b[36m", "\u001b[39m"),
    WHITE("\u001b[37m", "\u001b[39m"),
    BLACK_BRIGHT("\u001b[90m", "\u001b[39m"),
    RED_BRIGHT("\u001b[91m", "\u001b[39m"),
    GREEN_BRIGHT("\u001b[92m", "\u001b[39m"),
    YELLOW_BRIGHT("\u001b[93m", "\u001b[39m"),
    BLUE_BRIGHT("\u001b[94m", "\u001b[39m"),
    MAGENTA_BRIGHT("\u001b[95m", "\u001b[39m"),
    CYAN_BRIGHT("\u001b[96m", "\u001b[39m"),
    WHITE_BRIGHT("\u001b[97m", "\u001b[39m"),
    GRAY("\u001b[90m", "\u001b[39m"),
    GREY("\u001b[90m", "\u001b[39m"),
    BG_BLACK("\u001b[40m", "\u001b[49m"),
    BG_RED("\u001b[41m", "\u001b[49m"),
    BG_GREEN("\u001b[42m", "\u001b[49m"),
    BG_YELLOW("\u001b[43m", "\u001b[49m"),
    BG_BLUE("\u001b[44m", "\u001b[49m"),
    BG_MAGENTA("\u001b[45m", "\u001b[49m"),
    BG_CYAN("\u001b[46m", "\u001b[49m"),
    BG_WHITE("\u001b[47m", "\u001b[49m"),
    BG_BLACK_BRIGHT("\u001b[100m", "\u001b[49m"),
    BG_RED_BRIGHT("\u001b[101m", "\u001b[49m"),
    BG_GREEN_BRIGHT("\u001b[102m", "\u001b[49m"),
    BG_YELLOW_BRIGHT("\u001b[103m", "\u001b[49m"),
    BG_BLUE_BRIGHT("\u001b[104m", "\u001b[49m"),
    BG_MAGENTA_BRIGHT("\u001b[105m", "\u001b[49m"),
    BG_CYAN_BRIGHT("\u001b[106m", "\u001b[49m"),
    BG_WHITE_BRIGHT("\u001b[107m", "\u001b[49m"),
    BG_GRAY("\u001b[100m", "\u001b[49m"),
    BG_GREY("\u001b[100m", "\u001b[49m");

    public Style style;
    public String open;
    public String close;

    public String apply(String text) {
        return this.style.apply(text);
    }

    AnsiStyle(String open, String close) {
        this.style = new Style(open, close);
        this.open = this.style.open;
        this.close = this.style.close;
    }
}

