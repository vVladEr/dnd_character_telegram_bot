package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Interfaces.IGenerator;
import dnd.bot.maven.eclipse.db.Models.dbo.IDbo;

public abstract class BaseGenerator implements IGenerator {
    
    public LinkedHashMap<String, String> getFormattedFields(IDbo dbo) {
        var formattedFields = new LinkedHashMap<String, String>();

        for (var field : dbo.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                formattedFields.put(
                    field.getName(), 
                    String.format("%s", field.get(dbo))
                );
            } catch (IllegalAccessException e) {
                formattedFields.put(field.getName(), "Access denied"); 
            }
        }

        return formattedFields;
    }
}
