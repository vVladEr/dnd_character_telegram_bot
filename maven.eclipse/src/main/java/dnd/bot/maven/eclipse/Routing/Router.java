package dnd.bot.maven.eclipse.Routing;

import dnd.bot.maven.eclipse.Routing.States.AddState;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.IAddable;
import dnd.bot.maven.eclipse.Routing.States.UpdateState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;

public class Router {
    private GeneratorManager manager;
    private BaseState currentState;
    public Boolean isAddMode;
    public Boolean isUpdateMode;
    public Boolean isSelecteField;

    public Router(String userId) {
        manager = new GeneratorManager();
        currentState = manager.getGeneratorByStateName("gotouser")
                                   .generateState(new Combinekey(userId));
        isUpdateMode = false;
        isAddMode = false;
        isSelecteField = false;
    }

    public void makeTransition(String callback) {
        if (isAddMode) {
            processingAddValue(callback);
            return;
        }

        if (isUpdateMode){
            processingUpdateValue(callback);
            return;
        }

        var callbackParts = callback.split(":");

        if (callbackParts.length > 1) {
            makeTransitionForCompositeCallback(callbackParts);
        } else {
            makeTransitionForSimpleCallback(callback);
        }        
    } 

    private void processingAddValue(String value) {
        var addState = (AddState)currentState;
        
        addState.putValue(value);

        if (!addState.tryMoveNext()) {
            var combineKey = addState.getSavedData();
            currentState = manager.getGeneratorByStateName(String.format("goto%s", combineKey.getStateName().toLowerCase())).generateState(combineKey);
            var addableState = (IAddable)currentState;
            addableState.addElement(manager, combineKey.getNecessaryFields());
            isAddMode = false;
        }
    }

    private void processingUpdateValue(String value) {
        var splittedValue = value.split(":");
        var updateState = (UpdateState)currentState;

        if (splittedValue.length == 2) {
            if (splittedValue[0].equals("save")) {
                updateState.saveUpdates();
            }

            var combineKey = updateState.getPossibleTransitions().get(splittedValue[1]);
            currentState = manager.getGeneratorByStateName(splittedValue[1]).generateState(combineKey);
            isSelecteField = false;
            isUpdateMode = false;

        } else {
            if (!isSelecteField) {
                updateState.putUpdatedField(splittedValue[0]);
                isSelecteField = true;
                return;
            } 

            updateState.putValue(splittedValue[0]);
            isSelecteField = false;
        }
    }

    private void makeTransitionForCompositeCallback(String[] callbackParts) {

        var combineKey = currentState.getPossibleTransitions().get(callbackParts[1]);
        
        if (combineKey != null) {
            currentState = manager.getGeneratorByStateName(callbackParts[0]).generateState(combineKey);
        }
    }

    private void makeTransitionForSimpleCallback(String callback) {
        Combinekey combineKey;

        if (callback.equals("add")) {
            isAddMode = true;
        } else if (callback.equals("update")) {
            isUpdateMode = true;
        } else {
            isAddMode = false;
            isUpdateMode = false;
            isSelecteField = false;
        }

        combineKey = currentState.getPossibleTransitions().get(callback);

        if (combineKey != null) {
            this.currentState = manager.getGeneratorByStateName(callback).generateState(combineKey);
        }
    }

    public BaseState getCurrentState() {
        return currentState;
    }
}   