package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.AsteroidMovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import static dk.sdu.mmmi.cbse.common.data.GameKeys.*;

public class AsteroidControlSystem implements IEntityProcessingService {

    private double pi = Math.PI;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            AsteroidMovingPart movingPart = asteroid.getPart(AsteroidMovingPart.class);
            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);
            updateShape(asteroid);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = new float[6];
        float[] shapey = new float[6];
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 12 * entity.getSize());
        shapey[0] = (float) (y + Math.sin(radians) * 12 * entity.getSize());

        shapex[1] = (float) (x + Math.cos(radians + 1.0472f) * 12 * entity.getSize());
        shapey[1] = (float) (y + Math.sin(radians + 1.0472f) * 12 * entity.getSize());

        shapex[2] = (float) (x + Math.cos(radians + 2 * 1.0472f) * 12 * entity.getSize());
        shapey[2] = (float) (y + Math.sin(radians + 2 * 1.0472f) * 12 * entity.getSize());

        shapex[3] = (float) (x + Math.cos(radians + 3 * 1.0472f) * 12 * entity.getSize());
        shapey[3] = (float) (y + Math.sin(radians + 3 * 1.0472f) * 12 * entity.getSize());

        shapex[4] = (float) (x + Math.cos(radians + 4 * 1.0472f) * 12 * entity.getSize());
        shapey[4] = (float) (y + Math.sin(radians + 4 * 1.0472f) * 12 * entity.getSize());

        shapex[5] = (float) (x + Math.cos(radians + 5 * 1.0472f) * 12 * entity.getSize());
        shapey[5] = (float) (y + Math.sin(radians + 5 * 1.0472f) * 12 * entity.getSize());


        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
