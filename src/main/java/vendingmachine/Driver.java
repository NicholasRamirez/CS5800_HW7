package vendingmachine;

public class Driver {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.addSnack(new Snack("Coke", 2.00, 3));
        vendingMachine.addSnack(new Snack("Pepsi", 2.00, 4));
        vendingMachine.addSnack(new Snack("Cheetos", 2.00, 5));
        vendingMachine.addSnack(new Snack("Doritos", 2.00, 2));
        vendingMachine.addSnack(new Snack("KitKat", 2.00, 2));
        vendingMachine.addSnack(new Snack("Snickers", 2.00, 1));
        vendingMachine.createSnackChain();

        System.out.println("\nBuy a Coke");
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(2.00);
        vendingMachine.dispenseSnack();

        System.out.println("\nBuy a Pepsi with Not Enough Money");
        vendingMachine.selectSnack("Pepsi");
        vendingMachine.insertMoney(1.50);
        vendingMachine.dispenseSnack();

        System.out.println("\nBuy a Snickers");
        vendingMachine.selectSnack("Snickers");
        vendingMachine.insertMoney(2.00);
        vendingMachine.dispenseSnack();

        System.out.println("\nBuy a Snickers Again");
        vendingMachine.selectSnack("Snickers");
        vendingMachine.insertMoney(2.00);
        vendingMachine.dispenseSnack();

        // Will just say "Please select a snack"
        System.out.println("\nInserting Money without Snack Selection");
        vendingMachine.insertMoney(2.00);

        // Will dispense Cheetos
        System.out.println("\nBuying a Different Snack Mid Process");
        vendingMachine.selectSnack("Coke");
        vendingMachine.selectSnack("Cheetos");
        vendingMachine.insertMoney(3.00);
        vendingMachine.dispenseSnack();

        System.out.println("\nUnknown Snack");
        vendingMachine.selectSnack("Dr Pepper");
        vendingMachine.insertMoney(2.00);
        vendingMachine.dispenseSnack();
    }
}
