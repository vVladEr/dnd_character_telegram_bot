package dnd.bot.maven.eclipse.Commands;

import dnd.bot.maven.eclipse.Interfaces.ICommand;

public class StartCommand implements ICommand {
    public StartCommand() {
    }  

    @Override
	public String executeCommand() {
        return "начальное состояние задано";
    }

	public String getDescription() {
		return "запускает бота";
	}
}
