package App;

import Lake.FrozenLake;
import Objects.*;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Set;

import Interfaces.*;

import Equipment.*;
import Exceptions.*;

import java.util.HashSet;

public class LakePuzzle {
    public static final int ROW_COUNT = 8;
    public static final int COLUMN_COUNT = 11;
    private int cliffSide;
    private Menu menu;
    private FrozenLake lake;
    private Queue<Researcher> researchers;
    private Set<Experiment> experiments;
    private Set<Equipment> equipmentStorage;

    public LakePuzzle() {
        cliffSide = -1;
        menu = new Menu();
        lake = new FrozenLake();
        cliffSide = ((int) (Math.random() * 3) + 1);
        lake.initializeFrozenLake(cliffSide);
        researchers = createResearchersQueue();
        experiments = createExperimentsSet();
        equipmentStorage = createEquipmentSet(2);
    }

    public static void main(String[] args) throws Exception {
        LakePuzzle lakePuzzle = new LakePuzzle();
        lakePuzzle.menu.start(lakePuzzle.lake, lakePuzzle.researchers, lakePuzzle.experiments,
                lakePuzzle.equipmentStorage);

    }

    public int getCliffSide() {
        return cliffSide;
    }

    public void setCiffSide(int cliffSide) {
        this.cliffSide = cliffSide;
    }

    private class Menu {
        private Scanner scanner;
        private boolean isInjured;
        private int researcherRow;
        private int researcherColumn;
        Set<ResearchEquipment> placedEquipments;

        public Menu() {
            scanner = new Scanner(System.in);
            placedEquipments = new HashSet<ResearchEquipment>();
        }

        // gettters and setters
        public int getResearcherRow() {
            return researcherRow;
        }

        public int getResearcherColumn() {
            return researcherColumn;
        }

        public void setResearcherRow(int researcherRow) {
            this.researcherRow = researcherRow;
        }

        public void setResearcherColumn(int researcherColumn) {
            this.researcherColumn = researcherColumn;
        }

        public boolean getIsInjured() {
            return isInjured;
        }

        public void setIsInjured(boolean isInjured) {
            this.isInjured = isInjured;
        }

        public void start(FrozenLake lake, Queue<Researcher> researchers, Set<Experiment> experiments,
                Set<Equipment> equipmentStorage) {
            // Show initial state of the lake, researchers, experiments, and equipment
            // storage
            setIsInjured(false); // Flag to indicate if a researcher has died
            System.out.println("Welcome to Frozen Lake Puzzle App. There are " + researchers.size()
                    + " researchers waiting at the lake entrance.");
            System.out.println("There are " + experiments.size() + " experiment(s) that must be completed:");
            for (Experiment experiment : experiments) {
                System.out.println("- " + experiment.toString());
            }
            System.out.println("The initial map of the frozen lake: ");
            lake.setObject(1, (COLUMN_COUNT + 1) / 2, researchers.peek());
            lake.showLake();

            for (Researcher researcher : researchers) {
                // 3. Display the researcher
                // 4. Display the equipment storage

                researcherRow = 1;
                researcherColumn = (COLUMN_COUNT + 1) / 2;
                lake.setObject(researcherRow, researcherColumn, researcher);

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
                    ShortHand shortHand;
                    while (true) {
                        System.out.println("Enter the short name of an equipment:");
                        try {
                            shortHand = ShortHand.valueOf(getShorthand().toUpperCase());
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

                        equipment = getEquipment(shortHand);

                        for (Equipment e : equipmentStorage) {
                            if (e.getClass().equals(equipment.getClass())) {
                                equipment = e; // If equipment is found in the equipment storage, the equipment object
                                               // will be replaced with the one in the storage changing its id
                                break;
                            }
                        }

                        if (equipment.getId() == -1) { // If equipment is not found in the equipment storage its id will
                                                       // be -1
                            System.out.println(
                                    "*** There no more " + shortHand.toString() + " left in the Equipment Storage.");
                            continue;
                        }

                        try {
                            researcher.takeEquipment(equipment); // Add equipment to the researcher's bag
                        } catch (IncorrectBagContentsException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                        equipmentStorage.remove(equipment);
                        if (researcher.getId() == 1) {
                            System.out.print("- Contents of the bag of Researcher " + researcher.getId() + ": ");
                            for (Equipment e : researcher.getEquipmentBagArray()) {
                                System.out.print(e.showOnMap().toLowerCase() + ", ");
                            }
                            System.out.println();
                        }
                        break;
                    }
                    if (shortHand == ShortHand.NO) {
                        break;
                    }
                }

                System.out.print("- The final contents of the bag of Researcher " + researcher.getId() + ":");
                for (Equipment e : researcher.getEquipmentBagArray()) {
                    System.out.print(e.showOnMap().toLowerCase() + ", ");
                }
                System.out.println();

                if (researcher.getId() == 1) {
                    System.out.println("=====> Researcher " + researcher.getId()
                            + " heads out to the lake. Select a direction to slide ([U] Up, [D] Down, [L] Left, [R] Right):");
                } else {
                    System.out.println("=====> Researcher " + researcher.getId()
                            + " heads out to the lake. Select a direction to slide:");
                }
                char direction;
                while (true) {
                    try {
                        direction = getDirection(lake);
                        if (direction == 'U') {
                            throw new UnavailableDirectionException(
                                    "*** The input direction is unavailable. Please enter an available direction:");
                        }
                    } catch (UnavailableDirectionException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                }

                State state = slide(lake, researcher, researcherRow, researcherColumn, direction);

                setIsInjured(determineInjury(state, researcher));

                while (true) {
                    switch (state) {
                        case LARGE_WOODEN_BOARD:
                            lake.showLake();
                            System.out.println("=====> Researcher " + researcher.getId()
                                    + " manages to stop safely on a Wooden Board.");
                            break;
                        case CLIMBING_EQUIPMENT:
                            lake.showLake();
                            System.out.println("=====> Researcher " + researcher.getId()
                                    + " manages to climb back up the cliff using the Climbing Equipment.");
                            break;
                        case CLIFF_EDGE:
                            if (getIsInjured()) {
                                System.out.println("=====> Researcher " + researcher.getId()
                                        + " falls off the cliff and gets injured.");
                            } else {
                                lake.setObject(researcherRow, researcherColumn,
                                        researcher.useEquipment(new ClimbingEquipment()));
                                lake.showLake();
                                System.out.println("!!! Researcher " + researcher.getId()
                                        + " is sliding towards the cliff edge and starts falling. However, Researcher "
                                        + researcher.getId() + " is carrying a Climbing Equipment. Researcher "
                                        + researcher.getId()
                                        + " sets the Climbing Equipment and manages to climb back up the cliff.");
                            }
                            break;
                        case HOLE_IN_ICE:
                            if (getIsInjured()) {
                                System.out.println("=====> Researcher " + researcher.getId()
                                        + " falls into a hole in the ice and gets injured.");
                            } else {
                                lake.setObject(researcherRow, researcherColumn,
                                        researcher.useEquipment(new LargeWoodenBoard()));
                                lake.showLake();
                                System.out.println("!!! Researcher " + researcher.getId()
                                        + " comes across a hole in ice. However, Researcher " + researcher.getId()
                                        + " is carrying a Large Wooden Board. Researcher " + researcher.getId()
                                        + " covers the ice with the board and starts standing on it. ");
                            }
                            break;
                        case ICE_SPIKES:
                            if (getIsInjured()) {
                                System.out.println("=====> The ice spikes falls on Researcher " + researcher.getId()
                                        + "'s head and injures the Researcher.");
                            } else {
                                lake.setObject(researcherRow, researcherColumn, null);
                                researcher.useEquipment(new ProtectiveHelmet());
                                lake.showLake();
                                System.out.println("!!! Researcher " + researcher.getId()
                                        + " comes across ice spikes. However, Researcher " + researcher.getId()
                                        + " is carrying a Protective Helmet. Researcher " + researcher.getId()
                                        + " wears the helmet and manages to survive the ice spikes falling.");

                            }
                            break;
                        case IMAP_PLACEABLE:
                            lake.showLake();
                            System.out.println("=====> Researcher " + researcher.getId() + " manages to stop safely.");
                            break;
                        case ENTERANCE:
                            lake.showLake();
                            System.out.println("=====> Researcher " + researcher.getId()
                                    + " has reached the entrance. The researcher empties their bag into the equipment storage.");
                            for (Equipment e : researcher.getEquipmentBagArray()) {
                                equipmentStorage.add(e);
                            }
                            break;

                    }
                    if (getIsInjured() || state == State.ENTERANCE) {
                        break;
                    }
                    System.out.println("[1] Continue moving on the ice. \r\n" + //
                            "[2] Choose experiment equipment and perform an experiment. \r\n" + //
                            "[3] Sit on the ground and let the other researchers head out to the lake. \r\n" + //
                            "Choose the action of Researcher " + researcher.getId() + ":");
                    int selection = -1;
                    while (true) {
                        selection = getSelection(3);
                        switch (selection) {
                            case 1:
                                System.out.println("Select a direction to slide:");
                                direction = getDirection(lake);
                                state = slide(lake, researcher, researcherRow, researcherColumn, direction);
                                setIsInjured(determineInjury(state, researcher));
                                break;
                            case 2:
                                System.out.println("Select an experiment to perform:");
                                try {
                                    researcher.carryingResearchEquipment();
                                } catch (UnavailableEquipmentException e) {
                                    System.out.println("Researcher " + researcher.getId()
                                            + " is not carrying any research equipment.");
                                    System.out.println("Choose the action of Researcher " + researcher.getId() + ":");
                                    continue;
                                }
                                ShortHand shortHand;
                                while (true) {
                                    System.out.println("=====> Enter the name of the research equipment:");

                                    try {
                                        shortHand = ShortHand.valueOf(getShorthand().toUpperCase());
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Invalid input. Please enter a valid equipment short name:");
                                        continue;
                                    }
                                    break;
                                }

                                Equipment equipment = getEquipment(shortHand);

                                for (Equipment e : researcher.getEquipmentBagArray()) {
                                    if (e.getClass().equals(equipment.getClass())) {
                                        equipment = e;
                                        break;
                                    }
                                }

                                if (equipment.getId() == -1) {
                                    System.out.println("*** The selected equipment is not in the equipment bag.");
                                    System.out.println("Choose the action of Researcher " + researcher.getId() + ":");
                                    continue;
                                }

                                try {
                                    isValidLocationForExperiment(lake, equipment);
                                } catch (IncompatibleResearchEquipmentLocationException e) {
                                    System.out.println(e.getMessage());
                                    System.out.println("Choose the action of Researcher " + researcher.getId() + ":");
                                    continue;
                                }

                                lake.setObject(researcherRow, researcherColumn, researcher.useEquipment(equipment));
                                System.out.println(
                                        "--- The selected research equipment has been placed in the current location.");
                                placedEquipments = checkSuccess(equipment, experiments, placedEquipments);
                                break;
                            case 3:
                                break;
                        }
                        break;
                    }
                    if (selection == 3) {
                        break;
                    }
                }
                if (isInjured) {
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

        private char getDirection(FrozenLake lake) {
            String input;
            while (true) {
                input = scanner.nextLine().trim();
                if (!(input.matches("[RLUDrlud]"))) {
                    System.out.println("*** Invalid input. Please reenter your input:");
                }

                try {
                    checkIfDirectionIsAvailable(lake, input.toUpperCase().charAt(0));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                break;
            }

            return (input.toUpperCase().charAt(0));
        }

        private boolean checkIfDirectionIsAvailable(FrozenLake lake, char direction)
                throws UnavailableDirectionException {
            int row = getResearcherRow();
            int col = getResearcherColumn();
            boolean isValid = false;

            switch (direction) {
                case 'U':
                    isValid = !(lake.getPriorityObject(row - 1, col) instanceof IMapPlaceable)
                            || (lake.getPriorityObject(row - 1, col) instanceof LargeWoodenBoard)
                            || (lake.getPriorityObject(row - 1, col) instanceof IDangerousHazard);
                    break;
                case 'D':
                    isValid = !(lake.getPriorityObject(row + 1, col) instanceof IMapPlaceable)
                            || (lake.getPriorityObject(row + 1, col) instanceof LargeWoodenBoard)
                            || (lake.getPriorityObject(row + 1, col) instanceof IDangerousHazard);
                    break;
                case 'L':
                    isValid = !(lake.getPriorityObject(row, col - 1) instanceof IMapPlaceable)
                            || (lake.getPriorityObject(row, col - 1) instanceof LargeWoodenBoard)
                            || (lake.getPriorityObject(row, col - 1) instanceof IDangerousHazard);
                    break;
                case 'R':
                    isValid = !(lake.getPriorityObject(row, col + 1) instanceof IMapPlaceable)
                            || (lake.getPriorityObject(row, col + 1) instanceof LargeWoodenBoard)
                            || (lake.getPriorityObject(row, col + 1) instanceof IDangerousHazard);
                    break;
                default:
            }

            if (!isValid) {
                throw new UnavailableDirectionException(
                        "*** The input direction is unavailable. Please enter an available direction:");
            }

            return isValid;
        }

        enum State {
            LARGE_WOODEN_BOARD, CLIMBING_EQUIPMENT, CLIFF_EDGE, HOLE_IN_ICE, ICE_SPIKES, IMAP_PLACEABLE, ENTERANCE;
        }

        private boolean determineInjury(State state, Researcher researcher) {
            boolean isInjured = true;
            Equipment neededEquipment = null;
            switch (state) {
                case CLIFF_EDGE:
                    neededEquipment = new CliffEdge().getOvercomeBy();
                    break;
                case HOLE_IN_ICE:
                    neededEquipment = new HoleInIce().getOvercomeBy();
                    break;
                case ICE_SPIKES:
                    neededEquipment = new IceSpikes().getOvercomeBy();
                    break;
                default:
                    return false;
            }
            for (Equipment e : researcher.getEquipmentBagArray()) {
                if (e.getClass().equals(neededEquipment.getClass())) {
                    isInjured = false;
                    break;
                }
            }

            return isInjured;
        }

        private Equipment getEquipment(ShortHand shortHand) {
            Equipment equipment = null;
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
            return equipment;
        }

        private boolean isValidLocationForExperiment(FrozenLake lake, Equipment equipment)
                throws IncompatibleResearchEquipmentLocationException {
            int row = getResearcherRow();
            int col = getResearcherColumn();

            ResearchEquipment researchEquipment = (ResearchEquipment) equipment;

            if (researchEquipment instanceof TemperatureDetector) {
                if ((lake.getPriorityObject(row - 1, col) instanceof IceBlock) ||
                        (lake.getPriorityObject(row + 1, col) instanceof IceBlock) ||
                        (lake.getPriorityObject(row, col - 1) instanceof IceBlock) ||
                        (lake.getPriorityObject(row, col + 1) instanceof IceBlock) ||
                        (lake.getPriorityObject(row - 1, col) instanceof Wall) ||
                        (lake.getPriorityObject(row + 1, col) instanceof Wall) ||
                        (lake.getPriorityObject(row, col - 1) instanceof Wall) ||
                        (lake.getPriorityObject(row, col + 1) instanceof Wall)) {
                    throw new IncompatibleResearchEquipmentLocationException(
                            "*** The selected research equipment is incompatible with the current location.");
                }

            } else if (researchEquipment instanceof WindSpeedDetector) {
                if ((lake.getPriorityObject(row - 1, col) instanceof IDangerousHazard) ||
                        (lake.getPriorityObject(row + 1, col) instanceof IDangerousHazard) ||
                        (lake.getPriorityObject(row, col - 1) instanceof IDangerousHazard) ||
                        (lake.getPriorityObject(row, col + 1) instanceof IDangerousHazard)) {
                    throw new IncompatibleResearchEquipmentLocationException(
                            "*** The selected research equipment is incompatible with the current location.");
                }
            } else if (researchEquipment instanceof Camera) {
                int i = 1;
                while (true) {
                    switch (getCliffSide()) {
                        case 1:
                            if (lake.getPriorityObject(row, col - i) instanceof CliffEdge) {
                                return true;
                            }

                            if (lake.getPriorityObject(row, col - i) instanceof Hazard) {
                                throw new IncompatibleResearchEquipmentLocationException(
                                        "*** The selected research equipment is incompatible with the current location.");
                            }
                            i++;
                            break;
                        case 2:
                            if (lake.getPriorityObject(row, col + i) instanceof CliffEdge) {
                                return true;
                            }

                            if (lake.getPriorityObject(row, col + i) instanceof Hazard) {
                                throw new IncompatibleResearchEquipmentLocationException(
                                        "*** The selected research equipment is incompatible with the current location.");
                            }
                            i++;
                            break;
                        case 3:
                            if (lake.getPriorityObject(row + i, col) instanceof CliffEdge) {
                                return true;
                            }

                            if (lake.getPriorityObject(row + i, col) instanceof Hazard) {
                                throw new IncompatibleResearchEquipmentLocationException(
                                        "*** The selected research equipment is incompatible with the current location.");
                            }
                            i++;
                            break;
                        default:
                            break;

                    }
                }

            } else if (researchEquipment instanceof ChiselingEquipment) {
                if ((lake.getPriorityObject(row - 1, col) instanceof IceBlock) ||
                        (lake.getPriorityObject(row + 1, col) instanceof IceBlock) ||
                        (lake.getPriorityObject(row, col - 1) instanceof IceBlock) ||
                        (lake.getPriorityObject(row, col + 1) instanceof IceBlock)) {
                    return true;
                } else {
                    throw new IncompatibleResearchEquipmentLocationException(
                            "*** The selected research equipment is incompatible with the current location.");
                }
            }
            return false;
        }

        private Set<ResearchEquipment> checkSuccess(Equipment equipment, Set<Experiment> experiments,Set<ResearchEquipment> placedEquipments) {
            ResearchEquipment researchEquipment = (ResearchEquipment) equipment;
            placedEquipments.add(researchEquipment);
            Set<ResearchEquipment> goalEquipments = new HashSet<ResearchEquipment>();
            Set<Experiment> completedExperiments = new HashSet<Experiment>();
            for (ResearchEquipment e : placedEquipments) {
                if (experiments.contains(e.getExperiment())) {
                    completedExperiments.add(e.getExperiment());
                    goalEquipments.add(e);
                }
            }
            if (completedExperiments.size() == experiments.size()) {
                System.out.println("-----------> Research goal(s) have been accomplished. Here are their results:\n");
                for (ResearchEquipment e : goalEquipments) {
                    System.out.println("--" + e.report());
                }
                System.out.println("-----------> SUCCESSFUL ");
                System.exit(0);
            }
            return placedEquipments;
        }
    }

    public Menu.State slide(FrozenLake lake, Researcher researcher, int researcherRow, int researcherColumn,
            char direction) {
        int row = researcherRow;
        int col = researcherColumn;
        Menu.State state = null;
        int i = 1;

        lake.removeResearcher(row, col);

        while (true) {
            switch (direction) {
                case 'U':
                    if (lake.getPriorityObject(row - i, col) instanceof LargeWoodenBoard) {
                        this.menu.setResearcherRow(row - i);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.LARGE_WOODEN_BOARD;
                        return state;
                    } else if (lake.getPriorityObject(row - i, col) instanceof ClimbingEquipment) { // This is
                                                                                                    // impossible to
                                                                                                    // encounter in the
                                                                                                    // current set of
                                                                                                    // rules
                        this.menu.setResearcherRow(row - i + 1);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.CLIMBING_EQUIPMENT;
                        return state;
                    } else if (lake.getPriorityObject(row - i, col) instanceof CliffEdge) { // This is impossible to
                                                                                            // encounter in the current
                                                                                            // set of rules
                        this.menu.setResearcherRow(row - i + 1);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.CLIFF_EDGE;
                        return state;
                    } else if (lake.getPriorityObject(row - i, col) instanceof HoleInIce) {
                        this.menu.setResearcherRow(row - i);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.HOLE_IN_ICE;
                        return state;
                    } else if (lake.getPriorityObject(row - i, col) instanceof IceSpikes) {
                        this.menu.setResearcherRow(row - i);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.ICE_SPIKES;
                        return state;
                    } else if (lake.getPriorityObject(row - i, col) instanceof IMapPlaceable) {
                        this.menu.setResearcherRow(row - i + 1);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.IMAP_PLACEABLE;
                        return state;
                    } else if ((row - i == 1) && (col == (COLUMN_COUNT + 1) / 2)) { // This state can only be reached
                                                                                    // while going up to the entrance
                        state = Menu.State.ENTERANCE;
                        return state;
                    }
                    break;
                case 'D':
                    if (lake.getPriorityObject(row + i, col) instanceof LargeWoodenBoard) {
                        this.menu.setResearcherRow(row + i);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.LARGE_WOODEN_BOARD;
                        return state;
                    } else if (lake.getPriorityObject(row + i, col) instanceof ClimbingEquipment) {
                        this.menu.setResearcherRow(row + i - 1);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.CLIMBING_EQUIPMENT;
                        return state;
                    } else if (lake.getPriorityObject(row + i, col) instanceof CliffEdge) {
                        this.menu.setResearcherRow(row + i - 1);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.CLIFF_EDGE;
                        return state;
                    } else if (lake.getPriorityObject(row + i, col) instanceof HoleInIce) {
                        this.menu.setResearcherRow(row + i);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.HOLE_IN_ICE;
                        return state;
                    } else if (lake.getPriorityObject(row + i, col) instanceof IceSpikes) {
                        this.menu.setResearcherRow(row + i);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.ICE_SPIKES;
                        return state;
                    } else if (lake.getPriorityObject(row + i, col) instanceof IMapPlaceable) {
                        this.menu.setResearcherRow(row + i - 1);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.IMAP_PLACEABLE;
                        return state;
                    }
                    break;
                case 'L':
                    if (lake.getPriorityObject(row, col - i) instanceof LargeWoodenBoard) {
                        this.menu.setResearcherColumn(col - i);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.LARGE_WOODEN_BOARD;
                        return state;
                    } else if (lake.getPriorityObject(row, col - i) instanceof ClimbingEquipment) {
                        this.menu.setResearcherColumn(col - i + 1);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.CLIMBING_EQUIPMENT;
                        return state;
                    } else if (lake.getPriorityObject(row, col - i) instanceof CliffEdge) {
                        this.menu.setResearcherColumn(col - i + 1);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.CLIFF_EDGE;
                        return state;
                    } else if (lake.getPriorityObject(row, col - i) instanceof HoleInIce) {
                        this.menu.setResearcherColumn(col - i);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.HOLE_IN_ICE;
                        return state;
                    } else if (lake.getPriorityObject(row, col - i) instanceof IceSpikes) {
                        this.menu.setResearcherColumn(col - i);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.ICE_SPIKES;
                        return state;
                    } else if (lake.getPriorityObject(row, col - i) instanceof IMapPlaceable) {
                        this.menu.setResearcherColumn(col - i + 1);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.IMAP_PLACEABLE;
                        return state;
                    }
                    break;
                case 'R':
                    if (lake.getPriorityObject(row, col + i) instanceof LargeWoodenBoard) {
                        this.menu.setResearcherColumn(col + i);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.LARGE_WOODEN_BOARD;
                        return state;
                    } else if (lake.getPriorityObject(row, col + i) instanceof ClimbingEquipment) {
                        this.menu.setResearcherColumn(col + i - 1);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.CLIMBING_EQUIPMENT;
                        return state;
                    } else if (lake.getPriorityObject(row, col + i) instanceof CliffEdge) {
                        this.menu.setResearcherColumn(col + i - 1);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.CLIFF_EDGE;
                        return state;
                    } else if (lake.getPriorityObject(row, col + i) instanceof HoleInIce) {
                        this.menu.setResearcherColumn(col + i);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.HOLE_IN_ICE;
                        return state;
                    } else if (lake.getPriorityObject(row, col + i) instanceof IceSpikes) {
                        this.menu.setResearcherColumn(col + i);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.ICE_SPIKES;
                        return state;
                    } else if (lake.getPriorityObject(row, col + i) instanceof IMapPlaceable) {
                        this.menu.setResearcherColumn(col + i - 1);
                        lake.setObject(this.menu.getResearcherRow(), this.menu.getResearcherColumn(), researcher);
                        state = Menu.State.IMAP_PLACEABLE;
                        return state;
                    }
                    break;
                default:
                    break;
            }
            i++; // Increment i to check the next cell
        }

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
            equipment.add(new Camera(i));
            equipment.add(new WindSpeedDetector(i));
            equipment.add(new ChiselingEquipment(i));
        }

        return equipment;
    }

}
