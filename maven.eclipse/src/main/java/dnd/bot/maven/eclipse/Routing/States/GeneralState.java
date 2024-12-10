package dnd.bot.maven.eclipse.Routing.States;
import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;

public class GeneralState extends BaseState {
    public GeneralState(
        LinkedHashMap<String, String> fields, 
        LinkedHashMap<String, String> buttons, 
        HashMap<String, Combinekey> possibleTransitions
    ) {
        this.fields = fields;
        this.buttons = buttons;
        this.possibleTransitions = possibleTransitions;
    }
}
