import com.hotjavi.Util.Md5Util;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ylei on 17-4-6.
 */
public class Md5Test {
    @Test
    public void testmd5() {
        try {
            System.out.println(Md5Util.EncoderByMd5("Leiyi6429533"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
