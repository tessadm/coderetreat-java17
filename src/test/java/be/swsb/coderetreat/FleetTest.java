package be.swsb.coderetreat;


import be.swsb.coderetreat.ship.*;
import org.junit.jupiter.api.Test;

import static be.swsb.coderetreat.Direction.HORIZONTAL;
import static be.swsb.coderetreat.Direction.VERTICAL;
import static org.assertj.core.api.Assertions.*;

public class FleetTest {

    @Test
    void aValidFleetCanBeCreated() {
        assertThatCode(() -> new Fleet(
                new Carrier(new Position(0,0), HORIZONTAL),
                new Battleship(new Position(1,2), VERTICAL),
                new Destroyer(new Position(7,3), HORIZONTAL),
                new Submarine(new Position(6,5), VERTICAL),
                new PatrolBoat(new Position(8,9), HORIZONTAL)
        )).doesNotThrowAnyException();
    }


    @Test
    void AFleetWithCarrierAndBattleshipOnSamePosition_throws() {
        assertThatThrownBy(() -> new Fleet(
                new Carrier(new Position(0,0), HORIZONTAL),
                new Battleship(new Position(1,0), VERTICAL)
        )).isInstanceOf(ValidationException.class);
    }
}
