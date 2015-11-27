package liuliu.custom.method.cube.app.lifecycle;

/**
 * Created by liuliu on 2015/11/27   15:04
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public interface LifeCycleComponent {
    /**
     * The UI becomes partially invisible.
     * like {@link android.app.Activity#onPause}
     */
    public void onBecomesPartiallyInvisible();

    /**
     * The UI becomes visible from partially or totally invisible.
     * like {@link android.app.Activity#onResume}
     */
    public void onBecomesVisible();

    /**
     * The UI becomes totally invisible.
     * like {@link android.app.Activity#onStop}
     */
    public void onBecomesTotallyInvisible();

    /**
     * The UI becomes visible from totally invisible.
     * like {@link android.app.Activity#onRestart}
     */
    public void onBecomesVisibleFromTotallyInvisible();

    /**
     * like {@link android.app.Activity#onDestroy}
     */
    public void onDestroy();
}
