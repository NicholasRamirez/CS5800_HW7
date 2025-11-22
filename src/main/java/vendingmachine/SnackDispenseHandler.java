package vendingmachine;

public class SnackDispenseHandler {
    private Snack snack;
    private SnackDispenseHandler nextHandler;

    public SnackDispenseHandler(Snack snack) {
        this.snack = snack;
    }

    public void setNextHandler(SnackDispenseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public boolean handleSnack (String name, VendingMachine machine) {
        if (snack.getName().equalsIgnoreCase(name)) {

            if (snack.getQuantity() <= 0) {
                System.out.println(snack.getName() + ": this snack is out of stock.");
                machine.returnMoney();
                return true;
            }

            if (machine.getCurrentMoney() < snack.getPrice()) {
                System.out.println("Not enough money for: " + snack.getName()
                    + ". Insert at least $" + snack.getPrice());
                machine.returnMoney();
                return true;
            }

            snack.dispenseASnack();
            double change = machine.getCurrentMoney() - snack.getPrice();
            System.out.println("Snack is dispensing.");
            if (change > 0)
                System.out.println("Returning change: $" + change);

            machine.clearMoney();
            machine.clearSelectedSnack();
            machine.setState(new IdleState());
            return true;
        }

        if (nextHandler != null)
            return nextHandler.handleSnack(name, machine);

        System.out.println(name + ": this snack is unavailable.");
        machine.returnMoney();
        return false;
    }
}
