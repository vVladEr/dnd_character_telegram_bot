package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.ItemsState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.CombineKey;

public class ItemsGenerator extends BaseGenerator {
    private CombineKey parameters;
    private GeneratorManager manager;

    public ItemsGenerator(GeneratorManager manager) {
        this.manager = manager;
    }

    @Override
    public BaseState generateState(CombineKey parameters) {
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
        possibleTransitions.put("add", new CombineKey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Items"));
        return new ItemsState(parameters, fields, buttons, possibleTransitions, "Items");
    }

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();
        
        buttons.put("Назад", "gotoinventory");

        return buttons;
    }

    @Override
    public HashMap<String, CombineKey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, CombineKey>();
        
        possibleTransitions.put("gotoinventory", new CombineKey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Inventory"));

        return possibleTransitions;
    }
}
