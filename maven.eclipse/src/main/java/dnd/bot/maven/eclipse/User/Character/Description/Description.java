package dnd.bot.maven.eclipse.User.Character.Description;

import dnd.bot.maven.eclipse.User.Character.BasicDescription.BasicDescription;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Personality;
import dnd.bot.maven.eclipse.User.Character.Description.Possession.Possession;

import java.util.Dictionary;
import java.util.List;

public class Description {
    public Personality personality;
    public List<BasicDescription> features;
    public Dictionary<String, List<Possession>> possessions;
}
