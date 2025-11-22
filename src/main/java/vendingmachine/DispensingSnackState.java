package vendingmachine;

public class DispensingSnackState implements StateOfVendingMachine {

    @Override
    public void selectSnack(String name, VendingMachine machine) {
        System.out.println("Snack is dispensing.");
    }

    @Override
    public void insertMoney(double amount, VendingMachine machine) {
        System.out.println("Snack is dispensing.");
    }

    @Override
    public void dispenseSnack(VendingMachine machine) {
        machine.getHandler().handleSnack(machine.getSelectedSnack(), machine);
    }
}
