package liuliu.he.community.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import liuliu.he.community.test.LazyViewHolderCreator;
import liuliu.he.community.test.ViewHolderBase;
import liuliu.he.community.test.ViewHolderCreator;

/**
 * Created by liuliu on 2015/12/01   9:19
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public abstract class DataAdapterBase<ItemDataType> extends BaseAdapter {
    protected ViewHolderCreator<ItemDataType> mLazyCreator;
    Context mContext;
    int mLayoutId;
    List<ItemDataType> mList;

    public DataAdapterBase(Context context, int layoutId, List<ItemDataType> list) {
        mContext = context;
        mLayoutId = layoutId;
        mList = list;
        mLazyCreator = LazyViewHolderCreator.create(context, ViewHolderBase.class, "123");
    }

    private ViewHolderBase<ItemDataType> createViewHolder() {
        if (this.mLazyCreator == null) {
            throw new RuntimeException("view holder creator is null");
        } else {
            return this.mLazyCreator != null ? this.mLazyCreator.createViewHolder() : null;
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderBase holderBase = this.createViewHolder();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (holderBase != null) {
            convertView = inflater.inflate(mLayoutId, null);
            if (convertView != null) {
                convertView.setTag(holderBase);
                holderBase.setItemData(position, convertView);
                convert(holderBase, getItem(position), position);
            }
        }
        return convertView;
    }

    public abstract void convert(ViewHolderBase holder, ItemDataType t, int position);

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public ItemDataType getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
