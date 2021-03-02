package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.AsteroidMovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    private Entity player;
    private Random random = new Random();

    public AsteroidPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    private Entity createPlayerShip(GameData gameData) {
        float x = gameData.getDisplayWidth() * random.nextFloat();
        float y = gameData.getDisplayHeight() * random.nextFloat();
        float radians = 3.1415f * 2 * random.nextFloat();

        Entity asteroid = new Asteroid();
        asteroid.setColorArray(0.725f, 0.894f, 0.769f, 1);
        asteroid.setSize(random.nextInt(3) + 1);
        asteroid.setRadius(12 * asteroid.getSize());
        asteroid.setMass(asteroid.getRadius());
        asteroid.add(new AsteroidMovingPart(random.nextInt(10)+5,random.nextInt(10)+5));
        asteroid.add(new PositionPart(x, y, radians));

        return asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
