package controls;

import truck.central_unit.ICentralUnit;

public record Button(ICentralUnit centralUnit, ButtonType type) {

    public void press() {
        centralUnit.buttonPress(this.type);
    }

}
