package liuliu.he.community.test;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;

/**
 * Created by Administrator on 2015/12/1.
 */
public class ViewHolderBase<T> {
    protected int mLastPosition;
    protected int mPosition = -1;
    protected View mCurrentView;
    private SparseArray<View> mViews;


    public ViewHolderBase(String s) {
        this.mViews = new SparseArray<View>();
    }

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

    public ViewHolderBase loadImage(int viewId, ImageLoader loader, String url) {
        CubeImageView view = getView(viewId);
        view.loadImage(loader, url);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return this;
    }
}

