package controls;

import truck.central_unit.ICentralUnit;

public class BusDoor {

    private final Button innerButton;
    private final Button outerButton;
    private boolean isOpen;
    private boolean isLocked;
    IDCardReceiver idCardReceiver;

    public BusDoor(ICentralUnit centralUnit, ButtonType type) {
        this.innerButton = new Button(centralUnit, type); // TODO: Warum muss type übergeben werden?
        this.outerButton = new Button(centralUnit, type);
        this.isOpen = false;
        this.isLocked = false;
        idCardReceiver = new IDCardReceiver(centralUnit);
    }

    public void toggleOpen() {
        if (isOpen) close();
        else open();
    }

    public void close() {
        isOpen = false;
    }

    public void open() {
        if (!isLocked) isOpen = true;
    }

    public void toggleLock() {
        if (isLocked) {
            isLocked = false;
            open();
        } else {
            close();
            isLocked = true;
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public Button getInnerButton() {
        return innerButton;
    }

    public Button getOuterButton() {
        return outerButton;
    }

    public IDCardReceiver getIdCardReceiver() {
        return idCardReceiver;
    }
}
