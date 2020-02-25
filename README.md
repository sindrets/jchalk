# jchalk

> A partial port of [chalk](https://github.com/chalk/chalk) for Java

## Usage

```java
Chalk chalk = new Chalk();

System.out.println(chalk.yellow().bold().apply("Hello world!"));

// Nest styles
System.out.println(
    chalk.green().apply(
        "I am a green line " +
        chalk.blue().underline().bold().apply("with a blue substring") +
        " that becomes green again!"
    )
);

// RGB for terminals that support it
System.out.println(chalk.bgRgb(121, 41, 71).white().apply("RGB background!"));
```

<p align="left">
  <img height="300" src="https://imgur.com/FKLLP7n.jpg">
</p>

## API

### `chalk.<style>()[.<style>()...].apply(string)`

Example: `chalk.red().bold().underline().apply("Hello world");`
