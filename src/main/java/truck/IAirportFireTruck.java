package truck;

import cabin.Cabin;
import drive.Drive;
import lights.*;
import truck.central_unit.CentralUnit;

public interface IAirportFireTruck {

    void chargeTruck(int amount);

    void useFloorNozzles(int amount);

    HeadLight[] getHeadLightsFrontLeft();

    HeadLight[] getHeadLightsFrontRight();

    HeadLight[] getHeadLightsRoof();

    HeadLight[] getSideLightsLeft();

    HeadLight[] getSideLightsRight();

    TurnSignalLight[] getTurnSignalLightsLeft();

    TurnSignalLight[] getTurnSignalLightsRight();

    BrakeLight getBrakeLightLeft();

    BrakeLight getBrakeLightRight();

    BlueLight[] getBlueLights();

    WarningLight[] getWarningLights();

    Drive getDrive();

    CentralUnit getCentralUnit();

    Cabin getCabin();

    Tank getWaterTank();

    Tank getFoamPowderTank();

    MixingUnit getMixingUnit();

    FrontLauncher getFrontLauncher();

    RoofLauncher getRoofLauncher();

    FloorSprayingNozzle[] getFloorSprayingNozzles();

}
