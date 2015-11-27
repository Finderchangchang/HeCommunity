package liuliu.custom.method.cube.image.iface;

import android.graphics.drawable.BitmapDrawable;

/**
 * Created by liuliu on 2015/11/27   15:39
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public interface ImageMemoryCache {

    public void set(String key, BitmapDrawable data);

    public BitmapDrawable get(String key);

    public void clear();

    public void delete(String key);

    /**
     * max byte
     *
     * @return
     */
    public long getMaxSize();

    /**
     * used byte
     *
     * @return
     */
    public long getUsedSpace();
}

