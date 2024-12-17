package dnd.bot.maven.eclipse.Money;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.LinkedHashMap;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.Models.ChangeMoneyRequest;
import dnd.bot.maven.eclipse.db.Models.Money.MoneyTypes;
import dnd.bot.maven.eclipse.db.Services.CharacterCreater;
import dnd.bot.maven.eclipse.db.Services.ReposStorage;
import dnd.bot.maven.eclipse.db.Services.Money.MoneyManager;

public class MoneyManagerTest {

    private static MoneyManager moneyManager;
    private static ReposStorage reposStorage;
    private static ObjectId characterId;
    private static LinkedHashMap<MoneyTypes, Integer> coins;

    @BeforeAll
    public static void setUp(){
        reposStorage = new ReposStorage("test-db");
        moneyManager = new MoneyManager(reposStorage.getItemsRepository());
        var characterCreater = new CharacterCreater(reposStorage);
        var userId = new ObjectId().toString();
        characterId = characterCreater.createCharacter(userId);
        coins = new LinkedHashMap<>();
        coins.put(MoneyTypes.Copper, 1);
        coins.put(MoneyTypes.Silver, 1);
        coins.put(MoneyTypes.Gold, 1);
        coins.put(MoneyTypes.Platinum, 1);
    }

    @AfterEach
    public void clearBalance(){
        var itemRepo = reposStorage.getItemsRepository();
        var item = itemRepo.getCharactersMoney(characterId);
        itemRepo.updateField(item.id, "amount", 0);
    }

    @Test
    public void addMoneyTest(){
        var req = new ChangeMoneyRequest(true, coins);
        moneyManager.updateCharacterCoins(characterId, req);
        var newCoins = moneyManager.getCharacterCoins(characterId);
        assertEquals(coins.get(MoneyTypes.Copper), newCoins.get(MoneyTypes.Copper));
        assertEquals(coins.get(MoneyTypes.Silver), newCoins.get(MoneyTypes.Copper));
        assertEquals(coins.get(MoneyTypes.Gold), newCoins.get(MoneyTypes.Copper));
        assertEquals(coins.get(MoneyTypes.Platinum), newCoins.get(MoneyTypes.Copper));
    }

    @Test
    public void removeMoneyTest(){
        var req = new ChangeMoneyRequest(true, coins);
        moneyManager.updateCharacterCoins(characterId, req);
        var updatedCoins = moneyManager.getCharacterCoins(characterId);

        assertEquals(coins.get(MoneyTypes.Copper), updatedCoins.get(MoneyTypes.Copper));
        assertEquals(coins.get(MoneyTypes.Silver), updatedCoins.get(MoneyTypes.Copper));
        assertEquals(coins.get(MoneyTypes.Gold), updatedCoins.get(MoneyTypes.Copper));
        assertEquals(coins.get(MoneyTypes.Platinum), updatedCoins.get(MoneyTypes.Copper));

        var removeReq = new ChangeMoneyRequest(false, coins);
        moneyManager.updateCharacterCoins(characterId, removeReq);
        updatedCoins = moneyManager.getCharacterCoins(characterId);

        assertEquals(0, updatedCoins.get(MoneyTypes.Copper));
        assertEquals(0, updatedCoins.get(MoneyTypes.Copper));
        assertEquals(0, updatedCoins.get(MoneyTypes.Copper));
        assertEquals(0, updatedCoins.get(MoneyTypes.Copper));
    }

    @Test
    public void negativeBalanceTest(){
        var req = new ChangeMoneyRequest(false, coins);
        assertThrowsExactly(IllegalArgumentException.class, () -> {
             moneyManager.updateCharacterCoins(characterId, req);
            });
        
    }
}
