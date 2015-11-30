package liuliu.he.community.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.util.CLog;
import in.srain.cube.util.CubeDebug;
import in.srain.cube.views.list.LazyViewHolderCreator;
import in.srain.cube.views.list.ViewHolderBase;
import in.srain.cube.views.list.ViewHolderCreator;

/**
 * Created by liuliu on 2015/11/30   15:48
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public abstract class CommonBaseAdapter<ItemDataType> extends BaseAdapter {
    private static final String LOG_TAG = "cube-list";

    protected ViewHolderCreator<ItemDataType> mViewHolderCreator;
    protected ViewHolderCreator<ItemDataType> mLazyCreator;
    protected boolean mForceCreateView = false;
    protected ArrayList<ItemDataType> mItemDataList = new ArrayList<ItemDataType>();

    public CommonBaseAdapter() {

    }

    public CommonBaseAdapter(final Object enclosingInstance, final Class<?> cls) {
        setViewHolderClass(enclosingInstance, cls);
    }

    /**
     * @param viewHolderCreator The view holder creator will create a View Holder that extends {@link ViewHolderBase}
     */
    public CommonBaseAdapter(ViewHolderCreator<ItemDataType> viewHolderCreator) {
        mViewHolderCreator = viewHolderCreator;
    }

    public void setViewHolderCreator(ViewHolderCreator<ItemDataType> viewHolderCreator) {
        mViewHolderCreator = viewHolderCreator;
    }

    public void setViewHolderClass(final Object enclosingInstance, final Class<?> cls, final Object... args) {
        mLazyCreator = LazyViewHolderCreator.create(enclosingInstance, cls, args);
    }

    private ViewHolderBase<ItemDataType> createViewHolder() {
        if (mViewHolderCreator == null && mLazyCreator == null) {
            throw new RuntimeException("view holder creator is null");
        }
        if (mViewHolderCreator != null) {
            return mViewHolderCreator.createViewHolder();
        }
        if (mLazyCreator != null) {
            return mLazyCreator.createViewHolder();
        }
        return null;
    }

    public void forceCreateView(boolean yes) {
        mForceCreateView = yes;
    }

    public ArrayList<ItemDataType> getDataList() {
        return mItemDataList;
    }

    @Override
    public int getCount() {
        return mItemDataList.size();
    }

    @Override
    public ItemDataType getItem(int position) {
        if (mItemDataList.size() <= position || position < 0) {
            return null;
        }
        return mItemDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (CubeDebug.DEBUG_LIST) {
            CLog.d(LOG_TAG, "getView %s", position);
        }
        ItemDataType itemData = getItem(position);
        ViewHolderBase<ItemDataType> holderBase = null;
        if (mForceCreateView || convertView == null || (!(convertView.getTag() instanceof ViewHolderBase<?>))) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            holderBase = createViewHolder();
            if (holderBase != null) {
                convertView = holderBase.createView(inflater);
                if (convertView != null) {
                    if (!mForceCreateView) {
                        convertView.setTag(holderBase);
                    }
                }
            }
        } else {
            holderBase = (ViewHolderBase<ItemDataType>) convertView.getTag();
        }
        if (holderBase != null) {
            holderBase.setItemData(position, convertView);
            holderBase.showData(position, itemData);
        }
        return convertView;
    }

    public abstract void convert(CommonViewHolder holder, List<ItemDataType> t, int position);
}
