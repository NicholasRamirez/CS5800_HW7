package vendingmachineTest;

import vendingmachine.Snack;
import vendingmachine.SnackDispenseHandler;
import vendingmachine.VendingMachine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SnackDispenseHandlerTest {

    private VendingMachine vendingMachineSetup() {
        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.addSnack(new Snack("Coke", 2.00, 3));
        vendingMachine.addSnack(new Snack("Pepsi", 2.00, 3));
        vendingMachine.addSnack(new Snack("Cheetos", 2.00, 1));

        vendingMachine.createSnackChain();
        return vendingMachine;
    }

    @Test
    void chainPassesToRightHandlerTest() {
        System.out.println("\nChain Works as Should Test");
        VendingMachine vendingMachine = vendingMachineSetup();

        vendingMachine.selectSnack("Coke");
        vendingMachine.addMoney(2.00);
        
        SnackDispenseHandler dispenseHandler = vendingMachine.getHandler();
        boolean handleSnackT = dispenseHandler.handleSnack("Coke", vendingMachine);
        assertTrue(handleSnackT, "Chain works");

        assertEquals(0.0, vendingMachine.getCurrentMoney());
        assertNull(vendingMachine.getSelectedSnack());
    }

    @Test
    void refundProcessTest() {
        System.out.println("\nSnack is Refunded Test");
        VendingMachine vendingMachine = vendingMachineSetup();

        vendingMachine.selectSnack("Coke");
        vendingMachine.addMoney(1.50);

        SnackDispenseHandler dispenseHandler = vendingMachine.getHandler();
        boolean handleSnack1 = dispenseHandler.handleSnack("Coke", vendingMachine);
        assertTrue(handleSnack1, "Chain works");

        assertEquals(0.0, vendingMachine.getCurrentMoney(), "Money refunded");
        assertNull(vendingMachine.getSelectedSnack(), "Selection clear after refund");
    }

    @Test
    void unknownSnackTest() {
        System.out.println("\nUnknown Snack Test");
        VendingMachine vendingMachine = vendingMachineSetup();

        vendingMachine.selectSnack("Dr Pepper");
        vendingMachine.addMoney(2.00);

        SnackDispenseHandler dispenseHandler = vendingMachine.getHandler();
        boolean handleSnack1 = dispenseHandler.handleSnack("Dr Pepper", vendingMachine);
        assertFalse(handleSnack1, "Dr Pepper is unknown snack");

        assertEquals(0.0, vendingMachine.getCurrentMoney(), "Money refunded");
        assertNull(vendingMachine.getSelectedSnack(), "Selection clear after refund");
    }

    @Test
    void outOfSnackTest() {
        System.out.println("\nOut of Snack Test");
        VendingMachine vendingMachine = vendingMachineSetup();

        vendingMachine.selectSnack("Cheetos");
        vendingMachine.addMoney(2.00);

        SnackDispenseHandler dispenseHandler = vendingMachine.getHandler();
        boolean handleSnack1 = dispenseHandler.handleSnack("Cheetos", vendingMachine);
        assertTrue(handleSnack1, "First Cheetos Request");
        assertEquals(0.0, vendingMachine.getCurrentMoney());
        assertNull(vendingMachine.getSelectedSnack());

        vendingMachine.selectSnack("Cheetos");
        vendingMachine.addMoney(2.00);

        SnackDispenseHandler dispenseHandler2 = vendingMachine.getHandler();
        boolean handleSnack2 = dispenseHandler2.handleSnack("Cheetos", vendingMachine);
        assertTrue(handleSnack2, "Second Cheetos Request");

        assertEquals(0.0, vendingMachine.getCurrentMoney());
        assertNull(vendingMachine.getSelectedSnack());
    }

    @Test
    void snackWorksAfterUnknownSnackTest() {
        System.out.println("\nSnack Works After Unknown Test");
        VendingMachine vendingMachine = vendingMachineSetup();
        vendingMachine.selectSnack("Dr Pepper");
        vendingMachine.addMoney(2.00);

        SnackDispenseHandler dispenseHandler = vendingMachine.getHandler();
        boolean handleSnack1 = dispenseHandler.handleSnack("Dr Pepper", vendingMachine);
        assertFalse(handleSnack1, "Dr Pepper is unknown snack");
        assertEquals(0.0, vendingMachine.getCurrentMoney());
        assertNull(vendingMachine.getSelectedSnack());

        vendingMachine.selectSnack("Coke");
        vendingMachine.addMoney(2.00);

        boolean handleSnack2 = dispenseHandler.handleSnack("Coke", vendingMachine);
        assertTrue(handleSnack2, "Coke Request");
        assertEquals(0.0, vendingMachine.getCurrentMoney());
        assertNull(vendingMachine.getSelectedSnack());
    }
}
