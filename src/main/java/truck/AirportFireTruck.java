package truck;

import cabin.Cabin;
import drive.*;
import enums.ExtinguishingType;
import lights.*;
import truck.central_unit.CentralUnit;

public class AirportFireTruck implements IAirportFireTruck {
    private final HeadLight[] headLightsFrontLeft;
    private final HeadLight[] headLightsFrontRight;
    private final HeadLight[] headLightsRoof;
    private final HeadLight[] sideLightsLeft;
    private final HeadLight[] sideLightsRight;
    private final TurnSignalLight[] turnSignalLightsLeft;
    private final TurnSignalLight[] turnSignalLightsRight;
    private final BrakeLight brakeLightLeft;
    private final BrakeLight brakeLightRight;
    private final BlueLight[] blueLights;
    private final WarningLight[] warningLights;
    private final Drive drive;
    private final Cabin cabin;
    private final CentralUnit centralUnit;
    private final Tank waterTank;
    private final Tank foamPowderTank;
    private final MixingUnit mixingUnit;
    private final FrontLauncher frontLauncher;
    private final RoofLauncher roofLauncher;
    private final FloorSprayingNozzle[] floorSprayingNozzles;

    private AirportFireTruck(Builder builder) {
        this.headLightsFrontLeft = builder.headLightsFrontLeft;
        this.headLightsFrontRight = builder.headLightsFrontRight;
        this.headLightsRoof = builder.headLightsRoof;
        this.sideLightsLeft = builder.sideLightsLeft;
        this.sideLightsRight = builder.sideLightsRight;
        this.turnSignalLightsLeft = builder.turnSignalLightsLeft;
        this.turnSignalLightsRight = builder.turnSignalLightsRight;
        this.brakeLightLeft = builder.brakeLightLeft;
        this.brakeLightRight = builder.brakeLightRight;
        this.blueLights = builder.blueLights;
        this.warningLights = builder.warningLights;
        this.drive = builder.drive;
        this.centralUnit = new CentralUnit(this);
        this.cabin = new Cabin(this.centralUnit,builder.smartJoySticks);
        this.waterTank = builder.waterTank;
        this.foamPowderTank = builder.foamPowderTank;
        this.mixingUnit = builder.mixingUnit;
        this.frontLauncher = builder.frontLauncher;
        this.roofLauncher = builder.roofLauncher;
        this.floorSprayingNozzles = builder.floorSprayingNozzles;
    }

    public void chargeTruck(int amount) {
        this.getDrive().charge(amount);
        this.getCabin().getBatteryDisplay().setValue(String.valueOf(this.getDrive().getBatteryPercentage()));
    }

    public void useFloorNozzles(int amount) {
        for (FloorSprayingNozzle nozzle : floorSprayingNozzles) {
            nozzle.sprayWater(amount);
        }
    }

    public HeadLight[] getHeadLightsFrontLeft() {
        return headLightsFrontLeft;
    }

    public HeadLight[] getHeadLightsFrontRight() {
        return headLightsFrontRight;
    }

    public HeadLight[] getHeadLightsRoof() {
        return headLightsRoof;
    }

    public HeadLight[] getSideLightsLeft() {
        return sideLightsLeft;
    }

    public HeadLight[] getSideLightsRight() {
        return sideLightsRight;
    }

    public TurnSignalLight[] getTurnSignalLightsLeft() {
        return turnSignalLightsLeft;
    }

    public TurnSignalLight[] getTurnSignalLightsRight() {
        return turnSignalLightsRight;
    }

    public BrakeLight getBrakeLightLeft() {
        return brakeLightLeft;
    }

    public BrakeLight getBrakeLightRight() {
        return brakeLightRight;
    }

    public BlueLight[] getBlueLights() {
        return blueLights;
    }

    public WarningLight[] getWarningLights() {
        return warningLights;
    }

    public Drive getDrive() {
        return drive;
    }

    public CentralUnit getCentralUnit() {
        return centralUnit;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public Tank getWaterTank() {
        return waterTank;
    }

    public Tank getFoamPowderTank() {
        return foamPowderTank;
    }

    public MixingUnit getMixingUnit() {
        return mixingUnit;
    }

    public FrontLauncher getFrontLauncher() {
        return frontLauncher;
    }

    public RoofLauncher getRoofLauncher() {
        return roofLauncher;
    }

    public FloorSprayingNozzle[] getFloorSprayingNozzles() {
        return floorSprayingNozzles;
    }


    public static class Builder {
        private final HeadLight[] headLightsFrontLeft;
        private final HeadLight[] headLightsFrontRight;
        private final HeadLight[] headLightsRoof;
        private final HeadLight[] sideLightsLeft;
        private final HeadLight[] sideLightsRight;
        private final TurnSignalLight[] turnSignalLightsLeft;
        private final TurnSignalLight[] turnSignalLightsRight;
        private final BrakeLight brakeLightLeft;
        private final BrakeLight brakeLightRight;
        private final BlueLight[] blueLights;
        private final WarningLight[] warningLights;
        private final Drive drive;
        private final Tank waterTank;
        private final Tank foamPowderTank;
        private final MixingUnit mixingUnit;
        private final FrontLauncher frontLauncher;
        private final RoofLauncher roofLauncher;
        private final FloorSprayingNozzle[] floorSprayingNozzles;
        private final boolean smartJoySticks;

        public Builder(boolean smartJoySticks) {
            this.smartJoySticks = smartJoySticks;
            this.headLightsFrontLeft = new HeadLight[3];
            this.headLightsFrontRight = new HeadLight[3];
            for (int i = 0; i < headLightsFrontLeft.length; i++) {
                this.headLightsFrontLeft[i] = new HeadLight();
                this.headLightsFrontRight[i] = new HeadLight();
            }
            this.headLightsRoof = new HeadLight[4];
            for (int i = 0; i < headLightsRoof.length; i++) {
                this.headLightsRoof[i] = new HeadLight();
            }
            this.sideLightsLeft = new HeadLight[5];
            this.sideLightsRight = new HeadLight[5];
            for (int i = 0; i < sideLightsLeft.length; i++) {
                this.sideLightsLeft[i] = new HeadLight();
                this.sideLightsRight[i] = new HeadLight();
            }

            this.turnSignalLightsLeft = new TurnSignalLight[2];
            this.turnSignalLightsRight = new TurnSignalLight[2];
            for (int i = 0; i < turnSignalLightsLeft.length; i++) {
                this.turnSignalLightsLeft[i] = new TurnSignalLight();
                this.turnSignalLightsRight[i] = new TurnSignalLight();
            }

            this.brakeLightLeft = new BrakeLight();
            this.brakeLightRight = new BrakeLight();

            this.blueLights = new BlueLight[10];
            // front small
            for (int i = 0; i < 2; i++) {
                this.blueLights[i] = new BlueLight(1);
            }
            // front roof big
            for (int i = 2; i < 6; i++) {
                this.blueLights[i] = new BlueLight(4);
            }
            // back roof medium
            for (int i = 6; i < 10; i++) {
                this.blueLights[i] = new BlueLight(2);
            }

            this.warningLights = new WarningLight[2];
            for (int i = 0; i < warningLights.length; i++) {
                this.warningLights[i] = new WarningLight(1);
            }

            ElectricMotor[] electricMotors = new ElectricMotor[2];
            for (int i = 0; i < electricMotors.length; i++) {
                electricMotors[i] = new ElectricMotor();
            }
            BatteryBox batteryBox = new BatteryBox();
            SteeringAxle[] frontAxles = new SteeringAxle[2];
            for (int i = 0; i < frontAxles.length; i++) {
                frontAxles[i] = new SteeringAxle();
            }
            Axle[] backAxles = new Axle[2];
            for (int i = 0; i < backAxles.length; i++) {
                backAxles[i] = new Axle();
            }
            this.drive = new Drive(electricMotors, batteryBox, frontAxles, backAxles);

            this.waterTank = new Tank(ExtinguishingType.WATER, 75, 30, 15);
            this.foamPowderTank = new Tank(ExtinguishingType.FOAM_POWDER, 75, 30, 5);
            this.mixingUnit = new MixingUnit(this.waterTank, this.foamPowderTank);
            this.frontLauncher = new FrontLauncher(this.mixingUnit);
            this.roofLauncher = new RoofLauncher(this.mixingUnit);
            this.floorSprayingNozzles = new FloorSprayingNozzle[7];
            for (int i = 0; i < floorSprayingNozzles.length; i++) {
                this.floorSprayingNozzles[i] = new FloorSprayingNozzle(this.waterTank);
            }
        }

        public AirportFireTruck build() {
            return new AirportFireTruck(this);
        }

    }

}
