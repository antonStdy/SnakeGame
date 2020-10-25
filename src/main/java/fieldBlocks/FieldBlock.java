package fieldBlocks;

import enums.CollisionResults;

import java.awt.*;

public abstract class FieldBlock {
    private Point coordinates;

    public FieldBlock(Point coordinates) {
        this.coordinates = coordinates;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void updateCoordinates(Point newCoordinates) {
        coordinates.setLocation(newCoordinates);
    }

    public boolean at(Point coordinates) {
        return this.coordinates.equals(coordinates);
    }

    public abstract CollisionResults collideWithSnake();

    public abstract void draw(Graphics g, int unitSize);
}
