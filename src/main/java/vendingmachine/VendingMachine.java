package vendingmachine;

import java.util.*;

public class VendingMachine {
    private StateOfVendingMachine state;
    private SnackDispenseHandler snackHandler;

    private String selectedSnack;
    private double currentMoney;

    private List<Snack> snacks = new ArrayList<>();

    public VendingMachine() {
        this.state = new IdleState();
    }

    public void selectSnack(String name) {
        state.selectSnack(name, this);
    }

    public void insertMoney(double amount) {
        state.insertMoney(amount, this);
    }

    public void dispenseSnack() {
        state.dispenseSnack(this);
    }

    public void addSnack(Snack snack) {
        snacks.add(snack);
    }

    public String getSelectedSnack() {
        return selectedSnack;
    }

    public void setSelectedSnack(String selectedSnack) {
        this.selectedSnack = selectedSnack;
    }

    public void clearSelectedSnack() {
        this.selectedSnack = null;
    }

    public void setState(StateOfVendingMachine newState) {
        this.state = newState;
    }

    public SnackDispenseHandler getHandler() {
        return snackHandler;
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public void addMoney(double amount) {
        currentMoney += amount;
    }

    public void clearMoney() {
        currentMoney = 0;
    }

    public void returnMoney()  {
        if (currentMoney > 0)
            System.out.println("Returning current money: $" + currentMoney);
        clearMoney();
        clearSelectedSnack();
        setState(new IdleState());
    }

    public void createSnackChain() {
        SnackDispenseHandler previousHandler = null;
        for (int i = 0; i < snacks.size(); i++) {
            Snack currentSnack = snacks.get(i);
            SnackDispenseHandler currentHandler = new SnackDispenseHandler(currentSnack);

            if (i == 0)
                snackHandler = currentHandler;
            else
                previousHandler.setNextHandler(currentHandler);

            previousHandler = currentHandler;
        }
    }
}
