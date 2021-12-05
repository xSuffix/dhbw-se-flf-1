package truck.central_unit;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class IDCardDecoder {

    private final SecretKey key;

    public IDCardDecoder() {
        this.key = new SecretKeySpec("password".getBytes(StandardCharsets.UTF_8), "DES");
    }

    protected String decrypt(byte[] token) {
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] deciphered = cipher.doFinal(token);
            return new String(deciphered);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
