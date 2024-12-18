package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.NotesState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.CombineKey;
import dnd.bot.maven.eclipse.db.repos.MongoNotesRepository;

public class NotesGenerator extends BaseGenerator {
    private CombineKey parameters;
    private MongoNotesRepository repo;

    public NotesGenerator(GeneratorManager manager) {
        repo = manager.getReposStorage().getNotesRepository();
    }


    @Override
    public BaseState generateState(CombineKey parameters) {
        this.parameters = parameters;
        var notes = repo.getCharacterNotes(parameters.getObjectIdKey());

        var fields = new LinkedHashMap<String, String>();
        fields.put("Заметки", "\n");

        for (var note : notes) {
            fields.put(note.name, note.description + "\n");
        }

        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();
        return new NotesState(parameters, fields, buttons, possibleTransitions, "Notes");
    }

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();
        
        buttons.put("Назад", "gotocharacter");

        return buttons;
    }

    @Override
    public HashMap<String, CombineKey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, CombineKey>();
        
        possibleTransitions.put("add", new CombineKey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Notes"));
        possibleTransitions.put("gotocharacter", new CombineKey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Character"));

        return possibleTransitions;
    }
}
