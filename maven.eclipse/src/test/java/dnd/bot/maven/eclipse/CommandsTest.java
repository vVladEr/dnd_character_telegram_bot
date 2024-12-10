package dnd.bot.maven.eclipse;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.Commands.HelpCommand;
import dnd.bot.maven.eclipse.Commands.InfoCommand;
import dnd.bot.maven.eclipse.Interfaces.ICommand;

public class CommandsTest {

	@Test
	public void helpCommandShouldReturnEmptyLineWhenNoCommandsAdded() {
		var commands = new LinkedHashMap<String, ICommand>();
		var helpCommand = new HelpCommand(commands);
		var commandOutPut = helpCommand.executeCommand();
		assertTrue(commandOutPut.length() == 0);
	}

	@Test
	public void helpCommandShouldReturnCorrectNumberOfAddedCommands() {
		var commands = new LinkedHashMap<String, ICommand>();
		var helpCommand = new HelpCommand(commands);
		var infoCommand = new InfoCommand();
		commands.put("/info", infoCommand);
		commands.put("/help", helpCommand);
		var commandsLines = helpCommand.executeCommand().split("\n\n");
		assertTrue(commandsLines.length == 2);
	}

	@Test
	public void helpCommandShouldReturnCorrectDesc() {
		var commands = new LinkedHashMap<String, ICommand>();
		var helpCommand = new HelpCommand(commands);
		commands.put("/help", helpCommand);
		var commandsLines = helpCommand.executeCommand().trim();
		assertTrue(commandsLines.split(": ")[1].equals("предоставляет информацию о командах бота"));
	}

	@Test
	public void helpCommandShouldReturnDescInCorrectOrder() {
		var commands = new LinkedHashMap<String, ICommand>();
		var helpCommand = new HelpCommand(commands);
		var infoCommand = new InfoCommand();
		commands.put("/info", infoCommand);
		commands.put("/help", helpCommand);
		var commandsLines = helpCommand.executeCommand().split("\n\n");
		assertTrue(commandsLines[0].split(": ")[0].equals("/info"));
		assertTrue(commandsLines[1].split(": ")[0].equals("/help"));
	}
}
