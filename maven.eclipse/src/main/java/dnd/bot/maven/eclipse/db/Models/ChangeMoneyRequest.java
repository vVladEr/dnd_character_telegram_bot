package dnd.bot.maven.eclipse.db.Models;

import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.db.Models.Money.MoneyTypes;

public class ChangeMoneyRequest {

    private boolean isAddOperation;

    private LinkedHashMap<MoneyTypes, Integer> coins;

    public ChangeMoneyRequest(boolean isAddOperation, LinkedHashMap<MoneyTypes, Integer> coins)
    {
        this.coins = coins;
        this.isAddOperation = isAddOperation;
    }

    public boolean isAddOperation() {
        return isAddOperation;
    }

    public LinkedHashMap<MoneyTypes, Integer> getCoins() {
        return coins;
    }
}
