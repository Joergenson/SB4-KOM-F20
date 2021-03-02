package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends SpaceObject{
    private float lifeTime, lifeTimer;
    public boolean remove;

    public Bullet(float x, float y, float radians, float speed, int size) {
        this.x = x;
        this.y = y;
        this.radians = radians;


        this.speed = speed;
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;

        width = height = size;

        this.lifeTimer = 0;
        this.lifeTime = 1;
    }

    public void update(float dt) {
        x += dx * dt;
        y += dy * dt;

        wrap();

        lifeTimer += dt;
        if (lifeTimer > lifeTime) {
            remove = true;
        }
    }

    public void draw(ShapeRenderer sr) {
        sr.setColor(1,1,1,1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.circle(x - width/2,y - height/2,2);
        sr.end();
    }
}
