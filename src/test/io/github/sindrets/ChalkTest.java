package io.github.sindrets;

import io.github.sindrets.fling.TestGroup;
import io.github.sindrets.fling.TestInitiator;
import io.github.sindrets.jchalk.Chalk;

public class ChalkTest {

    @TestGroup(description = "Test nested styles")
    public void testNesting(TestInitiator suite) {
        Chalk chalk = new Chalk();
        String s1 = chalk.green().apply(
                "I am a green line "
                        + chalk.blue().underline().bold().apply("with a blue substring")
                        + " that becomes green again!");

        String s2 = chalk.yellow().apply(
                "<lvl1>"
                        + chalk.blue().bold().apply("<lvl2>"
                                + chalk.magenta().italic().apply("<lvl3></lvl3>")
                                + "</lvl2>")
                        + "</lvl1>");

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(chalk.rgb(121, 41, 71).apply("RGB foreground!"));
        System.out.println(chalk.bgRgb(121, 41, 71).white().apply("RGB background!"));

        suite.it("handles nested styles correctly 1")
                .expect(s1)
                .toBe("[32mI am a green line [34m[4m[1mwith a blue substring[39m[24m"
                        + "[22m[32m that becomes green again![39m");

        suite.it("handles nested styles correctly 2")
                .expect(s2)
                .toBe("[33m<lvl1>[34m[1m<lvl2>[35m[3m<lvl3></lvl3>[39m[23m[34m"
                        + "[1m</lvl2>[39m[22m[33m</lvl1>[39m");
    }

    @TestGroup(description = "Test visible length")
    public void testVisibleLength(TestInitiator suite) {
        Chalk chalk = new Chalk();

        suite.it("should report the correct visible length")
                .expect(Chalk.visibleLength(chalk.cyan().bold().underline().apply("foobar")))
                .toBe(6);

        String s = chalk.yellow().apply(
                "<lvl1>"
                        + chalk.blue().bold().apply("<lvl2>"
                                + chalk.magenta().italic().apply("<lvl3></lvl3>")
                                + "</lvl2>")
                        + "</lvl1>");
        suite.it("should report the correct visible length with nested style")
            .expect(Chalk.visibleLength(s))
            .toBe(39);
    }
}
