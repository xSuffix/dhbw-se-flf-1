package truck.central_unit;

import controls.ButtonType;
import controls.TurningKnobType;
import controls.PedalType;
import staff.IDCard;

public interface ICentralUnit {

    String getID();

    String getCode();

    void authorizePerson(String name);

    void validateToken(byte[] encryptedToken);

    void turnSteeringWheel(int rotation);

    void buttonPress(ButtonType type);

    void pedalPress(PedalType type);

    <E> void turningKnobTurn(TurningKnobType type, E setting);

}
