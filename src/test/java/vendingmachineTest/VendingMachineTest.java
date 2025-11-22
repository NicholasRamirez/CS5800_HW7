package vendingmachineTest;

import vendingmachine.Snack;
import vendingmachine.VendingMachine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineTest {

    private VendingMachine vendingMachineSetup() {
        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.addSnack(new Snack("Coke", 2.00, 3));
        vendingMachine.addSnack(new Snack("Pepsi", 2.00, 4));
        vendingMachine.addSnack(new Snack("Cheetos", 2.00, 5));
        vendingMachine.addSnack(new Snack("Doritos", 2.00, 2));
        vendingMachine.addSnack(new Snack("KitKat", 2.00, 2));
        vendingMachine.addSnack(new Snack("Snickers", 2.00, 1));

        vendingMachine.createSnackChain();
        return vendingMachine;
    }

    @Test
    void insertMoneyBeforeSnackSelectionTest() {
        System.out.println("\nInsert Money Before Snack Selection Test");

        // "Please select a snack" will be printed
        VendingMachine vendingMachine = vendingMachineSetup();
        vendingMachine.insertMoney(5);

        assertEquals(0.0, vendingMachine.getCurrentMoney());
        assertNull(vendingMachine.getSelectedSnack());
    }

    @Test
    void selectSnackStoresNameTest() {
        System.out.println("\nSelect Snack Stores Name Test");

        VendingMachine vendingMachine = vendingMachineSetup();
        vendingMachine.selectSnack("Coke");

        assertEquals("Coke", vendingMachine.getSelectedSnack());
    }

    @Test
    void purchaseIsSuccessfulTest() {
        System.out.println("\nPurchase is Successful Test");

        VendingMachine vendingMachine = vendingMachineSetup();
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(5);
        vendingMachine.dispenseSnack();

        assertEquals(0.0, vendingMachine.getCurrentMoney());
        assertNull(vendingMachine.getSelectedSnack());
    }

    @Test
    void refundIsSuccessfulTest() {
        System.out.println("\nRefund is Successful Test");

        VendingMachine vendingMachine = vendingMachineSetup();
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(1.5);
        vendingMachine.dispenseSnack();

        assertEquals(0.0, vendingMachine.getCurrentMoney());
        assertNull(vendingMachine.getSelectedSnack());
    }

    @Test
    void returnMoneyTest() {
        System.out.println("\nReturn Money Test");

        VendingMachine vendingMachine = vendingMachineSetup();
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(2);
        vendingMachine.returnMoney();

        assertEquals(0.0, vendingMachine.getCurrentMoney());
        assertNull(vendingMachine.getSelectedSnack());
    }

    @Test
    void multiplePurchaseTest() {
        System.out.println("\nMultiple Purchase Test");

        VendingMachine vendingMachine = vendingMachineSetup();
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(2);
        vendingMachine.dispenseSnack();

        assertEquals(0.0, vendingMachine.getCurrentMoney());
        assertNull(vendingMachine.getSelectedSnack());

        vendingMachine.selectSnack("Pepsi");
        vendingMachine.insertMoney(2);
        vendingMachine.dispenseSnack();

        assertEquals(0.0, vendingMachine.getCurrentMoney());
        assertNull(vendingMachine.getSelectedSnack());
    }

    @Test
    void selectedNewSnackTest() {
        System.out.println("\nSelect New Snack Test");

        VendingMachine vendingMachine = vendingMachineSetup();
        vendingMachine.selectSnack("Coke");
        assertEquals("Coke", vendingMachine.getSelectedSnack());

        vendingMachine.selectSnack("Pepsi");
        assertEquals("Pepsi", vendingMachine.getSelectedSnack());

        assertEquals(0.0, vendingMachine.getCurrentMoney());
    }

    @Test
    void moneyInsertionInRoundsTest() {
        System.out.println("\nMoney Insertion In Rounds Test");

        VendingMachine vendingMachine = vendingMachineSetup();
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(0.50);
        vendingMachine.insertMoney(0.50);
        vendingMachine.insertMoney(0.50);
        vendingMachine.insertMoney(0.50);

        assertEquals(2.0, vendingMachine.getCurrentMoney());
        vendingMachine.dispenseSnack();
        assertEquals(0.0, vendingMachine.getCurrentMoney());
        assertNull(vendingMachine.getSelectedSnack());
    }

    @Test
    void dispenseSnackWithNoSelectionTest() {
        System.out.println("\nDispense Snack With No Selection Test");

        VendingMachine vendingMachine = vendingMachineSetup();
        vendingMachine.dispenseSnack();

        assertEquals(0.0, vendingMachine.getCurrentMoney());
        assertNull(vendingMachine.getSelectedSnack());
    }
}
