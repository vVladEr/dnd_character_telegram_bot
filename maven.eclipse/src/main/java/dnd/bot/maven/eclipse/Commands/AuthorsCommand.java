package dnd.bot.maven.eclipse.Commands;
import dnd.bot.maven.eclipse.Interfaces.ICommand;
public class AuthorsCommand implements ICommand{

	@Override
	public String execute() {
		return "Создатели Телеграм-бота : \n"
				+ "Ермаков Владислав \n"
				+ "Щеглеватов Артём";
	}

}
