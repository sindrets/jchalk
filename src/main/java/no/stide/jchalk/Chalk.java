package no.stide.jchalk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Chalk {

    /**
     * A list of all unique closers.
     */
    private static final List<String> ANSI_CLOSERS = Arrays.asList(
        AnsiStyle.RESET.close,
        AnsiStyle.BOLD.close,
        AnsiStyle.ITALIC.close,
        AnsiStyle.UNDERLINE.close,
        AnsiStyle.REVERSE.close,
        AnsiStyle.HIDDEN.close,
        AnsiStyle.STRIKETHROUGH.close,
        AnsiStyle.BLACK.close,
        AnsiStyle.BG_BLACK.close
    );
    private static final Pattern ansiEscapeRegex = Pattern.compile("(?:\u001B[@-_]|[\u0080-\u009F])[0-?]*[ -/]*[@-~]");

    private ArrayList<Style> styles = new ArrayList<>();

    public String apply(String text) {
        String openers = this.getOpeners();
        String closers = this.getClosers();
        MatchResult[] escapes = ansiEscapeRegex.matcher(text).results().toArray(MatchResult[]::new);
        // reverse list
        for(int i = 0; i < escapes.length / 2; i++) {
            MatchResult temp = escapes[i];
            escapes[i] = escapes[escapes.length - i - 1];
            escapes[escapes.length - i - 1] = temp;
        }
        for (MatchResult e : escapes) {
            String value = e.group();
            if (ANSI_CLOSERS.contains(value)) {
                text = text.substring(0, e.end()) + openers + text.substring(e.end());
                break;
            }
        }
        return openers + text + closers;
    }

    public String apply(Object obj) {
        return this.apply(obj.toString());
    }

    @Override
    public Chalk clone() {
        Chalk clone = new Chalk();
        clone.styles = new ArrayList<>(this.styles);
        return clone;
    }

    public Chalk clone(Style newStyle) {
        Chalk clone = new Chalk();
        clone.styles = new ArrayList<>(this.styles);
        clone.styles.add(newStyle);
        return clone;
    }

    private String getOpeners() {
        String s = "";
        for (Style style : this.styles) {
            s += style.open;
        }
        return s;
    }

    private String getClosers() {
        String s = "";
        for (Style style : this.styles) {
            s += style.close;
        }
        return s;
    }

    /**
     * Returns the ansi code of all applied styles without any reset codes.
     */
    public String getAnsiCode() {
        String code = "";
        for (Style style : this.styles) {
            code += style.open;
        }
        return code;
    }

    public Chalk ansi(String ansiCode) {
        return this.clone(new Style(ansiCode, AnsiStyle.RESET.close));
    }

    public Chalk ansi16(int n) {
        String code = "\u001b[3" + (n % 8);
        if (n > 7) {
            code += ";1";
        }
        return this.clone(new Style(code + "m", AnsiStyle.BLACK.close));
    }

    public Chalk bgAnsi16(int n) {
        String code = "\u001b[4" + (n % 8);
        if (n > 7) {
            code += ";1";
        }
        return this.clone(new Style(code + "m", AnsiStyle.BG_BLACK.close));
    }

    public Chalk ansi256(int n) {
        return this.clone(new Style("\u001b[38;5;" + (n % 256) + "m", AnsiStyle.BLACK.close));
    }

    public Chalk bgAnsi256(int n) {
        return this.clone(new Style("\u001b[48;5;" + (n % 256) + "m", AnsiStyle.BG_BLACK.close));
    }

    public Chalk rgb(int r, int g, int b) {
        return this.clone(new Style(String.format("\u001b[38;2;%d;%d;%dm", r % 256, g % 256, b % 256), AnsiStyle.BLACK.close));
    }

    public Chalk bgRgb(int r, int g, int b) {
        return this.clone(new Style(String.format("\u001b[48;2;%d;%d;%dm", r % 256, g % 256, b % 256), AnsiStyle.BG_BLACK.close));
    }

    /*
     * --- TEXT ATTRIBUTES ---
     */

    public Chalk bold() {
        return this.clone(AnsiStyle.BOLD.style);
    }

    public Chalk dim() {
        return this.clone(AnsiStyle.DIM.style);
    }
    
    public Chalk italic() {
        return this.clone(AnsiStyle.ITALIC.style);
    }
    
    public Chalk underline() {
        return this.clone(AnsiStyle.UNDERLINE.style);
    }
    
    public Chalk reversed() {
        return this.clone(AnsiStyle.REVERSE.style);
    }
    
    public Chalk strikethrough() {
        return this.clone(AnsiStyle.STRIKETHROUGH.style);
    }
    
    /*
     * --- FG8 ---
     */
    
    public Chalk black() {
        return this.clone(AnsiStyle.BLACK.style);
    }
    
    public Chalk red() {
        return this.clone(AnsiStyle.RED.style);
    }
    
    public Chalk green() {
        return this.clone(AnsiStyle.GREEN.style);
    }
    
    public Chalk yellow() {
        return this.clone(AnsiStyle.YELLOW.style);
    }
    
    public Chalk blue() {
        return this.clone(AnsiStyle.BLUE.style);
    }
    
    public Chalk magenta() {
        return this.clone(AnsiStyle.MAGENTA.style);
    }
    
    public Chalk cyan() {
        return this.clone(AnsiStyle.CYAN.style);
    }
    
    public Chalk white() {
        return this.clone(AnsiStyle.WHITE.style);
    }
    
    /*
     * --- FG16 ---
     */
    
    public Chalk blackBright() {
        return this.clone(AnsiStyle.BLACK_BRIGHT.style);
    }
    
    public Chalk redBright() {
        return this.clone(AnsiStyle.RED_BRIGHT.style);
    }
    
    public Chalk greenBright() {
        return this.clone(AnsiStyle.GREEN_BRIGHT.style);
    }
    
    public Chalk yellowBright() {
        return this.clone(AnsiStyle.YELLOW_BRIGHT.style);
    }
    
    public Chalk blueBright() {
        return this.clone(AnsiStyle.BLUE_BRIGHT.style);
    }
    
    public Chalk magentaBright() {
        return this.clone(AnsiStyle.MAGENTA_BRIGHT.style);
    }
    
    public Chalk cyanBright() {
        return this.clone(AnsiStyle.CYAN_BRIGHT.style);
    }
    
    public Chalk whiteBright() {
        return this.clone(AnsiStyle.BLACK_BRIGHT.style);
    }
    
    /*
     * --- BG8 ---
     */
    
    public Chalk bgBlack() {
        return this.clone(AnsiStyle.BG_BLACK.style);
    }
    
    public Chalk bgRed() {
        return this.clone(AnsiStyle.BG_RED.style);
    }
    
    public Chalk bgGreen() {
        return this.clone(AnsiStyle.BG_GREEN.style);
    }
    
    public Chalk bgYellow() {
        return this.clone(AnsiStyle.BG_YELLOW.style);
    }
    
    public Chalk bgBlue() {
        return this.clone(AnsiStyle.BG_BLUE.style);
    }
    
    public Chalk bgMagenta() {
        return this.clone(AnsiStyle.BG_MAGENTA.style);
    }
    
    public Chalk bgCyan() {
        return this.clone(AnsiStyle.BG_CYAN.style);
    }
    
    public Chalk bgWhite() {
        return this.clone(AnsiStyle.BG_WHITE.style);
    }
    
    /*
     * --- BG16 ---
     */
    
    public Chalk bgBlackBright() {
        return this.clone(AnsiStyle.BG_BLACK_BRIGHT.style);
    }
    
    public Chalk bgRedBright() {
        return this.clone(AnsiStyle.BG_RED_BRIGHT.style);
    }
    
    public Chalk bgGreenBright() {
        return this.clone(AnsiStyle.BG_GREEN_BRIGHT.style);
    }
    
    public Chalk bgYellowBright() {
        return this.clone(AnsiStyle.BG_YELLOW_BRIGHT.style);
    }
    
    public Chalk bgBlueBright() {
        return this.clone(AnsiStyle.BG_BLUE_BRIGHT.style);
    }
    
    public Chalk bgMagentaBright() {
        return this.clone(AnsiStyle.BG_MAGENTA_BRIGHT.style);
    }
    
    public Chalk bgCyanBright() {
        return this.clone(AnsiStyle.BG_CYAN_BRIGHT.style);
    }
    
    public Chalk bgWhiteBright() {
        return this.clone(AnsiStyle.BG_BLACK_BRIGHT.style);
    }
}
