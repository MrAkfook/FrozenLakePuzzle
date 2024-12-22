package Lake;

import java.util.ArrayList;
import java.util.List;

import Interfaces.IMapPlaceable;
import Objects.CliffEdge;
import Objects.Wall;

public class FrozenLake {

    private int rowCount;
    private int columnCount;
    private ArrayList<ArrayList<LakeSquare>> frozenLake;

    public FrozenLake(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        frozenLake = new ArrayList<ArrayList<LakeSquare>>();
        for (int i = 0; i < rowCount + 2; i++) { // Add 2 to account for the border squares !!!!!!!!!!!!!!!!!!!
            ArrayList<LakeSquare> row = new ArrayList<LakeSquare>();
            for (int j = 0; j < columnCount + 2; j++) { // Add 2 to account for the border squares
                row.add(new LakeSquare());
            }
            frozenLake.add(row);
        }
    }

    public FrozenLake() {
        this(8, 11);
    }

    public FrozenLake(FrozenLake other) {
        this.rowCount = other.rowCount;
        this.columnCount = other.columnCount;
        frozenLake = new ArrayList<ArrayList<LakeSquare>>();
        for (int i = 0; i < rowCount + 2; i++) { // Add 2 to account for the border squares
            ArrayList<LakeSquare> row = new ArrayList<LakeSquare>();
            for (int j = 0; j < columnCount + 2; j++) { // Add 2 to account for the border squares
                row.add(new LakeSquare(other.frozenLake.get(i).get(j)));
            }
            frozenLake.add(row);
        }
    }

    // getters and setters
    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public IMapPlaceable getPriorityObject(int row, int column) {
        return frozenLake.get(row).get(column).getPriorityObject();
    }

    public List<IMapPlaceable> getAllObjects(int row, int column) {
        return frozenLake.get(row).get(column).getAllObjects();
    }

    public void setObject(int row, int column, IMapPlaceable object) {
        frozenLake.get(row).get(column).setObject(object);
    }

    public void showLake() {
        System.out.println("\n");
        System.out.println("      "+"-"+"------".repeat((int)(columnCount/2)) + "   " + "--" + "------".repeat((int)(columnCount/2)) );
        for (int i = 1; i < rowCount + 2; i++) {
            ArrayList<LakeSquare> currentRow = frozenLake.get(i);
            for (int j = 1; j < columnCount + 2; j++) {
                LakeSquare lakeSquare = currentRow.get(j);
                if (lakeSquare.getPriorityObject().getClass() == new Wall().getClass()) {
                    System.out.print("      ");
                } else if (lakeSquare.getPriorityObject().getClass() == new CliffEdge().getClass()) {
                    System.out.print("  CE  ");
                } else {
                    if (lakeSquare.toString().length() == 2) {
                        System.out.print("| " + lakeSquare.toString() + "  ");
                    } else {
                        System.out.print("|" + lakeSquare.toString());
                    }

                }
                if (i == columnCount) {
                    System.out.println("|");
                }
            }
            System.out.println("      "+"-"+"------".repeat(columnCount));
        }
    }

}