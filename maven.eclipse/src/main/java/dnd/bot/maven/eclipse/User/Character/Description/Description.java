package dnd.bot.maven.eclipse.User.Character.Description;

import dnd.bot.maven.eclipse.User.Character.BasicDescription.BasicDescription;
import dnd.bot.maven.eclipse.User.Character.Description.Personality.Personality;
import dnd.bot.maven.eclipse.User.Character.Description.Possession.Possession;

import java.util.Dictionary;
import java.util.List;

public class Description {
    Personality personality;
    List<BasicDescription> features;
    Dictionary<String, List<Possession>> possessions;
}
