package dnd.bot.maven.eclipse.User.Character.Description.Personality.Level;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;

public class Level extends State {
    private LevelRepository levelRepository;

    @Override
	public ResponseObject getStateMessages() {
        var data = levelRepository.getData();

        var currentLevel = data.currentLevel;
        var currentExp = data.currentExp;
        var necessaryExp = data.necessaryExp;

        var response = new ResponseObject();

		var currentLevelMessageObject = new MessageObject("Текущий уровень", String.format("%d", currentLevel));
        response.addMessageObject(currentLevelMessageObject);

        var currentExpMessageObject = new MessageObject("Текущий опыт", String.format("%d", currentExp));
        response.addMessageObject(currentExpMessageObject);

        var necessaryExpMessageObject = new MessageObject("Необходимый опыт", String.format("%d", necessaryExp));
        response.addMessageObject(necessaryExpMessageObject);

        return response;
	}
}
