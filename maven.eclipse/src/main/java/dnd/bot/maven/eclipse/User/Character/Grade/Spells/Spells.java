package dnd.bot.maven.eclipse.User.Character.Grade.Spells;

import java.util.List;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.BasicDescription.BasicDescription;

public class Spells extends State {
    public List<BasicDescription> spells;

    @Override
	public ResponseObject getStateMessages() {
		var response = new ResponseObject();

        for (var spell : spells) {
            var spellMessageObject = new MessageObject(spell.name, spell.description);
            response.addMessageObject(spellMessageObject);
        }

        return response;
	}
}
