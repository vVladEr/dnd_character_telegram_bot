package dnd.bot.maven.eclipse.User.Character.Description.Possessions;

import java.util.HashMap;
import java.util.List;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;

public class Possessions extends State {
    public HashMap<String, List<String>> possessions = new HashMap<String, List<String>>();

    public Possessions() {
        possibleTransitions = new HashMap<String, State>();
        possibleTransitions.put("back", new Back());
    }

    @Override
	public ResponseObject getStateMessages() {
		var response = new ResponseObject();	

        for (var possession : possessions.keySet()) {
            var descriptions = possessions.get(possession);
            for (var i = 0; i < descriptions.size(); i++) {
                var possessionMessageObject = new MessageObject(possession, descriptions.get(i));
                if (i == descriptions.size() - 1) {
                    var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
		            possessionMessageObject.addInlineKeybordButton(backInlineKeybordButton);
                }
                response.addMessageObject(possessionMessageObject);
            }
        }

        return response;
    }
}
