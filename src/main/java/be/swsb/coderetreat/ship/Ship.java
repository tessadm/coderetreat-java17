package be.swsb.coderetreat.ship;

import be.swsb.coderetreat.Direction;
import be.swsb.coderetreat.Position;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public abstract class Ship {
    private final int length;
    private final Position startPosition;
    private final Direction direction;
    private final Set<Position> hits = new HashSet<>();

    public Ship(
            int length,
            Position startPosition,
            Direction direction) {
        this.length = length;
        this.startPosition = startPosition;
        this.direction = direction;
    }

    public Stream<Position> getOccupiedPositions() {
        List<Position> positions = new ArrayList<>(List.of(startPosition));
        for(int i = 0; i< length -1; i++) {
            positions.add(direction.getNextPosition(positions.get(positions.size() -1)));
        }
        return positions.stream();
    }

    public Stream<Position> getHits() {
        return hits.stream();
    }

    public boolean isHit() {
        return getHits().findAny().isPresent();
    }

    public void receiveShot(Position position) {
        if (getOccupiedPositions().anyMatch(occupied -> Objects.equals(occupied, position))) {
            hits.add(position);
        }
    }

    public Optional<String> print(Position position) {
        if (hits.stream().anyMatch(hit -> hit.equals(position))) {
            return of("🔥");
        } else if (getOccupiedPositions().anyMatch(pos -> pos.equals(position))) {
            return of("⛴️");
        } else {
            return empty();
        }
    }
}
