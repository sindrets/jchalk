package no.stide.jchalk;

import org.junit.Test;
import static org.junit.Assert.*;

public class ChalkTest {
    
    @Test public void testNesting() {
        Chalk chalk = new Chalk();
        String s1 = chalk.green().apply(
            "I am a green line " +
            chalk.blue().underline().bold().apply("with a blue substring") +
            " that becomes green again!"
        );
        String s2 = chalk.yellow().apply(
            "<lvl1>" +
            chalk.blue().bold().apply(
                "<lvl2>" +
                chalk.magenta().italic().apply("<lvl3></lvl3>") +
                "</lvl2>"
            ) +
            "</lvl1>"
        );

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(chalk.rgb(121, 41, 71).apply("RGB foreground!"));
        System.out.println(chalk.bgRgb(121, 41, 71).white().apply("RGB background!"));

        assertEquals(
            "Nesting works as expected part 1", 
            "[32mI am a green line [34m[4m[1mwith a blue substring[39m[24m[22m[32m that becomes green again![39m",
            s1
        );
        assertEquals(
            "Nesting works as expected part 2",
            "[33m<lvl1>[34m[1m<lvl2>[35m[3m<lvl3></lvl3>[39m[23m[34m[1m</lvl2>[39m[22m[33m</lvl1>[39m",
            s2
        );
    }
}
