package dnd.bot.maven.eclipse.User.Character.Grade;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Grade.Spells.Spells;

public class Grade extends State {
    public int maxCount;
    public int count;
    public Spells spells;

    public Grade() {
        possibleTransitions = new HashMap<String, State>() {{
            possibleTransitions.put("moveToSpells", spells);
        }};
    }

    @Override
	public ResponseObject getStateMessages() {
        var response = new ResponseObject();

        var maxCountMessageObject = new MessageObject("Максимум ячеек", String.format("%d", maxCount));
        response.addMessageObject(maxCountMessageObject);

        var countMessageObject = new MessageObject("Осталось ячеек", String.format("%d", count));
        var spellsInlineKeybordButton = getInlineKeybordButton("Заклинания", "moveToSpells");
        countMessageObject.addInlineKeybordButton(spellsInlineKeybordButton);
        response.addMessageObject(countMessageObject);
        
        return response;
    }
}
