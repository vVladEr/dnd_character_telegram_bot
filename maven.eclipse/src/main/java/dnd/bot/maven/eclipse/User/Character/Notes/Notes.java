package dnd.bot.maven.eclipse.User.Character.Notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;

public class Notes extends State {
    public List<String> notes = new ArrayList<String>();

    public Notes() {
		possibleTransitions = new HashMap<>();
		possibleTransitions.put("back", new Back());
	}

    @Override
    public ResponseObject getStateMessages() {
        var response = new ResponseObject();

        for (var i = 0; i < notes.size(); i++)  {
            var noteMessageObject = new MessageObject(notes.get(i));
            if (i == notes.size() - 1) {
                var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
		        noteMessageObject.addInlineKeybordButton(backInlineKeybordButton);
            }
            response.addMessageObject(noteMessageObject);
        }

        return response;
    }   
}
