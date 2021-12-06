package controls;

import staff.IDCard;
import truck.central_unit.ICentralUnit;

public record IDCardReceiver(ICentralUnit centralUnit) {

    public void read(IDCard idCard) {
        byte[] token = idCard.rfidChip().getToken();
        centralUnit.validateToken(token);
    }

}
