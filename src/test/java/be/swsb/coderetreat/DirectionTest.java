package be.swsb.coderetreat;


import org.junit.jupiter.api.Test;

import static be.swsb.coderetreat.Direction.HORIZONTAL;
import static be.swsb.coderetreat.Direction.VERTICAL;
import static org.assertj.core.api.Assertions.assertThat;


public class DirectionTest {

    @Test
    void Horizontal_increasesX_ignoresY() {
        assertThat(HORIZONTAL.getNextPosition(new Position(0,0))).isEqualTo(new Position(1,0));
        assertThat(HORIZONTAL.getNextPosition(new Position(4,5))).isEqualTo(new Position(5,5));
    }

    @Test
    void Vertical_increasesY_ignoresX() {
        assertThat(VERTICAL.getNextPosition(new Position(0,0))).isEqualTo(new Position(0,1));
        assertThat(VERTICAL.getNextPosition(new Position(4,5))).isEqualTo(new Position(4,6));
    }
}
