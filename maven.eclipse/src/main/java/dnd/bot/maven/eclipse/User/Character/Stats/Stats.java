package dnd.bot.maven.eclipse.User.Character.Stats;


import java.util.HashMap;

import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat.GeneralStat;

public class Stats extends State {
    HashMap<String, GeneralStat> generalStats;
    
    @Override
	public void render() {
		possibleTransitions = new HashMap<String, State>();
	}
}