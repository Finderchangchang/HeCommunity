package liuliu.he.community.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import in.srain.cube.util.CLog;
import in.srain.cube.util.CubeDebug;

/**
 * Created by liuliu on 2015/12/01   9:19
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public abstract class DataAdapterBase<ItemDataType> extends BaseAdapter {

    private static final String LOG_TAG = "cube-list";
    protected ViewHolderCreator<ItemDataType> mViewHolderCreator;
    protected ViewHolderCreator<ItemDataType> mLazyCreator;
    protected boolean mForceCreateView = false;

    public DataAdapterBase() {
    }

    public DataAdapterBase(Object enclosingInstance, Class<?> cls) {
        this.setViewHolderClass(enclosingInstance, cls, new Object[0]);
    }

    public DataAdapterBase(ViewHolderCreator<ItemDataType> viewHolderCreator) {
        this.mViewHolderCreator = viewHolderCreator;
    }

    public void setViewHolderCreator(ViewHolderCreator<ItemDataType> viewHolderCreator) {
        this.mViewHolderCreator = viewHolderCreator;
    }

    public void setViewHolderClass(Object enclosingInstance, Class<?> cls, Object... args) {
        this.mLazyCreator = LazyViewHolderCreator.create(enclosingInstance, cls, args);
    }

    private ViewHolderBase<ItemDataType> createViewHolder() {
        if (this.mViewHolderCreator == null && this.mLazyCreator == null) {
            throw new RuntimeException("view holder creator is null");
        } else {
            return this.mViewHolderCreator != null ? this.mViewHolderCreator.createViewHolder() : (this.mLazyCreator != null ? this.mLazyCreator.createViewHolder() : null);
        }
    }

    public void forceCreateView(boolean yes) {
        this.mForceCreateView = yes;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (CubeDebug.DEBUG_LIST) {
            CLog.d("cube-list", "getView %s", new Object[]{Integer.valueOf(position)});
        }

        Object itemData = this.getItem(position);
        ViewHolderBase holderBase = null;
        if (!this.mForceCreateView && convertView != null && convertView.getTag() instanceof ViewHolderBase) {
            holderBase = (ViewHolderBase) convertView.getTag();
        } else {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            holderBase = this.createViewHolder();
            if (holderBase != null) {
                convertView = holderBase.createView(inflater);
                if (convertView != null && !this.mForceCreateView) {
                    convertView.setTag(holderBase);
                }
            }
        }

        if (holderBase != null) {
            holderBase.setItemData(position, convertView);
            holderBase.showData(position, itemData);
            convert(holderBase, getItem(position), position);
        }
        return convertView;
    }
    public abstract void convert(ViewHolderBase holder, ItemDataType t,int position);
    public abstract ItemDataType getItem(int var1);
}
