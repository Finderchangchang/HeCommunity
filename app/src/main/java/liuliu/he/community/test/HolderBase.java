package liuliu.he.community.test;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Administrator on 2015/12/1.
 */
public abstract class HolderBase<ItemDataType> {
    int mLastPosition;
    protected int mPosition = -1;
    protected View mCurrentView;

    public HolderBase() {

    }

    public abstract View createView(LayoutInflater var1);

    public abstract void showData(int var1, ItemDataType var2);

    public void setItemData(int position, View view) {
        this.mLastPosition = this.mPosition;
        this.mPosition = position;
        this.mCurrentView = view;
    }

    public boolean stillHoldLastItemData() {
        return this.mLastPosition == this.mPosition;
    }
}
