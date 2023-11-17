package be.swsb.coderetreat;

public class Field {

    private final int WIDTH = 10;
    private final int HEIGHT = 10;

    public boolean isOutsideField(Position position) {
        return  position.x() < getMinX()
                || position.x() > getMaxX()
                || position.y() < getMinY()
                || position.y() > getMaxY();
    }

    private int getMinX() {
        return 0;
    }

    private int getMinY() {
        return 0;
    }

    public int getMaxX() {
        return WIDTH -1;
    }

    public int getMaxY() {
        return HEIGHT -1;
    }
}
