package dnd.bot.maven.eclipse.Money;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import dnd.bot.maven.eclipse.db.Models.Money.MoneyTypes;
import dnd.bot.maven.eclipse.db.Services.Money.MoneyConverter;

public class MoneyConverterTests {

    @Test
    public void convertCoinsToCurrencyTest(){
        var coins = new LinkedHashMap<MoneyTypes, Integer>();
        var checkSum = 1 * 1000 +  2 * 100 + 5 * 10 + 101;
        coins.put(MoneyTypes.Copper, 101);
        coins.put(MoneyTypes.Silver, 5);
        coins.put(MoneyTypes.Gold, 2);
        coins.put(MoneyTypes.Platinum, 1);
        var sum = MoneyConverter.convertCoinsToMoney(coins);
        assertEquals(checkSum, sum);
    }

    @Test
    public void convertCurrencyToCoinsTest(){
        var sum = 1 * 1000 +  2 * 100 + 5 * 10 + 101;
        var coins = MoneyConverter.convertMoneyToCoins(sum);
        assertEquals(1, coins.get(MoneyTypes.Copper));
        assertEquals(5, coins.get(MoneyTypes.Silver));
        assertEquals(3, coins.get(MoneyTypes.Gold));
        assertEquals(1, coins.get(MoneyTypes.Platinum));
    }
}
