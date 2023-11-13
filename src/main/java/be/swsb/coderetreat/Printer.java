package be.swsb.coderetreat;

public class Printer {

    public static String print(Fleet fleet) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i <= fleet.getField().getMaxX(); i++) {
            for (int j = 0; j <= fleet.getField().getMaxY(); j++) {
                int x = i;
                int y = j;
                stringBuilder.append(fleet.print(new Position(x,y)));
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
