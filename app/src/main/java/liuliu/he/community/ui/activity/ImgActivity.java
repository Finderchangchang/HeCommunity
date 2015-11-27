package liuliu.he.community.ui.activity;

import android.content.Context;
import android.widget.ListView;

import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;

import liuliu.custom.method.cube.image.CubeImageView;
import liuliu.custom.method.cube.image.ImageLoader;
import liuliu.custom.method.cube.image.ImageLoaderFactory;
import liuliu.he.community.R;
import liuliu.he.community.adapter.CommonAdapter;
import liuliu.he.community.adapter.CommonViewHolder;
import liuliu.he.community.base.BaseActivity;

/**
 * Created by liuliu on 2015/11/27   14:54
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public class ImgActivity extends BaseActivity {
    //    @CodeNote(id = R.id.iv_item_image_list_big)
//    CubeImageView img_ll;
    @CodeNote(id = R.id.img_listview)
    ListView ll;
    Context context;
    CommonAdapter adapter;
    List<String> mList;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_img);
        context = this;
        initDatas();
        ll.setAdapter(new CommonAdapter<String>(context, mList, R.layout.item_test) {
            @Override
            public void convert(CommonViewHolder holder, String o, int position) {
                ImageLoader imageLoader = ImageLoaderFactory.create(context);
                CubeImageView view = holder.getView(R.id.tt);
                view.loadImage(imageLoader, "http://a.hiphotos.baidu.com/image/pic/item/30adcbef76094b36b4fe75eda1cc7cd98d109d45.jpg");
            }
        });

    }

    //加载数据
    private void initDatas() {
        mList = new ArrayList<>();
        String imgUrl = "http://pic24.nipic.com/20120920/10361578_112230424175_2.jpg";
        for (int i = 1; i <= 200; i++) {
            mList.add(imgUrl + "?rank=" + i);
        }
    }

    @Override
    public void initEvents() {

    }
}
