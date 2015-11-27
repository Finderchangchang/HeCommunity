package liuliu.custom.method.cube.image.iface;

import java.util.concurrent.Executor;

/**
 * Created by liuliu on 2015/11/27   15:30
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public interface ImageTaskExecutor extends Executor {
    void setTaskOrder(int order);
}
