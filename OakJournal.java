import java.util.Scanner;

abstract class BattleUnit {
    private String name;
    private int health;

    public BattleUnit(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public abstract String getElementType();

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

abstract class BlazeUnit extends BattleUnit {
    public BlazeUnit(String name, int health) {
        super(name, health);
    }

    @Override
    public String getElementType() {
        return "Blaze";
    }
}

abstract class AquaUnit extends BattleUnit {
    public AquaUnit(String name, int health) {
        super(name, health);
    }

    @Override
    public String getElementType() {
        return "Aqua";
    }
}

abstract class FloraUnit extends BattleUnit {
    public FloraUnit(String name, int health) {
        super(name, health);
    }

    @Override
    public String getElementType() {
        return "Flora";
    }
}


class DracoBlaze extends BlazeUnit {
    private int attackPower;

    public DracoBlaze(int health, int attackPower) {
        super("DracoBlaze", health);
        this.attackPower = attackPower;
    }
}

class PyroFox extends BlazeUnit {
    private int attackPower;

    public PyroFox(int health, int attackPower) {
        super("PyroFox", health);
        this.attackPower = attackPower;
    }
}

class HydroTurtle extends AquaUnit {
    private int attackPower;

    public HydroTurtle(int health, int attackPower) {
        super("HydroTurtle", health);
        this.attackPower = attackPower;
    }
}

class AquaDuck extends AquaUnit {
    private int attackPower;

    public AquaDuck(int health, int attackPower) {
        super("AquaDuck", health);
        this.attackPower = attackPower;
    }
}

class LeafSaur extends FloraUnit {
    private int attackPower;

    public LeafSaur(int health, int attackPower) {
        super("LeafSaur", health);
        this.attackPower = attackPower;
    }
}

class BloomBud extends FloraUnit {
    private int attackPower;

    public BloomBud(int health, int attackPower) {
        super("BloomBud", health);
        this.attackPower = attackPower;
    }
}

class SparkMouse extends BattleUnit {
    private int attackPower;

    public SparkMouse(int health, int attackPower) {
        super("SparkMouse", health);
        this.attackPower = attackPower;
    }

    @Override
    public String getElementType() {
        return "Spark";
    }
}

class NinjaFrog extends AquaUnit {
    private int attackPower;

    public NinjaFrog(int health, int attackPower) {
        super("NinjaFrog", health);
        this.attackPower = attackPower;
    }
}

class Wielder {
    private String name;
    private BattleUnit[] units;
    private int victoryTokens;

    public Wielder(String name) {
        this.name = name;
        this.units = new BattleUnit[6];
        this.victoryTokens = 0;
    }

    public void addUnit(BattleUnit u, int slotIndex) {
        if (slotIndex >= 0 && slotIndex < units.length) {
            this.units[slotIndex] = u;
        } else {
            System.out.println("Error: Invalid slot index " + slotIndex + " for " + name);
        }
    }

    public String getName() {
        return name;
    }

    public BattleUnit[] getUnits() {
        return units;
    }

    public int getVictoryTokens() {
        return victoryTokens;
    }

    public void earnToken() {
        this.victoryTokens++;
    }

    public boolean handleLoss() {
        this.victoryTokens--;
        if (this.victoryTokens <= -3) {
            System.out.println(this.name + " has lost 3 battles and is ELIMINATED!");
            return true;
        }
        return false;
    }
}


class ShowdownManager {
    private Wielder[] wielders;

    public ShowdownManager() {
        this.wielders = new Wielder[2];
    }

    public Wielder[] getWielders() { return wielders; }
    public void setWielders(Wielder[] wielders) { this.wielders = wielders; }


    public void runShowdown(Wielder w1, Wielder w2) {
        System.out.println("\n=============================================");
        System.out.println("Showdown: " + w1.getName() + " vs. " + w2.getName());
        System.out.println("=============================================");

        int w1Wins = 0;
        int w2Wins = 0;
        int draws = 0;

        for (int i = 0; i < 6; i++) {
            BattleUnit u1 = w1.getUnits()[i];
            BattleUnit u2 = w2.getUnits()[i];

            if (u1 == null || u2 == null) {
                System.out.println("Round " + (i + 1) + ": (Unit Missing in slot " + (i+1) + ") - Skipping.");
                continue;
            }

            String winnerName = null;
            String result = "";
            String type1 = u1.getElementType();
            String type2 = u2.getElementType();

            if (type1.equals("Blaze") && type2.equals("Flora")) {
                winnerName = w1.getName();
            } else if (type2.equals("Blaze") && type1.equals("Flora")) {
                winnerName = w2.getName();
            }
            else if (type1.equals("Flora") && type2.equals("Aqua")) {
                winnerName = w1.getName();
            } else if (type2.equals("Flora") && type1.equals("Aqua")) {
                winnerName = w2.getName();
            }
            else if (type1.equals("Aqua") && type2.equals("Blaze")) {
                winnerName = w1.getName();
            } else if (type2.equals("Aqua") && type1.equals("Blaze")) {
                winnerName = w2.getName();
            }

            if (winnerName == null) {
                if (u1.getHealth() > u2.getHealth()) {
                    winnerName = w1.getName();
                } else if (u2.getHealth() > u1.getHealth()) {
                    winnerName = w2.getName();
                } else {
                    result = "It's a draw!";
                    draws++;
                }
            }

            if (result.contains("draw")) {
                System.out.println("Round " + (i + 1) + " (" + u1.getName() + " vs. " + u2.getName() + "): " + result);
            } else {
                if (winnerName.equals(w1.getName())) w1Wins++;
                else if (winnerName.equals(w2.getName())) w2Wins++;
                System.out.println("Round " + (i + 1) + " (" + u1.getName() + " vs. " + u2.getName() + "): Winner is " + winnerName + "!");
            }
        }

        String finalWinner;
        if (w1Wins > w2Wins) {
            w1.earnToken();
            w2.handleLoss();
            finalWinner = w1.getName();
        } else if (w2Wins > w1Wins) {
            w2.earnToken();
            w1.handleLoss();
            finalWinner = w2.getName();
        } else {
            finalWinner = "Draw";
        }

        System.out.println("---------------------------------------------");
        System.out.println("Final Score: " + w1.getName() + " " + w1Wins + " - " + w2Wins + " " + w2.getName() + " (Draws: " + draws + ")");
        System.out.println("Final Winner: " + finalWinner);
        System.out.println("=============================================");
    }

    public static void main(String[] args) {

        BattleUnit unitA = new DracoBlaze(120, 15);
        BattleUnit unitB = new HydroTurtle(100, 10);
        BattleUnit unitC = new LeafSaur(90, 12);
        BattleUnit unitD = new SparkMouse(80, 20);

        BattleUnit unitE = new PyroFox(100, 10);
        BattleUnit unitF = new AquaDuck(130, 8);
        BattleUnit unitG = new BloomBud(110, 15);
        BattleUnit unitH = new NinjaFrog(95, 25);

        Wielder kai = new Wielder("Kai");
        Wielder zane = new Wielder("Zane");

        kai.addUnit(unitA, 0);
        kai.addUnit(unitB, 1);
        kai.addUnit(unitC, 2);
        kai.addUnit(unitD, 3);
        kai.addUnit(unitA, 4);
        kai.addUnit(unitB, 5);

        zane.addUnit(unitE, 0);
        zane.addUnit(unitF, 1);
        zane.addUnit(unitG, 2);
        zane.addUnit(unitH, 3);
        zane.addUnit(unitG, 4);
        zane.addUnit(unitE, 5);

        ShowdownManager manager = new ShowdownManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== Creature Battle Simulator Menu =====");
            System.out.println("1. Run Kai vs Zane Showdown");
            System.out.println("2. Check Wielder Victory Token Counts");
            System.out.println("3. Exit Program");
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        manager.runShowdown(kai, zane);
                        break;
                    case 2:
                        System.out.println("\n--- Victory Token Counts ---");
                        System.out.println(kai.getName() + " Tokens: " + kai.getVictoryTokens());
                        System.out.println(zane.getName() + " Tokens: " + zane.getVictoryTokens());
                        break;
                    case 3:
                        running = false;
                        System.out.println("Simulator exited. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                        break;
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        scanner.close();
    }
}
