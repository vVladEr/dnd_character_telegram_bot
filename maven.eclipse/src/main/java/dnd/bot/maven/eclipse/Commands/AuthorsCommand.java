package dnd.bot.maven.eclipse.Commands;
import dnd.bot.maven.eclipse.Interfaces.ICommand;
public class AuthorsCommand implements ICommand{

	public String executeCommand() {
		return "Создатели Телеграм-бота : \n"
				+ "Ермаков Владислав \n"
				+ "Щеглеватов Артём \n"
				+ "Попов Егор";
	}

	public String getDescription() {
		return "предоставляет информацию о создателях бота";
	}

}
