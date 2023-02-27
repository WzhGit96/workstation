import org.junit.Test;
import org.wzhframework.workstation.utils.Rsa2;

import java.security.NoSuchAlgorithmException;

/**
 * @author wzh
 * @since 2023/2/27
 */
public class Rsa2Test {

    @Test
    public void getRsaKeyPair() throws NoSuchAlgorithmException {
        Rsa2.genKeyPair();
    }
}
