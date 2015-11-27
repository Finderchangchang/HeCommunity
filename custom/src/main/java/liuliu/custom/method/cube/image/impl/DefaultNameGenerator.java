package liuliu.custom.method.cube.image.impl;

import liuliu.custom.method.cube.image.ImageLoadRequest;
import liuliu.custom.method.cube.image.iface.NameGenerator;

/**
 * Created by liuliu on 2015/11/27   16:01
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public class DefaultNameGenerator implements NameGenerator {

    private static DefaultNameGenerator sInstance;

    public static synchronized DefaultNameGenerator getInstance() {
        if (sInstance == null) {
            sInstance = new DefaultNameGenerator();
        }
        return sInstance;
    }

    @Override
    public String generateIdentityUrlFor(ImageLoadRequest request) {
        return request.getUrl();
    }
}
