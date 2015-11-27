package liuliu.custom.method.cube.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.srain.cube.util.CLog;
import liuliu.custom.method.cube.app.lifecycle.IComponentContainer;
import liuliu.custom.method.cube.app.lifecycle.LifeCycleComponent;
import liuliu.custom.method.cube.app.lifecycle.LifeCycleComponentManager;
import liuliu.custom.method.cube.util.CubeDebug;

/**
 * Created by liuliu on 2015/11/27   16:06
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public abstract class CubeFragment extends Fragment implements ICubeFragment, IComponentContainer {

    private static final boolean DEBUG = CubeDebug.DEBUG_LIFE_CYCLE;
    protected Object mDataIn;
    private boolean mFirstResume = true;

    private LifeCycleComponentManager mComponentContainer = new LifeCycleComponentManager();

    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public CubeFragmentActivity getContext() {
        return (CubeFragmentActivity) getActivity();
    }

    /**
     * ===========================================================
     * Implements {@link ICubeFragment}
     * ===========================================================
     */
    @Override
    public void onEnter(Object data) {
        mDataIn = data;
        if (DEBUG) {
            showStatus("onEnter");
        }
    }

    @Override
    public void onLeave() {
        if (DEBUG) {
            showStatus("onLeave");
        }
        mComponentContainer.onBecomesTotallyInvisible();
    }

    @Override
    public void onBackWithData(Object data) {
        if (DEBUG) {
            showStatus("onBackWithData");
        }
        mComponentContainer.onBecomesVisibleFromTotallyInvisible();
    }

    @Override
    public boolean processBackPressed() {
        return false;
    }

    @Override
    public void onBack() {
        if (DEBUG) {
            showStatus("onBack");
        }
        mComponentContainer.onBecomesVisibleFromTotallyInvisible();
    }

    /**
     * ===========================================================
     * Implements {@link IComponentContainer}
     * ===========================================================
     */
    @Override
    public void addComponent(LifeCycleComponent component) {
        mComponentContainer.addComponent(component);
    }

    /**
     * Not add self to back stack when removed, so only when Activity stop
     */
    @Override
    public void onStop() {
        super.onStop();
        if (DEBUG) {
            showStatus("onStop");
        }
        onLeave();
    }

    /**
     * Only when Activity resume, not very precise.
     * When activity recover from partly invisible, onBecomesPartiallyInvisible will be triggered.
     */
    @Override
    public void onResume() {
        super.onResume();
        if (!mFirstResume) {
            onBack();
        }
        if (mFirstResume) {
            mFirstResume = false;
        }
        if (DEBUG) {
            showStatus("onResume");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (DEBUG) {
            showStatus("onAttach");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DEBUG) {
            showStatus("onCreate");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (DEBUG) {
            showStatus("onActivityCreated");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (DEBUG) {
            showStatus("onStart");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (DEBUG) {
            showStatus("onPause");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (DEBUG) {
            showStatus("onDestroyView");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (DEBUG) {
            showStatus("onDestroy");
        }
        mComponentContainer.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (DEBUG) {
            showStatus("onDetach");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (DEBUG) {
            showStatus("onCreateView");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void showStatus(String status) {
        final String[] className = ((Object) this).getClass().getName().split("\\.");
        CLog.d("cube-lifecycle", "%s %s", className[className.length - 1], status);
    }
}

