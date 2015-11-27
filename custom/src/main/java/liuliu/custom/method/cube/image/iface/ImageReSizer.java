package liuliu.custom.method.cube.image.iface;

import android.graphics.BitmapFactory;

import liuliu.custom.method.cube.image.ImageTask;

/**
 * Created by liuliu on 2015/11/27   15:29
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public interface ImageReSizer {

    /**
     * Return the {@link BitmapFactory.Options#inSampleSize}, which will be used when load the image from the disk.
     * <p/>
     * You should better calculate this value according the hard device of the mobile.
     */
    public int getInSampleSize(ImageTask imageTask);

    public String getRemoteUrl(ImageTask imageTask);
}
