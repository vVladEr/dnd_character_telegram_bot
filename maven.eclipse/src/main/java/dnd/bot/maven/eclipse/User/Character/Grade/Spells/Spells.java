package dnd.bot.maven.eclipse.User.Character.Grade.Spells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;

public class Spells extends State {
    public List<String> spells = new ArrayList<String>();

    public Spells() {
		possibleTransitions = new HashMap<>();
		possibleTransitions.put("back", new Back());
	}

    @Override
	public ResponseObject getStateMessages() {
		var response = new ResponseObject();

        for (var i = 0; i < spells.size(); i++) {
            var spellMessageObject = new MessageObject(spells.get(i));
            if (i == spells.size() - 1) {
                var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
		        spellMessageObject.addInlineKeybordButton(backInlineKeybordButton);
            }
            response.addMessageObject(spellMessageObject);
        }

        return response;
	}
}
