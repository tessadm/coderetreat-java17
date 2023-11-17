package be.swsb.coderetreat;

public class Printer {

    // todo: print sunken ship differently
    public static String print(Fleet fleet) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i <= fleet.getField().getMaxX(); i++) {
            for (int j = 0; j <= fleet.getField().getMaxY(); j++) {
                int y = i;
                int x = j;
                stringBuilder.append(fleet.print(new Position(x,y)));
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
