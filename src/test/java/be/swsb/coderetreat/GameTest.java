package be.swsb.coderetreat;

import be.swsb.coderetreat.ship.*;
import org.junit.jupiter.api.Test;

import static be.swsb.coderetreat.Direction.HORIZONTAL;
import static be.swsb.coderetreat.Direction.VERTICAL;
import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {
    // players toevoegen ✅
    // fleets toevoegen per player ✅
    // when game is started, fleets can no longer change?
    // laat players shots firen ✅
    // beheert wiens beurt het is
    // weet wanneer het spel gedaan is

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
        Player player = new Player("Tessa");
        Carrier shipToHit = new Carrier(new Position(0, 0), HORIZONTAL);
        Fleet fleet = new Fleet(
                shipToHit,
                new Battleship(new Position(1, 2), VERTICAL),
                new Destroyer(new Position(7, 3), HORIZONTAL),
                new Submarine(new Position(6, 5), VERTICAL),
                new PatrolBoat(new Position(8, 9), HORIZONTAL)
        );
        game.registerFleetForPlayer(player, fleet);

        game.shootOn(player, new Position(1,0));

        assertThat(game.getFleetOfPlayer(player).getShipsHit()).containsExactly(shipToHit);
    }
}
