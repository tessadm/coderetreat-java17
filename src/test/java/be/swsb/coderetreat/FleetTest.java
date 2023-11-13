package be.swsb.coderetreat;


import be.swsb.coderetreat.ship.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static be.swsb.coderetreat.Direction.HORIZONTAL;
import static be.swsb.coderetreat.Direction.VERTICAL;
import static org.assertj.core.api.Assertions.*;

public class FleetTest {

    @Nested
    class Validation {
        @Test
        void aValidFleetCanBeCreated() {
            assertThatCode(() -> new Fleet(
                    new Carrier(new Position(0, 0), HORIZONTAL),
                    new Battleship(new Position(1, 2), VERTICAL),
                    new Destroyer(new Position(7, 3), HORIZONTAL),
                    new Submarine(new Position(6, 5), VERTICAL),
                    new PatrolBoat(new Position(8, 9), HORIZONTAL)
            )).doesNotThrowAnyException();
        }


        @Test
        void aFleetWith2SshipsOnSamePosition_throws() {
            assertThatThrownBy(() -> new Fleet(
                    new Carrier(new Position(0, 0), HORIZONTAL),
                    new Battleship(new Position(1, 0), VERTICAL)
            )).isInstanceOf(ValidationException.class);
        }


        @Nested
        class aFleetWithShipsOutOfBorders_throws {
            @Test
            void toTheLeft() {
                assertThatThrownBy(() -> new Fleet(
                        new Carrier(new Position(-1, 0), HORIZONTAL)
                )).isInstanceOf(ValidationException.class);
            }

            @Test
            void toTheRight() {
                assertThatThrownBy(() -> new Fleet(
                        new Carrier(new Position(8, 0), HORIZONTAL)
                )).isInstanceOf(ValidationException.class);
            }

            @Test
            void above() {
                assertThatThrownBy(() -> new Fleet(
                        new Carrier(new Position(0, -1), VERTICAL)
                )).isInstanceOf(ValidationException.class);
            }

            @Test
            void below() {
                assertThatThrownBy(() -> new Fleet(
                        new Carrier(new Position(0, 8), VERTICAL)
                )).isInstanceOf(ValidationException.class);
            }
        }
    }

    @Nested
    class FireShot {
        @Test
        void fire_miss_doesNothing() {
            Fleet fleet = new Fleet(
                    new Carrier(new Position(0, 0), HORIZONTAL),
                    new Battleship(new Position(1, 2), VERTICAL),
                    new Destroyer(new Position(7, 3), HORIZONTAL),
                    new Submarine(new Position(6, 5), VERTICAL),
                    new PatrolBoat(new Position(8, 9), HORIZONTAL)
            );

            fleet.receiveShot(new Position(0,5));

            assertThat(fleet.getShipsHit()).isEmpty();
        }

        @Test
        void fire_hit_addsShipsToShipsHit() {
            Battleship battleship = new Battleship(new Position(1, 2), VERTICAL);
            Fleet fleet = new Fleet(
                    new Carrier(new Position(0, 0), HORIZONTAL),
                    battleship,
                    new Destroyer(new Position(7, 3), HORIZONTAL),
                    new Submarine(new Position(6, 5), VERTICAL),
                    new PatrolBoat(new Position(8, 9), HORIZONTAL)
            );

            fleet.receiveShot(new Position(1,3));

            assertThat(fleet.getShipsHit()).containsExactly(battleship);
        }
    }

}
