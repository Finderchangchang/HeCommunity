package liuliu.he.community.model;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.views.list.ViewHolderBase;
import liuliu.he.community.R;

/**
 * Created by liuliu on 2015/11/28   9:55
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public class StringMiddleImageViewViewHolder extends ViewHolderBase<String> {

    private ImageLoader mImageLoader;
    private CubeImageView mImageView;

    public StringMiddleImageViewViewHolder(ImageLoader imageLoader) {
        mImageLoader = imageLoader;
    }
    
    public View createView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.load_middle_image_list_item, null);
        mImageView = (CubeImageView) view.findViewById(R.id.load_middle_image_list_image_view);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return view;
    }

    @Override
    public void showData(int position, String itemData) {
        mImageView.loadImage(mImageLoader, itemData);
    }
}

