package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.data.entityparts.EntityPart;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();

    private float[] colorArray = {1, 1, 1, 1};
    private float[] shapeX;
    private float[] shapeY;
    private float radius;
    private boolean isColliding;
    private float size;
    private float mass;
    private Map<Class, EntityPart> parts;

    public Entity() {
        parts = new ConcurrentHashMap<>();
    }

    public void add(EntityPart part) {
        parts.put(part.getClass(), part);
    }

    public void remove(Class partClass) {
        parts.remove(partClass);
    }

    public <E extends EntityPart> E getPart(Class partClass) {
        return (E) parts.get(partClass);
    }

    public void setRadius(float r) {
        this.radius = r;
    }

    public float getRadius() {
        return radius;
    }

    public String getID() {
        return ID.toString();
    }

    public float[] getShapeX() {
        return shapeX;
    }

    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
    }

    public float[] getShapeY() {
        return shapeY;
    }

    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }


    public float[] getColorArray() {
        return colorArray;
    }

    public void setColorArray(float r, float g, float b, float a) {
        this.colorArray = new float[]{r,g,b,a};
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public boolean isColliding() {
        return isColliding;
    }

    public void setColliding(boolean colliding) {
        isColliding = colliding;
    }
}
