package dnd.bot.maven.eclipse.User.Character.Stats;

import java.util.List;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat.GeneralStat;

public class Stats extends State {
    public List<GeneralStat> generalStats;
    
    @Override
	public ResponseObject getStateMessages() {
		var response = new ResponseObject();

		var generalStatsMessageObject = new MessageObject("Выберите стату", "");

		for (var i = 0; i < generalStats.size(); i++) {
			generalStatsMessageObject.addInlineKeybordButton(getInlineKeybordButton(generalStats.get(i).name, generalStats.get(i).name));
		}

		response.addMessageObject(generalStatsMessageObject);

		return response;
	}
}