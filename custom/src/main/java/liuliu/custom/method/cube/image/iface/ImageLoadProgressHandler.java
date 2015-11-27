package liuliu.custom.method.cube.image.iface;

import liuliu.custom.method.cube.image.CubeImageView;
import liuliu.custom.method.cube.image.ImageTask;

/**
 * Created by liuliu on 2015/11/27   15:28
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public interface ImageLoadProgressHandler {

    void onProgressChange(ImageTask imageTask, CubeImageView cubeImageView, int loaded, int total);
}
