package dnd.bot.maven.eclipse.Commands;
import dnd.bot.maven.eclipse.Interfaces.ICommand;

public class InfoCommand implements ICommand {

	@Override
	public String execute() {
		return "Телеграм бот предоставляет функционал для игры в днд : создание персонажа и менеджемент его ресурсов в течении компании";
	}

}
