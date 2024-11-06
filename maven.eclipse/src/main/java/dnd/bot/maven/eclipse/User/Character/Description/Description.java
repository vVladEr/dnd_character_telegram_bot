package dnd.bot.maven.eclipse.User.Character.Description;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Description.Features.Features;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Personality;
import dnd.bot.maven.eclipse.User.Character.Description.Possessions.Possessions;

import java.util.HashMap;

public class Description extends State {
    public Description(
            Personality personality, 
            Features features,
            Possessions possessions
        ) {
        possibleTransitions = new HashMap<String, State>();
        possibleTransitions.put("moveToPersonality", personality);
        possibleTransitions.put("moveToFeatures", features);
        possibleTransitions.put("moveToPossessions", possessions);
        possibleTransitions.put("back", new Back());
    }

    @Override
	public ResponseObject getStateMessages() {
		var response = new ResponseObject();	

        var messageObject = new MessageObject("Куда?");
        var personalityInlineKeybordButton = getInlineKeybordButton("Внешность", "moveToPersonality");
        var featuresInlineKeybordButton = getInlineKeybordButton("Особенности", "moveToFeatures");
        var possessionsInlineKeybordButton = getInlineKeybordButton("Владения", "moveToPossessions");
        var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
        messageObject.addInlineKeybordButton(personalityInlineKeybordButton);
        messageObject.addInlineKeybordButton(featuresInlineKeybordButton);
        messageObject.addInlineKeybordButton(possessionsInlineKeybordButton);
        messageObject.addInlineKeybordButton(backInlineKeybordButton);
        response.addMessageObject(messageObject);

        return response;
	}
}
