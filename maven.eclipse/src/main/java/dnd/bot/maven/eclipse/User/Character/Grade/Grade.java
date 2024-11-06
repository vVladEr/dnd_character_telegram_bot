package dnd.bot.maven.eclipse.User.Character.Grade;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Grade.Spells.Spells;

public class Grade extends State {
    public int maxCount;
    public int count;

    public Grade(
            Spells spells
        ) {
        possibleTransitions = new HashMap<String, State>();
        possibleTransitions.put("moveToSpells", spells);
        possibleTransitions.put("back", new Back());
    }

    @Override
	public ResponseObject getStateMessages() {
        // var data = gradeRepository.getData();

        // var maxCount = data.maxCount;
        // var count = data.count;

        var response = new ResponseObject();

        var maxCountMessageObject = new MessageObject("Максимум ячеек", String.format("%d", maxCount));
        response.addMessageObject(maxCountMessageObject);

        var countMessageObject = new MessageObject("Осталось ячеек", String.format("%d", count));
        var spellsInlineKeybordButton = getInlineKeybordButton("Заклинания", "moveToSpells");
        countMessageObject.addInlineKeybordButton(spellsInlineKeybordButton);
        var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
		countMessageObject.addInlineKeybordButton(backInlineKeybordButton);
        response.addMessageObject(countMessageObject);
        
        return response;
    }
}
