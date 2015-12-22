package liuliu.he.community.ui.activity;

import android.content.RestrictionsManager;

import net.tsz.afinal.annotation.view.CodeNote;

import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.request.CacheAbleRequest;
import in.srain.cube.request.RequestCacheManager;
import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;

/**
 * Created by Administrator on 2015/12/22.
 */
public class DemosActivity extends BaseActivity {
    @CodeNote(id = R.id.iv)
    CubeImageView iv;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_demo);
        ImageLoader imageLoader = ImageLoaderFactory.create(this);
        RequestCacheManager.init(this, "/data/data/liuliu.hecommunity/ioc", 1024, 1024);
        iv.loadImage(imageLoader, "http://img3.imgtn.bdimg.com/it/u=3772931735,1198783178&fm=21&gp=0.jpg");
    }

    @Override
    public void initEvents() {

    }
}
