package truck.central_unit;

import controls.ButtonType;
import controls.FrontLauncherOutput;
import controls.RoofLauncherOutput;
import controls.TurningKnobType;
import controls.PedalType;
import enums.LauncherState;
import enums.MixingRatio;
import lights.BlueLight;
import lights.HeadLight;
import lights.TurnSignalLight;
import lights.WarningLight;
import truck.IAirportFireTruck;

import java.util.ArrayList;
import java.util.List;

public class CentralUnit implements ICentralUnit {

    private final IAirportFireTruck airportFireTruck;
    private int frontLauncherOutput;
    private int roofLauncherOutput;

    private final String name;
    private final String code;
    private final List<String> authorizedPersons;
    private final IDCardDecoder idCardDecoder;

    public CentralUnit(IAirportFireTruck airportFireTruck) {
        this.airportFireTruck = airportFireTruck;
        this.frontLauncherOutput = 500;
        this.roofLauncherOutput = 500;

        this.name = "DUS | FLF-5";
        this.code = "6072";
        this.authorizedPersons = new ArrayList<>();
        this.idCardDecoder = new IDCardDecoder();
    }

    @Override
    public String getID() {
        return "FT-" + name.replace("|", "").replaceAll("\\s+", "-");
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void authorizePerson(String name) {
        authorizedPersons.add(name);
    }

    @Override
    public void checkAuthentication(byte[] encryptedToken) {
        // TODO: Check for Token length
        String token = idCardDecoder.decrypt(encryptedToken);
        if (token != null) {
            String id = getID();
            String name = token.substring(id.length() + 1, token.length() - code.length() - 1);
            boolean validToken = token.equals(id + "-" + name + "-" + code);
            System.out.println(name);
            System.out.println(validToken);
        }
    }

    //rotates axles to the exact rotation of steering wheel plus turn on
    @Override
    public void turnSteeringWheel(int rotation) {
        airportFireTruck.getDrive().rotateAxles(rotation);
        if (rotation < 0) {
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightsLeft()) {
                light.turnOn();
            }
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightsRight()) {
                light.turnOff();
            }
        } else if (rotation > 0) {
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightsLeft()) {
                light.turnOff();
            }
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightsRight()) {
                light.turnOn();
            }
        } else {
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightsLeft()) {
                light.turnOff();
            }
            for (TurnSignalLight light : airportFireTruck.getTurnSignalLightsRight()) {
                light.turnOff();
            }
        }
    }

    @Override
    public void buttonPress(ButtonType type) {
        switch (type) {
            case ELECTRIC_MOTOR -> {
                if (airportFireTruck.getDrive().motorsOn()) airportFireTruck.getDrive().stopMotors();
                else airportFireTruck.getDrive().startMotors();
            }
            case BLUE_LIGHT -> {
                for (BlueLight light : airportFireTruck.getBlueLights()) {
                    light.toggle();
                }
            }
            case WARNING_LIGHT -> {
                for (WarningLight light : airportFireTruck.getWarningLights()) {
                    light.toggle();
                }
            }
            case ROOF_LIGHT -> {
                for (HeadLight light : airportFireTruck.getHeadLightsRoof()) {
                    light.toggle();
                }
            }
            case HEAD_LIGHT -> {
                for (HeadLight light : airportFireTruck.getHeadLightsFrontLeft()) {
                    light.toggle();
                }
                for (HeadLight light : airportFireTruck.getHeadLightsFrontRight()) {
                    light.toggle();
                }
            }
            case SIDE_LIGHT -> {
                for (HeadLight light : airportFireTruck.getSideLightsLeft()) {
                    light.toggle();
                }
                for (HeadLight light : airportFireTruck.getSideLightsRight()) {
                    light.toggle();
                }
            }
            case FIRE_SELF_PROTECTION -> airportFireTruck.useFloorNozzles(100);

            case LEFT_DOOR -> airportFireTruck.getCabin().getLeftDoor().toggleOpen();
            case RIGHT_DOOR -> airportFireTruck.getCabin().getRightDoor().toggleOpen();

            // joystick for front launcher
            case LEFT_JOYSTICK_LEFT -> airportFireTruck.getFrontLauncher().pan();
            case LEFT_JOYSTICK_RIGHT -> airportFireTruck.getFrontLauncher().switchRatio();
            case LEFT_JOYSTICK_BACK -> airportFireTruck.getFrontLauncher().sprayWater(frontLauncherOutput);

            // joystick for roof launcher
            case RIGHT_JOYSTICK_LEFT -> airportFireTruck.getRoofLauncher().extend();
            case RIGHT_JOYSTICK_RIGHT -> airportFireTruck.getRoofLauncher().switchRatio();
            case RIGHT_JOYSTICK_BACK -> airportFireTruck.getRoofLauncher().sprayWater(roofLauncherOutput);

            case SMART_JOYSTICK_LEFT -> {
                if (airportFireTruck.getFrontLauncher().getState() == LauncherState.INACTIVE)
                    airportFireTruck.getFrontLauncher().pan();
                else if (airportFireTruck.getFrontLauncher().getRatio() == MixingRatio.D)
                    airportFireTruck.getFrontLauncher().pan();
                else
                    airportFireTruck.getFrontLauncher().switchRatio();
            }

            case SMART_JOYSTICK_RIGHT -> {
                if (airportFireTruck.getRoofLauncher().getState() == LauncherState.INACTIVE)
                    airportFireTruck.getRoofLauncher().extend();
                else if (airportFireTruck.getRoofLauncher().getRatio() == MixingRatio.D)
                    airportFireTruck.getRoofLauncher().extend();
                else
                    airportFireTruck.getRoofLauncher().switchRatio();
            }
        }
    }

    @Override
    public void pedalPress(PedalType type) {
        switch (type) {
            case GAS -> airportFireTruck.getDrive().drive(airportFireTruck.getDrive().getCurrentVelocity() + 4);
            case BRAKE -> airportFireTruck.getDrive().drive(airportFireTruck.getDrive().getCurrentVelocity() - 4);
        }
        //update speed/battery displays while driving
        airportFireTruck.getCabin().getSpeedDisplay().setValue(String.valueOf(airportFireTruck.getDrive().getCurrentVelocity()));
        airportFireTruck.getCabin().getBatteryDisplay().setValue(String.valueOf(airportFireTruck.getDrive().getBatteryPercentage()));
    }

    @Override
    public <E> void turningKnobTurn(TurningKnobType type, E setting) {
        switch (type) {
            case FRONT_LAUNCHER -> frontLauncherOutput = Math.min(Math.max(((FrontLauncherOutput) setting).getValue(), 0), 3500);
            case ROOF_LAUNCHER -> roofLauncherOutput = Math.min(Math.max(((RoofLauncherOutput) setting).getValue(), 0), 10000);
        }
    }

}
