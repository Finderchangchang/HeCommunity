package liuliu.he.community.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.tsz.afinal.annotation.view.CodeNote;
import net.tsz.afinal.cache.ACache;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.list.ListViewDataAdapter;
import liuliu.custom.control.toolbar.TToolbar;
import liuliu.custom.method.Utils;
import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;
import liuliu.he.community.model.ChangeItemModel;
import liuliu.he.community.model.ItemModel;
import liuliu.he.community.ui.first_frag.FenleiFragment;
import liuliu.he.community.ui.first_frag.ShouyeFragment;
import liuliu.he.community.ui.first_frag.WodeFragment;

/**
 * Created by liuliu on 2015/11/28   9:50
 * 首页
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public class MainActivity extends BaseActivity {
    public static MainActivity mIntails;
    @CodeNote(id = R.id.frag_ll)
    FrameLayout frag_ll;
    @CodeNote(id = R.id.total_bottom_shouye_ll, click = "onClick")
    LinearLayout shouye_ll;
    @CodeNote(id = R.id.total_bottom_shouye_iv)
    ImageView shouye_iv;
    @CodeNote(id = R.id.total_bottom_shouye_tv)
    TextView shouye_tv;
    @CodeNote(id = R.id.total_bottom_fenlei_ll, click = "onClick")
    LinearLayout fenlei_ll;
    @CodeNote(id = R.id.total_bottom_fenlei_iv)
    ImageView fenlei_iv;
    @CodeNote(id = R.id.total_bottom_fenlei_tv)
    TextView fenlei_tv;
    @CodeNote(id = R.id.total_bottom_wode_ll, click = "onClick")
    LinearLayout wode_ll;
    @CodeNote(id = R.id.total_bottom_wode_iv)
    ImageView wode_iv;
    @CodeNote(id = R.id.total_bottom_wode_tv)
    TextView wode_tv;
    @CodeNote(id = R.id.total_bottom_gouwuche_ll, click = "onClick")
    LinearLayout gouwuche_ll;
    @CodeNote(id = R.id.total_bottom_gouwuche_iv)
    ImageView gouwuche_iv;
    @CodeNote(id = R.id.total_bottom_gouwuche_tv)
    TextView gouwuche_tv;
    ListViewDataAdapter<String> adapter;
    int mClick;//被点击的项
    List<ChangeItemModel> listbtn;//生成的按钮集合（需要颜色改变的view）
    List<ItemModel> mItems;
    ShouyeFragment shouye = null;
    @CodeNote(id = R.id.main_toolbar)
    TToolbar toolbar;
    ACache mCache;


    @Override
    public void initViews() {
        setContentView(R.layout.activity_main);
        mIntails = this;
        listbtn = new ArrayList<>();
        mItems = new ArrayList();
        Intent intent = getIntent();
        if (intent != null) {
            mUtils = new Utils(this);
            String bottomId = mUtils.IntentGet(intent, "BottomId");
            if (!bottomId.equals("")) {
                mClick = Integer.parseInt(bottomId);
                now_pressed = mClick;
            }
        }
        mCache = ACache.get(this);
        mCache.clear();//清空所有缓存
    }

    @Override
    public void initEvents() {
        mItems.add(new ItemModel("首页", R.mipmap.shouye_normal, R.mipmap.shouye_normal_pressed));
        mItems.add(new ItemModel("分类", R.mipmap.fenlei_normal, R.mipmap.fenlei_normal_pressed));
        mItems.add(new ItemModel("我的", R.mipmap.wode_normal, R.mipmap.wode_normal_pressed));
        mItems.add(new ItemModel("购物车", R.mipmap.gouwuche_normal, R.mipmap.gouwuche_normal_pressed));
        listbtn.add(new ChangeItemModel(shouye_tv, shouye_iv));//添加组件到listview
        listbtn.add(new ChangeItemModel(fenlei_tv, fenlei_iv));//添加组件到listview
        listbtn.add(new ChangeItemModel(wode_tv, wode_iv));//添加组件到listview
        listbtn.add(new ChangeItemModel(gouwuche_tv, gouwuche_iv));//添加组件到listview
        //加载第一个显示页面
        setItem(mClick);
        now_pressed = mClick;
        toolbar.setLeftOnClick(new TToolbar.LeftOnClickListener() {
            @Override
            public void leftclick() {

            }
        });
        toolbar.setCenterOnClick(new TToolbar.CenterOnClickListener() {
            @Override
            public void centerclick() {

            }
        });
        toolbar.setRightOnClick(new TToolbar.RightOnClickListener() {
            @Override
            public void rightclick() {

            }
        });
    }

    private int now_pressed = -1;

    /**
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.total_bottom_shouye_ll:
                if (now_pressed != 0) {
                    setItem(0);
                    now_pressed = 0;
                }
                break;
            case R.id.total_bottom_fenlei_ll:
                if (now_pressed != 1) {
                    setItem(1);
                    now_pressed = 1;
                }
                break;
            case R.id.total_bottom_wode_ll:
                if (now_pressed != 2) {
                    setItem(2);
                    now_pressed = 2;
                }
                break;
            case R.id.total_bottom_gouwuche_ll:
                mUtils.IntentPost(DetailListsActivity.class, new Utils.putListener() {
                    @Override
                    public void put(Intent intent) {
                        intent.putExtra("desc", "gwc?");
                    }
                });
                break;
        }
    }

    private void setItem(int position) {
        //恢复成未点击状态
        listbtn.get(mClick).getTv().setTextColor(mIntails.getResources().getColor(R.color.main_item_normal));
        Bitmap bitmap = Utils.readBitMap(mIntails, mItems.get(mClick).getNormal_img());
        listbtn.get(mClick).getIv().setImageBitmap(bitmap);
        //设置为点击状态
        listbtn.get(position).getTv().setTextColor(mIntails.getResources().getColor(R.color.main_item_pressed));
        listbtn.get(position).getIv().setImageBitmap(Utils.readBitMap(mIntails, mItems.get(position).getPressed_img()));
        mClick = position;
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        final FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                shouye = new ShouyeFragment();
                transaction.replace(R.id.frag_ll, shouye);
                shouye.setOnItemClick(new ShouyeFragment.OnItemClick() {
                    @Override
                    public void onItemClick(Object value) {
                        now_pressed = (Integer) value;
                        setItem(now_pressed);
                    }
                });
                break;
            case 1:
                transaction.replace(R.id.frag_ll, new FenleiFragment());
                break;
            case 2:
                transaction.replace(R.id.frag_ll, new WodeFragment());
                break;
        }
        transaction.commit();
    }
}
