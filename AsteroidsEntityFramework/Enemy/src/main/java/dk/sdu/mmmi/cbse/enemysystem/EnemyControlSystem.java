package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import static dk.sdu.mmmi.cbse.common.data.GameKeys.*;

public class EnemyControlSystem implements IEntityProcessingService {

    private double pi = Math.PI;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);

//            movingPart.setLeft(gameData.getKeys().isDown(RIGHT));
//            movingPart.setRight(gameData.getKeys().isDown(LEFT));
//            movingPart.setUp(gameData.getKeys().isDown(UP));


            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            updateShape(enemy);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = new float[4];
        float[] shapey = new float[4];
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * pi / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * pi / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + pi) * 15);
        shapey[2] = (float) (y + Math.sin(radians + pi) * 15);

        shapex[3] = (float) (x + Math.cos(radians + 4 * pi / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * pi / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
