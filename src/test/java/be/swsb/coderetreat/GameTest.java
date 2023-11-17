package be.swsb.coderetreat;

import be.swsb.coderetreat.ship.*;
import org.junit.jupiter.api.Test;

import static be.swsb.coderetreat.Direction.HORIZONTAL;
import static be.swsb.coderetreat.Direction.VERTICAL;
import static be.swsb.coderetreat.Printer.print;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GameTest {

    @Test
    void canAddFleetForPlayer() {
        Game game = new Game();
        Player player = new Player("Tessa");
        Fleet fleet = new Fleet(
                new Carrier(new Position(0, 0), HORIZONTAL),
                new Battleship(new Position(1, 2), VERTICAL),
                new Destroyer(new Position(7, 3), HORIZONTAL),
                new Submarine(new Position(6, 5), VERTICAL),
                new PatrolBoat(new Position(8, 9), HORIZONTAL)
        );
        game.registerFleetForPlayer(player, fleet);

        assertThat(game.getFleetOfPlayer(player)).isEqualTo(fleet);
    }

    @Test
    void aPlayerCanFireOnAnotherPlayer() {
        Game game = new Game();
        Player tessa = new Player("Tessa");
        Player opponent = new Player("Opponent");
        Carrier shipToHit = new Carrier(new Position(0, 0), HORIZONTAL);
        Fleet fleet = new Fleet(
                shipToHit,
                new Battleship(new Position(1, 2), VERTICAL),
                new Destroyer(new Position(7, 3), HORIZONTAL),
                new Submarine(new Position(6, 5), VERTICAL),
                new PatrolBoat(new Position(8, 9), HORIZONTAL)
        );
        game.registerFleetForPlayer(tessa, fleet);
        game.registerFleetForPlayer(opponent, fleet);

        game.shoot(tessa, opponent, new Position(1,0));

        assertThat(game.getFleetOfPlayer(opponent).getShipsHit()).containsExactly(shipToHit);
    }

    @Test
    void samePlayerCannotFireTwice() {
        Game game = new Game();
        Player tessa = new Player("Tessa");
        Player opponent = new Player("Opponent");
        Fleet fleet = new Fleet(
                new Carrier(new Position(0, 0), HORIZONTAL),
                new Battleship(new Position(1, 2), VERTICAL),
                new Destroyer(new Position(7, 3), HORIZONTAL),
                new Submarine(new Position(6, 5), VERTICAL),
                new PatrolBoat(new Position(8, 9), HORIZONTAL)
        );
        game.registerFleetForPlayer(tessa, fleet);
        game.registerFleetForPlayer(opponent, fleet);
        game.shoot(tessa, opponent, new Position(1,0));

        assertThatThrownBy(() -> game.shoot(tessa, opponent, new Position(2,0))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void completeGameTest() {
        Game game = new Game();
        Player tessa = new Player("Tessa");
        Player opponent = new Player("Opponent");
        Fleet fleetOfTessa = new Fleet(
                new PatrolBoat(new Position(8, 9), HORIZONTAL)
        );
        game.registerFleetForPlayer(tessa, fleetOfTessa);

        Fleet fleetOfOpponent = new Fleet(
                new PatrolBoat(new Position(8, 9), HORIZONTAL)
        );
        game.registerFleetForPlayer(opponent, fleetOfOpponent);

        game.shoot(tessa, opponent, new Position(8,9));
        game.shoot(opponent, tessa, new Position(0,0));
        game.shoot(tessa, opponent, new Position(9,9));
        System.out.println(print(fleetOfOpponent));
        System.out.println(print(fleetOfTessa));

        assertThatThrownBy(() -> game.shoot(opponent, tessa, new Position(0,0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Game is over, Tessa has won");
    }

}
