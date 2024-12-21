package Lake;

import java.util.ArrayList;
import java.util.List;

import Interfaces.IMapPlaceable;

public class FrozenLake {

    private int rowCount;
    private int columnCount;
    private ArrayList<ArrayList<LakeSquare>> frozenLake;

    public FrozenLake(int rowCount, int columnCount){
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

    public FrozenLake(){
        this(8, 11);
    }

    public FrozenLake(FrozenLake other){
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

    public IMapPlaceable getPriorityObject(int row, int column){
        return frozenLake.get(row).get(column).getPriorityObject();
    }

    public List<IMapPlaceable> getAllObjects(int row, int column){
        return frozenLake.get(row).get(column).getAllObjects();
    }

    public void setObject(int row, int column, IMapPlaceable object){
        frozenLake.get(row).get(column).setObject(object);
    }

    public void showLake(){
        return null;
        // TODO: Implement this method
        }
    }






}
