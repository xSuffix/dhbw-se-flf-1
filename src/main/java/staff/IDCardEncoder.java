package staff;

import truck.central_unit.ICentralUnit;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class IDCardEncoder {

    public void encode(ICentralUnit centralUnit, IDCard idCard, String name, String password) {
        String id = centralUnit.getID();
        String code = centralUnit.getCode();

        String token = id + "-" + name + "-" + code;
        byte[] encryptedToken;
        try {
            SecretKey key = new SecretKeySpec(password.getBytes(StandardCharsets.UTF_8), "DES");
            encryptedToken = encrypt(token, key);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        idCard.rfidChip().setToken(encryptedToken);
        centralUnit.authorizePerson(name);
    }

    private byte[] encrypt(String input, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(input.getBytes());
    }

}
