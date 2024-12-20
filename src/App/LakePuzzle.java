package App;

import Lake.FrozenLake;
import Objects.*;

public class LakePuzzle {
    public static final int ROW_COUNT = 8;
    public static final int COLUMN_COUNT = 11;
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

    private FrozenLake initializeFrozenLake() {
        // Create the FrozenLake with 8x11 dimensions
        FrozenLake lake = new FrozenLake(ROW_COUNT, COLUMN_COUNT);
    
        // Set the entrance at the upper middle square
        int entranceColumn = (COLUMN_COUNT + 1) / 2; // Middle column (0-indexed)
    
        // Randomly choose the cliffside (1: left, 2: right, 3: bottom)
        int cliffSide = (int) (Math.random() * 3) + 1;
    
        // Add walls to the all edges, except the entrance
        addWalls(lake,entranceColumn);
    
        // Add CliffEdge based on the randomly chosen side
        addCliffEdge(lake, cliffSide);
    
        // Add IceBlocks
        addIceBlocks(lake, entranceColumn, cliffSide);
    
        // Add Hazards
        addHazards(lake, entranceColumn, cliffSide);
    
        return lake;
    }

    private void addWalls(FrozenLake lake, int entranceColumn) {
        // Add walls to upper edge except the entrance
        for (int col = 0; col < COLUMN_COUNT + 2; col++) {
            if (col != entranceColumn) {
                lake.setObject(0, col, new Wall());
            }
        }

        // add walls to the lower edge
        for (int col = 0; col < COLUMN_COUNT + 2; col++) {
            lake.setObject(ROW_COUNT + 1, col, new Wall());
        }

        for (int row = 0; row < ROW_COUNT + 2; row++) {
            // Add walls to the left edge
            lake.setObject(row, 0, new Wall());
            // Add walls to the right edge
            lake.setObject(row, COLUMN_COUNT + 1, new Wall());
        }
    }
    
    private void addCliffEdge(FrozenLake lake, int cliffSide) {
        switch (cliffSide) {
            case 1: // Left side
                for (int row = 1; row < ROW_COUNT + 1; row++) {
                    lake.setObject(row, 0, new CliffEdge());
                }
                break;
            case 2: // Right side
                for (int row = 1; row < ROW_COUNT + 1; row++) {
                    lake.setObject(row, COLUMN_COUNT + 1, new CliffEdge());
                }
                break;
            case 3: // Bottom side
                for (int col = 1; col < COLUMN_COUNT + 1; col++) {
                    lake.setObject(ROW_COUNT + 1, col, new CliffEdge());
                }
                break;
        }
    }
    
    private void addIceBlocks(FrozenLake lake, int entranceColumn, int cliffSide) {
        // Ensure 8 IceBlocks, one per row, not blocking entrance
        // Ensure at least one IceBlock in the middle column below the entrance
        int iceBlockCount = 8;
        boolean[] rowUsed = new boolean[ROW_COUNT + 2];
        int iceBlocksPlaced = 0;

        // Place IceBlock to the column below the entrance
        int randomRow = (int) (Math.random() * ROW_COUNT) + 1; // Random row (1-indexed)
        lake.setObject(randomRow, entranceColumn, new IceBlock(iceBlocksPlaced));
        rowUsed[randomRow] = true;
        iceBlocksPlaced++;
    
        while (iceBlocksPlaced < iceBlockCount) {
            int row = (int) (Math.random() * ROW_COUNT) + 1; // Random row (1-indexed)
            
            // Skip if row already used or is the entrance row
            if (rowUsed[row] || row <= 0 || row >= ROW_COUNT + 1) continue;
    
            // Place IceBlock
            if (row > 0 && row < ROW_COUNT + 1) {
                int col = 0;
                do {
                    col = (int) (Math.random() * COLUMN_COUNT) + 1; // Random column (1-indexed)
                } while (row == 1 && col == entranceColumn); // Skip if blocking entrance
                lake.setObject(row, col, new IceBlock(iceBlocksPlaced));

                rowUsed[row] = true;
                iceBlocksPlaced++;
            }
            
        }

        if (cliffSide == 3) {
            // Add IceBlock to the row above the cliff
            int randomColAboveCliff = (int) (Math.random() * COLUMN_COUNT) + 1; // Random Column (1-indexed)
            lake.setObject(ROW_COUNT, randomColAboveCliff, new IceBlock(iceBlocksPlaced));
        }
    }
    
    private void addHazards(FrozenLake lake, int entranceColumn, int cliffSide) {
        // Add 3 HoleInIce
        int holeCount = 0;
        while (holeCount < 3) {
            int row = (int) (Math.random() * ROW_COUNT) + 1; // Random row (1-indexed)
            int col = (int) (Math.random() * COLUMN_COUNT) + 1; // Random column (1-indexed)
            
            // Avoid placing near entrance or on cliffside
            if (isValidHazardPlacement(lake, row, col, entranceRow, entranceColumn, cliffSide)) {
                // lake.addHazard(row, col, new HoleInIce());
                holeCount++;
            }
        }
    
        // Add 3 IceSpikes next to walls
        int iceSpikeCount = 0;
        while (iceSpikeCount < 3) {
            int row = (int) (Math.random() * 8);
            int col = (int) (Math.random() * 11);
            
            // Check if next to a wall and not near entrance or cliffside
            if (isValidIceSpikePlacement(lake, row, col, entranceRow, entranceColumn, cliffSide)) {
                // lake.addHazard(row, col, new IceSpikes());
                iceSpikeCount++;
            }
        }
    }
    
    private boolean isValidHazardPlacement(FrozenLake lake, int row, int col, 
                                           int entranceRow, int entranceColumn, int cliffSide) {
        // Implementation of hazard placement validation
        // Check:
        // 1. Not within 3 squares of entrance
        // 2. Not on cliffside
        // 3. No existing hazards on the square
        return true; // Placeholder
    }
    
    private boolean isValidIceSpikePlacement(FrozenLake lake, int row, int col, 
                                             int entranceRow, int entranceColumn, int cliffSide) {
        // Implementation of ice spike placement validation
        // Check:
        // 1. Next to a wall
        // 2. Not near entrance
        // 3. Not on cliffside
        // 4. No existing hazards on the square
        return true; // Placeholder
    }
}
