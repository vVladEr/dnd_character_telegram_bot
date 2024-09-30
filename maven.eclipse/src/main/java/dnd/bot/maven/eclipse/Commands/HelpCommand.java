package dnd.bot.maven.eclipse.Commands;
import java.util.Map;

import dnd.bot.maven.eclipse.Interfaces.ICommand;

public class HelpCommand implements ICommand {

	private static Map<String, ICommand> _commands;
	
	public HelpCommand(Map<String, ICommand> commands) {
		_commands = commands;
	}
	
	@Override
	public String executeCommand() {
		var descriptions = new StringBuilder();
		
		_commands.forEach((key, value) -> descriptions.append(key + ": " + value.getDescription() + "\n\n"));
		
		return descriptions.toString();
	}

	public String getDescription() {
		return "предоставляет информацию о командах бота";
	}
}
