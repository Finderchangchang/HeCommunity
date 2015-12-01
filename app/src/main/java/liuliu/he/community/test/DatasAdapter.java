package liuliu.he.community.test;

import java.util.ArrayList;

/**
 * Created by liuliu on 2015/12/01   9:18
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public abstract class DatasAdapter<ItemDataType> extends DataAdapterBase<ItemDataType> {
    protected ArrayList<ItemDataType> mItemDataList = new ArrayList<ItemDataType>();

    public DatasAdapter() {

    }

    /**
     * @param viewHolderCreator The view holder creator will create a View Holder that extends {@link ViewHolderBase}
     */
    public DatasAdapter(ViewHolderCreator<ItemDataType> viewHolderCreator) {
        super(viewHolderCreator);
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
}
