package dnd.bot.maven.eclipse.User;

import java.util.List;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Character;


public class User extends State {
    public int id;
    public List<Character> characters;

    @Override
    public ResponseObject getStateMessages() {
        var response = new ResponseObject();

		var characterMessageObject = new MessageObject("Выберите персонажа", "");

        for (var i = 0; i < characters.size(); i++) {
			characterMessageObject.addInlineKeybordButton(getInlineKeybordButton(characters.get(i).name, characters.get(i).name));
		}

        response.addMessageObject(characterMessageObject);

		return response;
    }
}
