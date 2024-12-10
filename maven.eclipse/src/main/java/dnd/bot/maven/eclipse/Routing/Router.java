package dnd.bot.maven.eclipse.Routing;

import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.FeaturesState;
import dnd.bot.maven.eclipse.Routing.States.ItemsState;
import dnd.bot.maven.eclipse.Routing.States.PossessionsState;
import dnd.bot.maven.eclipse.Routing.States.SpellsState;
import dnd.bot.maven.eclipse.Routing.States.UserState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;

public class Router {
    private GeneratorManager manager;
    private BaseState currentState;

    public Router(String userId) {
        this.manager = new GeneratorManager();
        this.currentState = manager.getGeneratorByStateName("gotouser")
                                   .generateState(new Combinekey(userId));
    }

    public void makeTransition(String callback) {
        var callbackParts = callback.split(":");

        if (callbackParts.length > 1) {
            makeTransitionForCompositeCallback(callbackParts);
        } else {
            makeTransitionForSimpleCallback(callback);
        }        
    } 

    private void makeTransitionForCompositeCallback(String[] callbackParts) {

        var combineKey = currentState.getPossibleTransitions().get(callbackParts[1]);
        
        if (combineKey != null) {
            this.currentState = manager.getGeneratorByStateName(callbackParts[0]).generateState(combineKey);
        }
    }

    private void makeTransitionForSimpleCallback(String callback) {
        switch (callback) {
            case "addcharacter":
                var userState = (UserState)currentState;
                userState.addCharacter(manager);
                break;
            case "addfeature":
                var featuresState = (FeaturesState)currentState;
                featuresState.addFeature(manager);;
                break;
            case "additem":
                var itemsState = (ItemsState)currentState;
                itemsState.addItem(manager);
                break;
            case "addpossession":
                var possessionsState = (PossessionsState)currentState;
                possessionsState.addPossession(manager);
                break;
            case "addspell":
                var spellsState = (SpellsState)currentState;
                spellsState.addSpell(manager);
                break;
            default:
                break;
        }


        var combineKey = currentState.getPossibleTransitions().get(callback);

        if (combineKey != null) {
            this.currentState = manager.getGeneratorByStateName(callback).generateState(combineKey);
        }
    }

    public BaseState getCurrentState() {
        return currentState;
    }
}   