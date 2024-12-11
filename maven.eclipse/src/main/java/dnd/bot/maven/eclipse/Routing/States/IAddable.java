package dnd.bot.maven.eclipse.Routing.States;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Routing.GeneratorManager;

public interface IAddable {
    public void addElement(GeneratorManager manager, HashMap<String, String> necessaryFields);
}
