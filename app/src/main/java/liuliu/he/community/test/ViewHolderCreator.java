package liuliu.he.community.test;

import in.srain.cube.views.list.*;

/**
 * Created by Administrator on 2015/12/1.
 */
public interface ViewHolderCreator<ItemDataType> {
    ViewHolderBase<ItemDataType> createViewHolder();
}
