package dnd.bot.maven.eclipse.db.Services;

import java.util.HashMap;

import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.db.Models.UpdateFieldRequest;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.GradeCompositeKey;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.StatCompositeKey;
import dnd.bot.maven.eclipse.db.repos.Interfaces.FieldUpdatable;
import dnd.bot.maven.eclipse.db.repos.Interfaces.InnerFieldUpdatable;

public class UpdateManager {

    private HashMap<String, FieldUpdatable<ObjectId>> basicRepos;

    private InnerFieldUpdatable<StatCompositeKey> statRepo;

    private InnerFieldUpdatable<GradeCompositeKey> gradeRepo;

    public UpdateManager(ReposStorage rs) {
        statRepo = rs.getStatRepository();
        gradeRepo = rs.getGradesRepository();
        basicRepos = new HashMap<String, FieldUpdatable<ObjectId>>();

        basicRepos.put("Items", rs.getItemsRepository());
        basicRepos.put("Appearance", rs.getAppearenceRepository());
        basicRepos.put("Social", rs.getSocialRepository());
        basicRepos.put("Level", rs.getLevelRepository());
        basicRepos.put("HP", rs.getHpRepository());
        basicRepos.put("Personality", rs.getPersonalityRepository());
        basicRepos.put("Features", rs.getFeaturesRepository());
        basicRepos.put("Possesions", rs.getPossesionsRepository());
        basicRepos.put("Characters", rs.getCharacterRepository());

    }

    public void updateField(UpdateFieldRequest request) {
        if (basicRepos.containsKey(request.stateName)) {
            basicRepos.get(request.stateName)
                    .updateField(
                            request.key.getObjectIdKey(),
                            request.fieldName,
                            request.fieldValue);
            return;
        }

        if (request.stateName == "Stat") {
            if (request.innerFieldName == null) {
                statRepo.updateField(request.key.getStatCompositeKey(), request.fieldName, request.fieldValue);
            } else {
                statRepo.updateInnerField(
                        request.key.getStatCompositeKey(),
                        request.fieldName,
                        request.innerFieldName,
                        request.fieldValue);
            }
            return;
        }

        if (request.stateName == "Grade") {
            if (request.innerFieldName == null) {
                gradeRepo.updateField(request.key.getGradeCompositeKey(), request.fieldName, request.fieldValue);
            } else {
                gradeRepo.updateInnerField(
                        request.key.getGradeCompositeKey(),
                        request.fieldName,
                        request.innerFieldName,
                        request.fieldValue);
            }
            return;
        }
    }
}
