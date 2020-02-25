package no.stide.jchalk;

import java.util.function.Function;

public class Style implements Function<String, String> {
    public String open;
    public String close;

    public Style(String open, String close) {
        this.open = open;
        this.close = close;
    }

    @Override
    public String apply(String text) {
        return this.open + text + this.close;
    }
}