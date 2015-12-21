package liuliu.he.community.adapter;

import in.srain.cube.views.list.*;

/**
 * Created by Administrator on 2015/12/1.
 */
public interface ViewHolderCreator<ItemDataType> {
    ViewHolderBase<ItemDataType> createViewHolder();
}
