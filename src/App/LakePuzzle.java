package App;

import Lake.FrozenLake;
import Objects.*;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Set;

import Equipment.*;
import Exceptions.IncorrectBagContentsException;

import java.util.HashSet;

public class LakePuzzle {
    public static final int ROW_COUNT = 8;
    public static final int COLUMN_COUNT = 11;

    public static void main(String[] args) throws Exception {
        LakePuzzle lakePuzzle = new LakePuzzle();

        FrozenLake lake = lakePuzzle.initializeFrozenLake();
        Queue<Researcher> researchers = lakePuzzle.createResearchersQueue();
        Set<Experiment> experiments = lakePuzzle.createExperimentsSet();
        Set<Equipment> equipmentStorage = lakePuzzle.createEquipmentSet(2);

        Menu menu = lakePuzzle.new Menu();
        menu.start(lake, researchers, experiments, equipmentStorage);

    }

    private class Menu {
        private Scanner scanner;
        private boolean isDead;

        public Menu() {
            scanner = new Scanner(System.in);
        }

        public void start(FrozenLake lake, Queue<Researcher> researchers, Set<Experiment> experiments,
                Set<Equipment> equipmentStorage) {
            // Show initial state of the lake, researchers, experiments, and equipment storage
            boolean isInjured = false; // Flag to indicate if a researcher has died
            System.out.println("Welcome to Frozen Lake Puzzle App. There are " + researchers.size()
                    + " researchers waiting at the lake entrance.");
            System.out.println("There are " + experiments.size() + " experiment(s) that must be completed:");
            for (Experiment experiment : experiments) {
                System.out.println("- " + experiment.toString());
            }
            System.out.println("The initial map of the frozen lake: ");
            lake.showLake();

            for (Researcher researcher : researchers) {
                // 3. Display the researcher
                // 4. Display the equipment storage
                if (researcher.getId() == 1) {
                    System.out.println(
                            "=====> Researcher 1 starts waiting at the entrance and can select up to 3 pieces of equipment of the \r\n"
                                    + //
                                    "same type. Here are the shorter notations of the equipments: \r\n" + //
                                    "[td] Temperature detector \r\n" + //
                                    "[ws] Wind speed detector \r\n" + //
                                    "[cm] Camera \r\n" + //
                                    "[ch] Chiseling equipment \r\n" + //
                                    "[cl] Climbing equipment \r\n" + //
                                    "[wb] Large wooden board \r\n" + //
                                    "[ph] Protective helmet \r\n" + //
                                    "[no] Stop taking equipment and head out to the lake ");
                } else {
                    System.out
                            .println("=====> Researcher " + (researcher.getId() - 1) + " stops moving, and Researcher "
                                    + (researcher.getId()) + " starts waiting at the entrance.");
                    lake.showLake();
                    System.out.println("Researcher " + (researcher.getId())
                            + " can select up to 3 pieces of equipment of the same type.");
                }
                // 5. Assign equipment to a researcher
                for (int i = 0; i < 3; i++) {
                    Equipment equipment = null;
                    while (true) {
                        System.out.println("Enter the short name of an equipment:");
                        ShortHand shortHand;
                        try {
                            shortHand = ShortHand.valueOf(getShorthand());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid input. Please enter a valid equipment short name:");
                            continue;
                        }
                        if (shortHand == ShortHand.NO) {
                            if (i == 0) {
                                System.out.println("*** Researchers cannot head to the lake with an empty bag.");
                                continue;
                            } else {
                                break;
                            }
                        }
                        switch (shortHand) {
                            case TD:
                                equipment = new TemperatureDetector();
                                break;
                            case WS:
                                equipment = new WindSpeedDetector();
                                break;
                            case CM:
                                equipment = new Camera();
                                break;
                            case CH:
                                equipment = new ChiselingEquipment();
                                break;
                            case CL:
                                equipment = new ClimbingEquipment();
                                break;
                            case WB:
                                equipment = new LargeWoodenBoard();
                                break;
                            case PH:
                                equipment = new ProtectiveHelmet();
                                break;
                            default:
                                break;
                        }

                        for(Equipment e : equipmentStorage){ 
                            if(e.getClass().equals(equipment.getClass())){
                                equipment = e; //  If equipment is found in the equipment storage, the equipment object will be replaced with the one in the storage changing its id
                                break;
                            }
                        }

                        if (equipment.getId() == -1) { // If equipment is not found in the equipment storage its id will be -1
                            System.out.println("*** There no more "+shortHand.toString()+" left in the Equipment Storage.");
                            continue;
                        }

                        try {
                            researcher.takeEquipment(equipment); // Add equipment to the researcher's bag
                        } catch (IncorrectBagContentsException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                        equipmentStorage.remove(equipment);
                        break;
                    }

                }
                
                if (researcher.getId() == 1){
                System.out.println("=====> Researcher "+researcher.getId()+" heads out to the lake. Select a direction to slide ([U] Up, [D] Down, [L] Left, [R] Right):");}
                else{
                    System.out.println("=====> Researcher "+researcher.getId()+" heads out to the lake. Select a direction to slide:");
                }
                char direction = getDirection();
                while (true){
                    if (direction == 'U'){
                        System.out.println("*** The input direction is unavailable. Please enter an available direction:");
                        direction = getDirection();
                    }
                    else{
                        break;
                    }
                }
                State state = researcher.slide(lake, direction);
                
                while(true){
                    switch (state){
                        case LargeWoodenBoard:
                            System.out.println("=====> Researcher "+researcher.getId()+" manages to stop safely on a Wooden Board." );
                            break;
                        case ClimbingEquipment:
                            System.out.println("=====> Researcher "+researcher.getId()+" manages to climb back up the cliff using the Climbing Equipment." ); //TODO
                            break;
                        case CliffEdge:
                            if(isInjured){
                                System.out.println("=====> Researcher "+researcher.getId()+" falls off the cliff and gets injured.");
                            }
                            else{
                                System.out.println("!!! Researcher " + researcher.getId()
                                        + " is sliding towards the cliff edge and starts falling. However, Researcher "
                                        + researcher.getId() + " is carrying a Climbing Equipment. Researcher "
                                        + researcher.getId()+ " sets the Climbing Equipment and manages to climb back up the cliff.");
                            }
                            break;
                        case HoleInIce:
                            if(isInjured){
                                System.out.println("=====> Researcher "+researcher.getId()+" falls into a hole in the ice and gets injured.");
                            }
                            else{
                                System.out.println("!!! Researcher " + researcher.getId()
                                        + " comes across a hole in ice. However, Researcher " + researcher.getId()
                                        + " is carrying a Large Wooden Board. Researcher " + researcher.getId()
                                        + " covers the ice with the board and starts standing on it. ");
                            }
                            break;
                        case IceSpikes:
                            if(isInjured){
                                System.out.println("=====> The ice spikes falls on Researcher "+researcher.getId()+"'s head and injures the Researcher.");
                            }
                            else{
                                System.out.println("!!! Researcher " + researcher.getId()
                                        + " comes across ice spikes. However, Researcher " + researcher.getId()
                                        + " is carrying a Protective Helmet. Researcher " + researcher.getId()
                                        + " wears the helmet and manages to survive the ice spikes falling.");
                            }
                            break;
                        case IMapPlaceable:
                            System.out.println("=====> Researcher "+researcher.getId()+" manages to stop safely." );
                            break;
                        
                        
                    }
                    if(isInjured){
                        break;
                    }
                    System.out.println("[1] Continue moving on the ice. \r\n" + //
                                        "[2] Choose experiment equipment and perform an experiment. \r\n" + //
                                        "[3] Sit on the ground and let the other researchers head out to the lake. \r\n" + //
                                        "Choose the action of Researcher "+researcher.getId()+":");
                    int selection = getSelection(3);
                    switch (selection){
                        case 1:
                            System.out.println("Select a direction to slide:");
                            direction = getDirection();
                            checkIfDirectionIsAvailable(lake,direction);
                            
                    }

                }
                if (isInjured){
                    break;
                }
            }

            // 6. Start an experiment
            // 7. Exit
            
        }

        private String getShorthand() {
            String input;
            while (true) {
                input = scanner.nextLine().trim();
                if (input.matches("[a-zA-Z]{2}")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a valid equipment short name:");
                }
            }
            return input.toLowerCase();
        }

        private int getSelection(int upperBound) {
            int selection;
            while (true) {
                try {
                    selection = Integer.parseInt(scanner.nextLine().trim());
                    if (selection >= 1 && selection <= upperBound) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a valid selection:");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid selection:");
                }
            }
            return selection;
        }

        private char getDirection() {
            String input;
            while (true) {
                input = scanner.nextLine().trim();
                if (input.matches("[RLUDrlud]")) {
                    break;
                } else {
                    System.out.println("*** Invalid input. Please reenter your input:");
                }
            }
            return (input.toUpperCase().charAt(0));
        }

        enum State {
            LargeWoodenBoard, ClimbingEquipment, CliffEdge, HoleInIce, IceSpikes, IMapPlaceable;
        }
    }

    private FrozenLake initializeFrozenLake() {
        // Create the FrozenLake with 8x11 dimensions
        FrozenLake lake = new FrozenLake(ROW_COUNT, COLUMN_COUNT);

        // Set the entrance at the upper middle square
        int entranceColumn = (COLUMN_COUNT + 1) / 2; // Middle column (0-indexed)

        // Randomly choose the cliffside (1: left, 2: right, 3: bottom)
        int cliffSide = (int) (Math.random() * 3) + 1;

        // Add walls to the all edges, except the entrance
        addWalls(lake, entranceColumn);

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
            if (rowUsed[row] || row <= 0 || row >= ROW_COUNT + 1)
                continue;

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
            if (isValidHoleInIcePlacement(lake, row, col, entranceColumn, cliffSide)) {
                lake.setObject(row, col, new HoleInIce(holeCount));
                holeCount++;
            }
        }

        // Add 3 IceSpikes next to walls
        int iceSpikeCount = 0;
        while (iceSpikeCount < 3) {
            int row = (int) (Math.random() * ROW_COUNT) + 1; // Random row (1-indexed)
            int col = (int) (Math.random() * COLUMN_COUNT) + 1; // Random column (1-indexed)

            // Check if next to a wall and not near entrance or cliffside
            if (isValidIceSpikePlacement(lake, row, col, entranceColumn, cliffSide)) {
                lake.setObject(row, col, new IceSpikes(iceSpikeCount));
                iceSpikeCount++;
            }
        }
    }

    private boolean isValidHoleInIcePlacement(FrozenLake lake, int row, int col, int entranceColumn, int cliffSide) {
        // Implementation of hazard placement validation
        // Check:
        // 1. Not within 3 squares of entrance
        // 2. Not on cliffside
        // 3. No existing hazards on the square
        return true; // Placeholder
    }

    private boolean isValidIceSpikePlacement(FrozenLake lake, int row, int col, int entranceColumn, int cliffSide) {
        // Implementation of ice spike placement validation
        // Check:
        // 1. Next to a wall
        if (!((row == 1 || row == ROW_COUNT) && (col == 1 || col == COLUMN_COUNT))) {
            return false;
        }
        // 2. Not near entrance
        if ((row == 1) && (col >= entranceColumn - 2 && col <= entranceColumn + 2)) { // TODO: hocadan dönüş alınacak
            return false;
        }
        // 3. Not on cliffside
        if ((cliffSide == 1 && col == 1) || (cliffSide == 2 && col == COLUMN_COUNT)
                || (cliffSide == 3 && row == ROW_COUNT)) {
            return false;
        }
        // 4. No existing hazards on the square
        if (lake.getPriorityObject(row, col) instanceof Hazard) {
            return false;
        }

        return true;
    }

    private Queue<Researcher> createResearchersQueue() {
        int researcherCount = (int) (Math.random() * 3) + 2; // Random number between 2 and 4

        // Create a queue of researchers
        Queue<Researcher> researchers = new LinkedList<Researcher>();
        for (int i = 0; i < researcherCount; i++) {
            researchers.add(new Researcher((i + 1)));
        }
        return researchers;
    }

    private Set<Experiment> createExperimentsSet() {
        int experimentCount = (int) (Math.random() * 4) + 1; // Random number between 1 and 4

        Set<Experiment> experiments = new HashSet<Experiment>();
        // Create 3 experiments
        while (experiments.size() < experimentCount) {
            Experiment experiment = Experiment.values()[(int) (Math.random() * Experiment.values().length)];
            experiments.add(experiment);
        }

        return experiments;
    }

    private Set<Equipment> createEquipmentSet(int equipmentCount) {
        // equipmentCount is number of each equipment
        Set<Equipment> equipment = new HashSet<Equipment>();

        for (int i = 0; i < equipmentCount; i++) {
            equipment.add(new LargeWoodenBoard(i));
            equipment.add(new ClimbingEquipment(i));
            equipment.add(new TemperatureDetector(i));
            equipment.add(new ProtectiveHelmet(i));
            equipment.add(new TemperatureDetector(i));
            equipment.add(new Camera(i));
            equipment.add(new WindSpeedDetector(i));
        }

        return equipment;
    }

}
