package liuliu.custom.method.cube.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by liuliu on 2015/11/27   16:13
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public class Encrypt {
    private Encrypt() {
    }

    /**
     * A hashing method that changes a string (like a URL) into a hash string
     */
    public static String md5(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    public static String bytesToHexString(byte[] bytes) {
        // http://stackoverflow.com/questions/332079
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}

