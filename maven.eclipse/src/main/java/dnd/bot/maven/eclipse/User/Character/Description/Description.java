package dnd.bot.maven.eclipse.User.Character.Description;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.BasicDescription.BasicDescription;
import dnd.bot.maven.eclipse.User.Character.Description.Features.Features;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Personality;

import java.util.HashMap;
import java.util.List;

public class Description extends State {
    public Personality personality;
    public Features features;
    public HashMap<String, List<BasicDescription>> possessions;

    public Description() {
        possibleTransitions = new HashMap<String, State>() {{
            possibleTransitions.put("moveToPersonality", personality);
            possibleTransitions.put("moveToFeatures", features);
        }};
    }

    @Override
	public ResponseObject getStateMessages() {
		var response = new ResponseObject();	

        var messageObject = new MessageObject();
        var personalityInlineKeybordButton = getInlineKeybordButton("Внешность", "moveToPersonality");
        var featuresInlineKeybordButton = getInlineKeybordButton("Особенности", "moveToFeatures");
        messageObject.addInlineKeybordButton(personalityInlineKeybordButton);
        messageObject.addInlineKeybordButton(featuresInlineKeybordButton);
        response.addMessageObject(messageObject);

        for (var possession : possessions.keySet()) {
            for (var description : possessions.get(possession)) {
                var possessionMessageObject = new MessageObject(description.name, description.description);
                response.addMessageObject(possessionMessageObject);
            }
        }

        return response;
	}
}
