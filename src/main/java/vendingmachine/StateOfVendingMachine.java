package vendingmachine;

public interface StateOfVendingMachine {
    void selectSnack(String snackName, VendingMachine machine);
    void insertMoney(double amount, VendingMachine machine);
    void dispenseSnack(VendingMachine machine);
}
