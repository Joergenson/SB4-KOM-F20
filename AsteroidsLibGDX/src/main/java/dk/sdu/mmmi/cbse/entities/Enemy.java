package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class Enemy extends SpaceObject {

    private final int MAX_BULLETS = 4;
    private ArrayList<Bullet> bullets;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean shoot;
    private final float maxSpeed;
    private final float acceleration;
    private final float deceleration;

    private final Random random = new Random();

    public Enemy(ArrayList<Bullet> bullets) {

        this.bullets = bullets;

        x = (float) (Game.WIDTH / 2.0);
        y = (float) (Game.HEIGHT / 2.0);

        maxSpeed = 150;
        acceleration = 100;
        deceleration = 10;

        shapex = new float[4];
        shapey = new float[4];

        radians = 3.1415f / 2;
        rotationSpeed = 1;
        Timer t = new Timer();
    }

    public void shoot(){
        if (bullets.size() == MAX_BULLETS) return;
        bullets.add(new Bullet(x,y,radians,350,2));
        shoot = false;
    }

    public void logic() {
        long time = Instant.now().toEpochMilli() / 1000;
        if (time % 3 == 0) {
            up = true;
            int move = random.nextInt(1);
            if (move == 0) {
                left = true;
                right = false;
            } else {
                right = true;
                left = false;
            }
        }
        if (time % 10 == 0) {
            up = false;
        }
        if (time % 11 == 0) {
            if (!shoot) {
                shoot = true;
                System.out.println("shoot");
                System.out.println(time);
                shoot();
            }
        }
    }

    private void setShape() {
        shapex[0] = x + MathUtils.cos(radians) * 8;
        shapey[0] = y + MathUtils.sin(radians) * 8;

        shapex[1] = x + MathUtils.cos((float) (radians - 4 * Math.PI / 5)) * 8;
        shapey[1] = y + MathUtils.sin((float) (radians - 4 * Math.PI / 5)) * 8;

        shapex[2] = x + MathUtils.cos((float) (radians + Math.PI)) * 5;
        shapey[2] = y + (MathUtils.sin(radians + 3.1415f) * 5);

        shapex[3] = x + MathUtils.cos((float) (radians + 4 * Math.PI / 5)) * 8;
        shapey[3] = y + MathUtils.sin((float) (radians + 4 * Math.PI / 5)) * 8;

    }

    public void update(float dt) {
        // turning
        if (left) {
            radians += rotationSpeed * dt;
        } else if (right) {
            radians -= rotationSpeed * dt;
        }

        // accelerating
        if (up) {
            dx += MathUtils.cos(radians) * acceleration * dt;
            dy += MathUtils.sin(radians) * acceleration * dt;
        }

        // deceleration
        float vec = (float) Math.sqrt(dx * dx + dy * dy);
        if (vec > 0) {
            dx -= (dx / vec) * deceleration * dt;
            dy -= (dy / vec) * deceleration * dt;
        }
        if (vec > maxSpeed) {
            dx = (dx / vec) * maxSpeed;
            dy = (dy / vec) * maxSpeed;
        }

        // set position
        x += dx * dt;
        y += dy * dt;

        // set shape
        setShape();

        // screen wrap
        wrap();
        logic();
    }

    public void draw(ShapeRenderer sr) {

        sr.setColor(1, 0, 0, 1);

        sr.begin(ShapeRenderer.ShapeType.Line);

        for (int i = 0, j = shapex.length - 1;
             i < shapex.length;
             j = i++) {

            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);

        }

        sr.end();
    }

}
