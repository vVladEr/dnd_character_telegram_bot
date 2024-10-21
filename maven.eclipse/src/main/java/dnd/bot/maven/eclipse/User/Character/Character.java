package dnd.bot.maven.eclipse.User.Character;

import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Description.Description;
import dnd.bot.maven.eclipse.User.Character.Grade.Grade;
import dnd.bot.maven.eclipse.User.Character.Inventory.Inventory;
import dnd.bot.maven.eclipse.User.Character.Notes.Notes;
import dnd.bot.maven.eclipse.User.Character.Stats.Stats;

import java.util.HashMap;

public class Character extends State {
    public Stats stats;
    public Description description;
    public Inventory inventory;
    public Grade grades;
    public Notes notes;

    public Character() {
        possibleTransitions = new HashMap<String, State>() {{
            possibleTransitions.put("moveToStats", stats);
            possibleTransitions.put("moveToDescription", description);
            possibleTransitions.put("moveToInventory", inventory);
            possibleTransitions.put("moveToGrade", grades);
            possibleTransitions.put("moveToNotes", notes);
        }};
    }

    @Override
    public void render() {

    }
}
