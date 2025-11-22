package vendingmachine;

public class WaitingForMoneyState implements StateOfVendingMachine {

    @Override
    public void selectSnack(String name, VendingMachine machine) {
        machine.setSelectedSnack(name);
        System.out.println("New snack selected: " + name);
    }

    @Override
    public void insertMoney(double amount, VendingMachine machine) {
        machine.addMoney(amount);
        System.out.println("Money inserted: $" + amount + ". Total Money: $" + machine.getCurrentMoney());
    }

    @Override
    public void dispenseSnack(VendingMachine machine) {
        if (machine.getSelectedSnack() == null) {
            System.out.println("No snack selected.");
            return;
        }
        machine.setState(new DispensingSnackState());
        machine.dispenseSnack();
    }
}
