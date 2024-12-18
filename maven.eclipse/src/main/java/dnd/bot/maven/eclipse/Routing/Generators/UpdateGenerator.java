package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.ArrayList;
import java.util.HashMap;

import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.UpdateState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.CombineKey;
import dnd.bot.maven.eclipse.db.Services.UpdateManager;

public class UpdateGenerator extends BaseGenerator {
    private UpdateManager updateManager;
    private BaseState updatedState;

    public UpdateGenerator(UpdateManager updateManager, BaseState updatedState) {
        this.updateManager = updateManager;
        this.updatedState = updatedState;
    }

    @Override
    public BaseState generateState(CombineKey parameters) {
        var fields = new ArrayList<String>();
        
        for (var field : updatedState.fields.keySet()) {
            fields.add(field);
        }
    
        return new UpdateState(fields, parameters, updateManager);
    }

    @Override
    public HashMap<String, String> getFormattedButtons() {
        throw new UnsupportedOperationException("Unimplemented method 'getFormattedButtons'");
    }

    @Override
    public HashMap<String, CombineKey> getPossibleTransitions() {
        throw new UnsupportedOperationException("Unimplemented method 'getPossibleTransitions'");
    }
}