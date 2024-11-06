package dnd.bot.maven.eclipse.Commands;

import dnd.bot.maven.eclipse.Bot.Mocks;
import dnd.bot.maven.eclipse.Interfaces.ICommand;
import dnd.bot.maven.eclipse.Routing.Router;
import dnd.bot.maven.eclipse.User.User;
import dnd.bot.maven.eclipse.User.Character.Character;

public class StartCommand implements ICommand {
    private Router router;
    private Character[] characters;

    public StartCommand(Router router) {
        this.router = router;
        this.characters = Mocks.characters;
    }  

    @Override
	public String executeCommand() {
        router.clear();
        router.addState(new User(characters));
        return "начальное состояние задано";
    }

	public String getDescription() {
		return "запускает бота";
	}
}
