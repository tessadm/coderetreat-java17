package be.swsb.coderetreat;

public enum Direction {
    HORIZONTAL {
        @Override
        public Position getNextPosition(Position position) {
            return new Position(position.x() + 1, position.y());
        }
    },
    VERTICAL{
        @Override
        public Position getNextPosition(Position position) {
            return new Position(position.x(), position.y() + 1) ;
        }
    };

    public abstract Position getNextPosition(Position position);
}
