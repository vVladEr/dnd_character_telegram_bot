package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.ItemsState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;

public class ItemsGenerator extends BaseGenerator {
    private Combinekey parameters;
    private GeneratorManager manager;

    public ItemsGenerator(GeneratorManager manager) {
        this.manager = manager;
    }

    @Override
    public BaseState generateState(Combinekey parameters) {
        var repo = this.manager.getReposStorage().getItemsRepository();
        this.parameters = parameters;
        var items = repo.getCharactersItems(parameters.getObjectIdKey());
        
        var fields = new LinkedHashMap<String, String>();
        fields.put("Предметы", "\n");

        for (var item : items) {
            fields.put(item.name, item.description + " " + item.amount + "\n");
        }

        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();

        return new ItemsState(parameters, fields, buttons, possibleTransitions, "Items");
    }

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();
        
        buttons.put("Назад", "gotoinventory");

        return buttons;
    }

    @Override
    public HashMap<String, Combinekey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, Combinekey>();
        
        possibleTransitions.put("gotoinventory", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Inventory"));
        possibleTransitions.put("add", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Items"));
        possibleTransitions.put("update", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Items"));

        return possibleTransitions;
    }
}
