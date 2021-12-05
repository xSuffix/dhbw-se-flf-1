package controls;

import truck.central_unit.ICentralUnit;

public class SteeringWheel {

    private final ICentralUnit centralUnit;
    private int rotation;

    public SteeringWheel(ICentralUnit centralUnit) {
        this.centralUnit = centralUnit;
        rotation = 0;
    }

    public void rotate(int rotation) {
        this.rotation += rotation;
        if (this.rotation < -90) {
            this.rotation = -90;
        } else if (this.rotation > 90) {
            this.rotation = 90;
        }
        centralUnit.turnSteeringWheel(this.rotation);
    }

    public int getRotation() {
        return this.rotation;
    }

}
