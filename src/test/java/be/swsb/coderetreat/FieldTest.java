package be.swsb.coderetreat;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FieldTest {

    @Nested
    class IsOutsideField_true {
        @Test
        void toTheLeft() {
            assertThat(new Field().isOutsideField(new Position(-1, 0))).isTrue();
        }

        @Test
        void toTheRight() {
            assertThat(new Field().isOutsideField(new Position(10, 0))).isTrue();
        }

        @Test
        void above() {
            assertThat(new Field().isOutsideField(new Position(0, -1))).isTrue();
        }

        @Test
        void below() {
            assertThat(new Field().isOutsideField(new Position(0, 10))).isTrue();
        }
    }

    @Test
    void isOutsideField_whenInsideField_returnsFalse() {
        assertThat(new Field().isOutsideField(new Position(3, 5))).isFalse();
        assertThat(new Field().isOutsideField(new Position(0, 0))).isFalse();
        assertThat(new Field().isOutsideField(new Position(9, 9))).isFalse();
        assertThat(new Field().isOutsideField(new Position(0, 9))).isFalse();
        assertThat(new Field().isOutsideField(new Position(9, 0))).isFalse();
    }
}
