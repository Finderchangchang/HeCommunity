package liuliu.he.community.ui.demo;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import liuliu.he.community.R;
import liuliu.he.community.model.GoodModel;
import liuliu.he.community.test.ViewHolderBase;

/**
 * Created by liuliu on 2015/11/28   9:55
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public class GouwucheViewViewHolder extends ViewHolderBase<GoodModel> {

    private ImageLoader mImageLoader;
    private CubeImageView mImageView;
    Button jia_btn;
    Button jian_btn;
    EditText count_tv;
    TextView good_name;
    TextView good_price;
    TextView good_guige;


    public GouwucheViewViewHolder(ImageLoader imageLoader) {
        mImageLoader = imageLoader;
    }

    @Override
    public View createView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.item_gouwuche, null);
        mImageView = (CubeImageView) view.findViewById(R.id.good_img);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        jia_btn = (Button) view.findViewById(R.id.count_jia_btn);
        jian_btn = (Button) view.findViewById(R.id.count_jian_btn);
        count_tv = (EditText) view.findViewById(R.id.num_count_tv);
        good_name = (TextView) view.findViewById(R.id.good_name_tv);
        good_price = (TextView) view.findViewById(R.id.good_price_tv);
        good_guige = (TextView) view.findViewById(R.id.good_guige_tv);
//        jia_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count_tv.setText("jia");
//            }
//        });
        jian_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_tv.setText("jian");
            }
        });
        return view;
    }

    public void setClick() {

    }

    @Override
    public void showData(int position, GoodModel item) {
        mImageView.loadImage(mImageLoader, item.getGoodImgUrl());
        good_name.setText(item.getGoodName());
        good_price.setText(item.getGoodPrice());
        good_guige.setText(item.getGoodDesc());
        count_tv.setText(item.getGoodCount() + "");
    }
}

