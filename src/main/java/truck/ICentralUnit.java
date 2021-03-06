package truck;

import cabin.controls.ButtonType;
import cabin.controls.PedalType;
import cabin.controls.TurningKnobType;

public interface ICentralUnit {
    void turnSteeringWheel(int rotation);

    void buttonPress(ButtonType type);

    void pedalPress(PedalType type);

    <E> void turningKnobTurn(TurningKnobType type, E setting);

    String getID();

    String getCode();

    void authorizePerson(String name);

    void checkAuthentication(byte[] encryptedToken);

}
