package dnd.bot.maven.eclipse.db.Services.Money;

import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.db.Models.Money.MoneyTypes;

public class MoneyConverter {

    public static LinkedHashMap<MoneyTypes, Integer> ConvertMoneyToCoins(int money)
    {
        var coins = new LinkedHashMap<MoneyTypes, Integer>();
        coins.put(MoneyTypes.Copper, money % 10);
        coins.put(MoneyTypes.Silver, (money / 10) % 10);
        coins.put(MoneyTypes.Gold, (money / 100)  % 10);
        coins.put(MoneyTypes.Platinum, money / 1000);
        return coins;
    }

    public static int  ConvertCoinsToMoney(LinkedHashMap<MoneyTypes, Integer> coins)
    {
        var money = 0;
        money += coins.get(MoneyTypes.Copper);
        money += coins.get(MoneyTypes.Silver)  * 10;
        money += coins.get(MoneyTypes.Gold) * 100;
        money += coins.get(MoneyTypes.Platinum) * 1000;
        return money;
    }
}
