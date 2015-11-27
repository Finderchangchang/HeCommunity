package liuliu.custom.method.cube.image.iface;

import java.io.OutputStream;

import liuliu.custom.method.cube.image.ImageTask;

/**
 * Created by liuliu on 2015/11/27   15:24
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */

public interface ImageDownloader {

    public boolean downloadToStream(ImageTask imageTask,
                                    String url,
                                    OutputStream outputStream,
                                    ProgressUpdateHandler progressUpdateHandler);
}
