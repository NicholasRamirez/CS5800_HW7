package vendingmachineTest;

import vendingmachine.Snack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SnackTest {

    @Test
    public void snackTest() {
        Snack snack = new Snack("Coke", 2.00, 3);

        assertEquals("Coke", snack.getName());
        assertEquals(2.00, snack.getPrice());
        assertEquals(3, snack.getQuantity());
    }

    @Test
    public void dispenseASnackTest() {
        Snack snack = new Snack("Coke", 2.00, 1);

        snack.dispenseASnack();
        assertEquals(0, snack.getQuantity());
        snack.dispenseASnack();
        assertEquals(0, snack.getQuantity());
    }
}
