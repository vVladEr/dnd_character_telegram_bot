package dnd.bot.maven.eclipse.User.Character.Description.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;

public class Features extends State {
    public List<String> features = new ArrayList<String>();

    public Features() {
		possibleTransitions = new HashMap<>();
		possibleTransitions.put("back", new Back());
	}

    @Override
	public ResponseObject getStateMessages() {
        var response = new ResponseObject();

        for (var i = 0; i < features.size(); i++)  {
            var featureMessageObject = new MessageObject(features.get(i));
            if (i == features.size() - 1) {
                var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
		        featureMessageObject.addInlineKeybordButton(backInlineKeybordButton);
            }
            response.addMessageObject(featureMessageObject);
        }

        return response;
	}
}