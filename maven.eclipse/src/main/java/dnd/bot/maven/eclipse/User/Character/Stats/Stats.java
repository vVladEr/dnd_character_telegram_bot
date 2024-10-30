package dnd.bot.maven.eclipse.User.Character.Stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Stats.Stat.Stat;

public class Stats extends State {
    public List<Stat> stats = new ArrayList<Stat>();

	public Stats() {
		possibleTransitions = new HashMap<>();
		possibleTransitions.put("back", new Back());
	}
    
    @Override
	public ResponseObject getStateMessages() {
		var response = new ResponseObject();

		var generalStatsMessageObject = new MessageObject("Выберите стату:");
		var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");

		for (var stat : stats) {
			generalStatsMessageObject.addInlineKeybordButton(getInlineKeybordButton(stat.name, stat.name));
		}
		generalStatsMessageObject.addInlineKeybordButton(backInlineKeybordButton);
		response.addMessageObject(generalStatsMessageObject);

		return response;
	}
}