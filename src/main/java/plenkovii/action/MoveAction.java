package plenkovii.action;

import plenkovii.Map;
import plenkovii.entity.Entity;
import plenkovii.entity.mobile.Creature;

import java.util.List;

public class MoveAction extends Action {

    @Override
    public void perform(Map map, Class<? extends Entity> targetClass) {
        List<Creature> creaturesOnMap = map.getCreaturesOnMap();

        for (Creature creature : creaturesOnMap) {
//            creature.makeMove2();
        }
    }
}
