package liuliu.he.community.test;

import android.content.Intent;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import liuliu.custom.method.Utils;
import liuliu.he.community.model.TopImage;
import liuliu.he.community.ui.activity.GoodDetailActivity;
import liuliu.he.community.ui.activity.GoodListActivity;
import liuliu.he.community.ui.activity.HelpActivity;
import liuliu.he.community.ui.activity.MainActivity;

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

    public ViewHolderBase setHeight(int viewId, int height) {
        LinearLayout view = getView(viewId);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = height;
        view.setLayoutParams(lp);
        return this;
    }

    public ViewHolderBase setMargin(int viewId, int left, int right, int top, int bottom) {
        LinearLayout ll = (LinearLayout) getView(viewId);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll.getLayoutParams();
        layoutParams.setMargins(left, right, top, bottom);//4个参数按顺序分别是左上右下
        ll.setLayoutParams(layoutParams);
        return this;
    }

    public ViewHolderBase setVisible(int viewId, int visible) {
        getView(viewId).setVisibility(visible);
        return this;
    }

//    public ViewHolderBase loadImage(int viewId, ImageLoader loader, String url) {
//        loadImage(viewId, loader, url, null);
//        return this;
//    }

    public ViewHolderBase loadImageByUrl(int viewId, ImageLoader loader, final TopImage model) {
        CubeImageView view = getView(viewId);
        view.loadImage(loader, model.getImg());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setVisibility(View.VISIBLE);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jiexiLink(model.getLink());
            }
        });

        return this;
    }

    public ViewHolderBase loadImage(int viewId, ImageLoader loader, String url) {
        CubeImageView view = getView(viewId);
        view.loadImage(loader, url);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setVisibility(View.VISIBLE);

        return this;
    }

    /**
     * 解析link
     *
     * @param link = "../product/detail.php?id=852";
     */
    private void jiexiLink(final String link) {
        if (link.contains("product")) {
            if (link.contains("detail.php")) {//跳转到商品的详细页面
                MainActivity.mIntails.mUtils.IntentPost(GoodDetailActivity.class, new Utils.putListener() {
                    @Override
                    public void put(Intent intent) {
                        intent.putExtra("id", link.split("=")[1]);
                    }
                });
            } else if (link.contains("list.php")) {//跳转到商品列表
                MainActivity.mIntails.mUtils.IntentPost(GoodListActivity.class, new Utils.putListener() {
                    @Override
                    public void put(Intent intent) {
                        intent.putExtra("type", link.split("=")[1]);
                    }
                });
            }
        } else if (link.contains("user")) {//跳转到帮助中心
            if (link.contains("help.php")) {
                MainActivity.mIntails.mUtils.IntentPost(HelpActivity.class);
            }
        }
    }

    public interface OnImageClick {
        void click();
    }

    public void loadClick(OnImageClick click) {

    }

    public ViewHolderBase setText(int viewId, String val) {
        TextView view = getView(viewId);
        view.setText(val);
        return this;
    }
}

