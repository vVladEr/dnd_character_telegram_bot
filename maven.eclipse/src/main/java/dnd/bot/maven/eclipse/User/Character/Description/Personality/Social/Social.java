package dnd.bot.maven.eclipse.User.Character.Description.Personality.Social;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;

public class Social extends State {
    public String race;
    public String characterClass;

    public Social() {
		possibleTransitions = new HashMap<>();
		possibleTransitions.put("back", new Back());
	}

    @Override
	public ResponseObject getStateMessages() {
        // var data = socialRepository.getData();

        // var race = data.race;
        // var characterName = data.characterName;
        // var characterClass = data.characterClass;

		var response = new ResponseObject();	

        var raceMessageObject = new MessageObject("Раса", String.format("%s", race));
        response.addMessageObject(raceMessageObject);

        var characterClassMessageObject = new MessageObject("Класс", String.format("%s", characterClass));
        var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
		characterClassMessageObject.addInlineKeybordButton(backInlineKeybordButton);
        response.addMessageObject(characterClassMessageObject);

        return response;
	}
}
