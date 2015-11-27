package liuliu.custom.method.cube.image.iface;

import liuliu.custom.method.cube.image.ImageLoadRequest;

/**
 * Created by liuliu on 2015/11/27   15:41
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public interface NameGenerator {

    /**
     * @param request
     * @return
     */
    public String generateIdentityUrlFor(ImageLoadRequest request);
}

