package be.swsb.coderetreat;

import java.util.HashMap;
import java.util.Map;

public class Game {
    Map<Player, Fleet> fleets = new HashMap<>();
    Player playerWhoFiredLast = null;
    Player winningPlayer = null;

    public void registerFleetForPlayer(Player player, Fleet fleet) {
        fleets.put(player, fleet);
    }

    public Fleet getFleetOfPlayer(Player player) {
        return fleets.get(player);
    }

    public void shoot(Player firingPlayer, Player targetPlayer, Position position) {
        if (winningPlayer != null) {
            throw new IllegalArgumentException("Game is over, " + winningPlayer.name() + " has won.");
        }
        if (firingPlayer == playerWhoFiredLast) {
            throw new IllegalArgumentException("Not your turn");
        }

        getFleetOfPlayer(targetPlayer).receiveShot(position);
        playerWhoFiredLast = firingPlayer;

        if (getFleetOfPlayer(targetPlayer).allShipsAreSunken()) {
            System.out.println(firingPlayer.name() + " wins!");
            winningPlayer = firingPlayer;
        }
    }
}
