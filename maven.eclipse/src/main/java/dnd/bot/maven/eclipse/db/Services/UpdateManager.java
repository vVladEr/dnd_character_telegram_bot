package dnd.bot.maven.eclipse.db.Services;

import java.util.HashMap;

import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.db.Models.UpdateFieldRequest;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.GradeCompositeKey;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.StatCompositeKey;
import dnd.bot.maven.eclipse.db.repos.Interfaces.IFieldUpdatable;
import dnd.bot.maven.eclipse.db.repos.Interfaces.IInnerFieldUpdatable;

public class UpdateManager {

    private HashMap<String, IFieldUpdatable<ObjectId>> basicRepos;

    private IInnerFieldUpdatable<StatCompositeKey> statRepo;

    private IInnerFieldUpdatable<GradeCompositeKey> gradeRepo;

    public UpdateManager(ReposStorage rs)
    {
        statRepo = rs.getStatRepository();
        gradeRepo = rs.getGradesRepository();
        basicRepos = new HashMap<String, IFieldUpdatable<ObjectId>>();

        basicRepos.put("Item", rs.getItemsRepository());
        basicRepos.put("Appearence", rs.getAppearenceRepository());
        basicRepos.put("Social", rs.getSocialRepository());
        basicRepos.put("Level", rs.getLevelRepository());
        basicRepos.put("HP", rs.getHpRepository());
        basicRepos.put("Personality", rs.getPersonalityRepository());
        basicRepos.put("Features", rs.getFeaturesRepository());
        basicRepos.put("Possesions", rs.getPossesionsRepository());
        
    }

    public void UpdateField(UpdateFieldRequest request)
    {
        if (basicRepos.containsKey(request.stateName))
        {
            basicRepos.get(request.stateName)
            .UpdateField(
                request.key.getObjectIdKey(),
                 request.fieldName,
                  request.fieldValue);
            return;
        }

        if (request.stateName == "Stat")
        {
            if (request.innerFieldName == null)
            {
                statRepo.UpdateField(request.key.getStatCompositeKey(), request.fieldName, request.fieldValue);
            }
            else
            {
                statRepo.UpdateInnerField(
                    request.key.getStatCompositeKey(),
                    request.fieldName,
                    request.innerFieldName,
                    request.fieldValue);
            }
            return;
        }

        if (request.stateName == "Grade")
        {
            if (request.innerFieldName == null)
            {
                gradeRepo.UpdateField(request.key.getGradeCompositeKey(), request.fieldName, request.fieldValue);
            }
            else
            {
                gradeRepo.UpdateInnerField(
                    request.key.getGradeCompositeKey(),
                    request.fieldName,
                    request.innerFieldName,
                    request.fieldValue);
            }
            return;
        }
    }
}
