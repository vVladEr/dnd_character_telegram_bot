package dnd.bot.maven.eclipse.db.Services.Money;

import java.util.LinkedHashMap;

import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.db.Models.Money.MoneyTypes;
import dnd.bot.maven.eclipse.db.repos.MongoItemsRepository;

public class MoneyManager {

    private MongoItemsRepository itemRepos;

    public MoneyManager(MongoItemsRepository repo)
    {
        itemRepos = repo;
    }

    public LinkedHashMap<MoneyTypes, Integer> getCharacterCoins(ObjectId characterId)
    {
        var money = itemRepos.getCharactersMoney(characterId).amount;
        return MoneyConverter.ConvertMoneyToCoins(money);
    }

    public void updateCharacterCoins(ObjectId characterId, LinkedHashMap<MoneyTypes, Integer> coins) throws IllegalArgumentException
    {
        var moneyDbo = itemRepos.getCharactersMoney(characterId);
        var moneyInput = MoneyConverter.ConvertCoinsToMoney(coins);
        if (moneyInput >= 0)
        {
            
        }


    }
}
