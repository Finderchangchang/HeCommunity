package liuliu.he.community.test;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Administrator on 2015/12/1.
 */
public abstract class ViewHolderBase<ItemDataType> {
    protected int mLastPosition;
    protected int mPosition = -1;
    protected View mCurrentView;
    private final SparseArray<View> mViews;

    public ViewHolderBase() {
        this.mViews = new SparseArray<View>();
    }

    public abstract View createView(LayoutInflater var1);

    public abstract void showData(int var1, ItemDataType var2);

    public void setItemData(int position, View view) {
        this.mLastPosition = this.mPosition;
        this.mPosition = position;
        this.mCurrentView = view;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mCurrentView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public boolean stillHoldLastItemData() {
        return this.mLastPosition == this.mPosition;
    }
}

