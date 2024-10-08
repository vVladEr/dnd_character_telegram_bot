package dnd.bot.maven.eclipse.User.Character;

import dnd.bot.maven.eclipse.User.Character.BasicDescription.BasicDescription;
import dnd.bot.maven.eclipse.User.Character.Description.Description;
import dnd.bot.maven.eclipse.User.Character.Grade.Grade;
import dnd.bot.maven.eclipse.User.Character.Inventory.Inventory;
import dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat;

import java.util.Dictionary;
import java.util.List;

public class Character {
    Dictionary<String, GeneralStat> stats;
    public Description[] descriptions;
    public Inventory inventory;
    public Dictionary<Integer, Grade> grades;
    public List<BasicDescription> notes;
}
