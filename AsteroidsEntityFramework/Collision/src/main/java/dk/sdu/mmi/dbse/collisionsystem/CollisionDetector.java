package dk.sdu.mmi.dbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.AsteroidMovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            for (Entity f : world.getEntities()) {
                if (e.getID().equals(f.getID())) {
                    continue;
                }
                if (boxCollision(e, f)) {
                    if (e.getPart(AsteroidMovingPart.class) != null && f.getPart(AsteroidMovingPart.class) != null) {
                        if (newCircleCollision(e, f)) {
                            System.out.println("E and F are asteroids");
                            calculateX(e, f);
                        }
                    }
                }
            }
        }
    }

    private void calculateX(Entity a, Entity b) {
        AsteroidMovingPart mPartA = a.getPart(AsteroidMovingPart.class);
        AsteroidMovingPart mPartB = b.getPart(AsteroidMovingPart.class);

        float newVelX1 = (mPartA.getSpeedX() * (a.getMass() - b.getMass()) + (2 * b.getMass() * mPartB.getSpeedX())) / (a.getMass() + b.getMass());
        float newVelY1 = (mPartA.getSpeedY() * (a.getMass() - b.getMass()) + (2 * b.getMass() * mPartB.getSpeedY())) / (a.getMass() + b.getMass());

        float newVelX2 = (mPartB.getSpeedX() * (b.getMass() - a.getMass()) + (2 * a.getMass() * mPartA.getSpeedX())) / (a.getMass() + b.getMass());
        float newVelY2 = (mPartB.getSpeedY() * (b.getMass() - a.getMass()) + (2 * a.getMass() * mPartA.getSpeedY())) / (a.getMass() + b.getMass());

        mPartA.setSpeedX(newVelX1);
        mPartA.setSpeedY(newVelY1);
        mPartB.setSpeedX(newVelX2);
        mPartB.setSpeedY(newVelY2);
    }


    private boolean newCircleCollision(Entity e, Entity f) {
        PositionPart ep = e.getPart(PositionPart.class);
        PositionPart fp = f.getPart(PositionPart.class);

        if ((ep.getX() - fp.getX()) * (ep.getX() - fp.getX())
                + (ep.getY() - fp.getY()) * (ep.getY() - fp.getY())
                < (e.getRadius() + f.getRadius()) * (e.getRadius() + f.getRadius())) {
            return true;
        }

        return false;
    }

    private boolean boxCollision(Entity a, Entity b) {
        PositionPart pPartA = a.getPart(PositionPart.class);
        PositionPart pPartB = b.getPart(PositionPart.class);


        if (pPartA.getX() + a.getRadius() + b.getRadius() > pPartB.getX()
                && pPartA.getX() < pPartB.getX() + a.getRadius() + b.getRadius()
                && pPartA.getY() + a.getRadius() + b.getRadius() > pPartB.getY()
                && pPartA.getY() < pPartB.getY() + a.getRadius() + b.getRadius()) {
            return true;
        }
        return false;
    }

}
