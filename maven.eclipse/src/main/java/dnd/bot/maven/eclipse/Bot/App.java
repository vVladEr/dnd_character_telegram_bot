package dnd.bot.maven.eclipse.Bot;

import java.util.UUID;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import dnd.bot.maven.eclipse.db.dbConnector;
import dnd.bot.maven.eclipse.db.repos.MongoUserRepository;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws TelegramApiException{
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot = new Bot();                  //We moved this line out of the register method, to access it later
        bot.Init();
        botsApi.registerBot(bot);             //The L just turns the Integer into a Long
    }
}
