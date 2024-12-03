package dnd.bot.maven.eclipse.Interfaces;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;
import dnd.bot.maven.eclipse.db.Models.dbo.IDbo;

public interface IGenerator {
    public BaseState generateState(Combinekey parameters);

    public HashMap<String, String> getFormattedFields(IDbo dbo);

    public HashMap<String, String> getFormattedButtons();

    public HashMap<String, Combinekey> getPossibleTransitions();

}