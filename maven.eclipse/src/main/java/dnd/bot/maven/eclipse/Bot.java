package dnd.bot.maven.eclipse;

import java.util.LinkedHashMap;
import java.util.Map;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import dnd.bot.maven.eclipse.Interfaces.ICommand;
import dnd.bot.maven.eclipse.Commands.*;

public class Bot extends TelegramLongPollingBot{

	private static Map<String, ICommand> commands;
	
	public void Init() {
		commands = new LinkedHashMap<String, ICommand>();
		commands.put("/info", new InfoCommand());
		commands.put("/authors", new AuthorsCommand());
		commands.put("/help", new HelpCommand(commands));
	}
	
	@Override
	public void onUpdateReceived(Update update) {
	    var msg = update.getMessage();
	    var user = msg.getFrom();
	    var id = user.getId();
	    if(msg.isCommand()) 
	    {
	    	var command = commands.get(msg.getText());
	    	System.out.println(command);
	    	sendText(id, command.executeCommand());
	    }
	    else 
	    {
		    sendText(id, msg.getText());
	    }
	}

	@Override
	public String getBotUsername() {
		return "TestBot";
	}

	@Override
	public String getBotToken() {
		return System.getenv("TgToken");
	}
	
	public void sendText(Long who, String what){
		   SendMessage sm = SendMessage.builder()
		                    .chatId(who.toString()) //Who are we sending a message to
		                    .text(what).build();    //Message content
		   try {
		        execute(sm);                        //Actually sending the message
		   } catch (TelegramApiException e) {
		        throw new RuntimeException(e);      //Any error will be printed here
		   }
		}
}
