package vendingmachine;

public class IdleState implements StateOfVendingMachine{

    @Override
    public void selectSnack(String name, VendingMachine machine) {
        machine.setSelectedSnack(name);
        System.out.println("Snack selected: " + name);
        machine.setState(new WaitingForMoneyState());
    }

    @Override
    public void insertMoney(double amount, VendingMachine machine) {
        System.out.println("Please select a snack");
    }

    @Override
    public void dispenseSnack(VendingMachine machine) {
        System.out.println("Select a snack and insert money.");
    }
}
