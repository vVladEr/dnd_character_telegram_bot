package dnd.bot.maven.eclipse.User.Character.Description.Personality.Level;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;

public class Level extends State {
    public int currentLevel;
    public int currentExp;
    public int necessaryExp;

    public Level() {
		possibleTransitions = new HashMap<>();
		possibleTransitions.put("back", new Back());
	}

    @Override
	public ResponseObject getStateMessages() {
        var response = new ResponseObject();

		var currentLevelMessageObject = new MessageObject("Текущий уровень", String.format("%d", currentLevel));
        response.addMessageObject(currentLevelMessageObject);

        var currentExpMessageObject = new MessageObject("Текущий опыт", String.format("%d", currentExp));
        response.addMessageObject(currentExpMessageObject);

        var necessaryExpMessageObject = new MessageObject("Необходимый опыт", String.format("%d", necessaryExp));
        var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
		necessaryExpMessageObject.addInlineKeybordButton(backInlineKeybordButton);
        response.addMessageObject(necessaryExpMessageObject);

        return response;
	}
}
