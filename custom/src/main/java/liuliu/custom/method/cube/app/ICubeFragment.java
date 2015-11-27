package liuliu.custom.method.cube.app;

/**
 * Created by liuliu on 2015/11/27   16:07
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public interface ICubeFragment {

    /**
     * pass the data from {@link CubeFragmentActivity#pushFragmentToBackStack(Class, Object)}to this fragment
     *
     * @param data
     */
    void onEnter(Object data);

    void onLeave();

    void onBack();

    void onBackWithData(Object data);

    /**
     * process the return back logic
     * return true if back pressed event has been processed and should stay in current view
     *
     * @return
     */
    boolean processBackPressed();
}
