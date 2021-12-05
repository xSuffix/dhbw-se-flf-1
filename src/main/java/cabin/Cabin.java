package cabin;

import controls.*;
import enums.SeatPositions;
import truck.central_unit.ICentralUnit;

public class Cabin {

    private final BusDoor leftDoor;
    private final BusDoor rightDoor;
    private final Seat[] seats;
    private final Joystick leftJoystick;
    private final Joystick rightJoystick;
    private final ControlPanel controlPanel;
    private final TurningKnob<FrontLauncherOutput> frontLauncherKnob;
    private final TurningKnob<RoofLauncherOutput> roofLauncherKnob;
    private final SteeringWheel steeringWheel;
    private final BatteryDisplay batteryDisplay;
    private final SpeedDisplay speedDisplay;
    private final Pedal breakPedal;
    private final Pedal gasPedal;

    public Cabin(ICentralUnit centralUnit,boolean smartJoysticks) {
        this.leftDoor = new BusDoor(centralUnit, ButtonType.LEFT_DOOR);
        this.rightDoor = new BusDoor(centralUnit, ButtonType.RIGHT_DOOR);
        this.seats = new Seat[SeatPositions.values().length];
        for (int i = 0; i < seats.length; i++) seats[i] = new Seat(SeatPositions.values()[i]);
        this.leftJoystick = new Joystick(centralUnit, JoystickType.LEFT,smartJoysticks);
        this.rightJoystick = new Joystick(centralUnit, JoystickType.RIGHT,smartJoysticks);
        this.controlPanel = new ControlPanel(centralUnit);
        this.frontLauncherKnob = new TurningKnob<>(centralUnit, TurningKnobType.FRONT_LAUNCHER, FrontLauncherOutput.A);
        this.roofLauncherKnob = new TurningKnob<>(centralUnit, TurningKnobType.ROOF_LAUNCHER, RoofLauncherOutput.A);
        this.steeringWheel = new SteeringWheel(centralUnit);
        this.batteryDisplay = new BatteryDisplay();
        this.speedDisplay = new SpeedDisplay();
        this.breakPedal = new Pedal(centralUnit, PedalType.BRAKE);
        this.gasPedal = new Pedal(centralUnit, PedalType.GAS);
    }

    public BusDoor getLeftDoor() {
        return leftDoor;
    }

    public BusDoor getRightDoor() {
        return rightDoor;
    }

    public Seat getSeat(int pos) {
        return seats[pos];
    }

    public Joystick getLeftJoystick() {
        return leftJoystick;
    }

    public Joystick getRightJoystick() {
        return rightJoystick;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public TurningKnob<FrontLauncherOutput> getFrontLauncherKnob() {
        return frontLauncherKnob;
    }

    public TurningKnob<RoofLauncherOutput> getRoofLauncherKnob() {
        return roofLauncherKnob;
    }

    public SteeringWheel getSteeringWheel() {
        return steeringWheel;
    }

    public BatteryDisplay getBatteryDisplay() {
        return batteryDisplay;
    }

    public SpeedDisplay getSpeedDisplay() {
        return speedDisplay;
    }

    public Pedal getBreakPedal() {
        return breakPedal;
    }

    public Pedal getGasPedal() {
        return gasPedal;
    }

}
