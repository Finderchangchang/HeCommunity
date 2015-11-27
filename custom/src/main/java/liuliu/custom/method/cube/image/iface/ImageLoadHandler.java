package liuliu.custom.method.cube.image.iface;

import android.graphics.drawable.BitmapDrawable;

import liuliu.custom.method.cube.image.CubeImageView;
import liuliu.custom.method.cube.image.ImageTask;

/**
 * Created by liuliu on 2015/11/27   15:28
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public interface ImageLoadHandler {

    /**
     * When begin to load the image from disk or network.
     */
    void onLoading(ImageTask imageTask, CubeImageView cubeImageView);

    /**
     * After image is loaded.
     */
    void onLoadFinish(ImageTask imageTask, CubeImageView cubeImageView, BitmapDrawable drawable);

    /**
     * Some errors has occurred
     */
    void onLoadError(ImageTask imageTask, CubeImageView imageView, int errorCode);
}
