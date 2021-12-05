package controls;

import truck.central_unit.ICentralUnit;

public class Joystick {

    private final JoystickType type;
    private final Button frontLeftButton;
    private final Button frontRightButton;
    private final Button backSwitch;

    //beim smarten joystick werden die vorderen beiden knöpfe sozusagen zusammengeschweißt
    public Joystick(ICentralUnit centralUnit, JoystickType type, boolean smart) {
        this.type = type;
        if (type == JoystickType.LEFT) {
            if (smart) {
                this.frontLeftButton = new Button(centralUnit, ButtonType.SMART_JOYSTICK_LEFT);
                this.frontRightButton = new Button(centralUnit, ButtonType.SMART_JOYSTICK_LEFT);
            } else {
                this.frontLeftButton = new Button(centralUnit, ButtonType.LEFT_JOYSTICK_LEFT);
                this.frontRightButton = new Button(centralUnit, ButtonType.LEFT_JOYSTICK_RIGHT);
            }
            this.backSwitch = new Button(centralUnit, ButtonType.LEFT_JOYSTICK_BACK);
        } else {
            if (smart) {
                this.frontLeftButton = new Button(centralUnit, ButtonType.SMART_JOYSTICK_RIGHT);
                this.frontRightButton = new Button(centralUnit, ButtonType.SMART_JOYSTICK_RIGHT);
            } else {
                this.frontLeftButton = new Button(centralUnit, ButtonType.RIGHT_JOYSTICK_LEFT);
                this.frontRightButton = new Button(centralUnit, ButtonType.RIGHT_JOYSTICK_RIGHT);
            }
            this.backSwitch = new Button(centralUnit, ButtonType.RIGHT_JOYSTICK_BACK);
        }
    }

    public JoystickType getType() {
        return type;
    }

    public Button getFrontLeftButton() {
        return frontLeftButton;
    }

    public Button getFrontRightButton() {
        return frontRightButton;
    }

    public Button getBackSwitch() {
        return backSwitch;
    }

}

