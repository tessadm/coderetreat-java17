package be.swsb.coderetreat;

import java.util.HashMap;
import java.util.Map;

public class Game {
    Map<Player, Fleet> fleets = new HashMap<>();

    public void registerFleetForPlayer(Player player, Fleet fleet) {
        fleets.put(player, fleet);
    }

    public Fleet getFleetOfPlayer(Player player) {
        return fleets.get(player);
    }

    public void shootOn(Player player, Position position) {
        fleets.get(player).receiveShot(position);
    }
}
