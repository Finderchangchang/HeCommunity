package liuliu.he.community.ui;

import android.view.View;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import liuliu.he.community.R;
import liuliu.he.community.adapter.CommonAdapter;
import liuliu.he.community.adapter.CommonViewHolder;
import liuliu.he.community.base.BaseActivity;
import liuliu.he.community.model.CartModel;

/**
 * Created by Administrator on 2015/11/20.
 */
public class CartList extends BaseActivity {
    ListView lv;
    CommonAdapter adapter;

    @Override
    public void initViews() {
        loadDate();
    }

    private void loadDate() {
        setContentView(R.layout.activity_shopping_cart_list);
        lv = (ListView) findViewById(R.id.cart_list_lv);
        final List<CartModel> lsit = new ArrayList<CartModel>();
        CartModel cart;
        cart = new CartModel();
        cart.setName("加绒打底裤");
        cart.setNum("1");
        cart.setPrice("29.9");
        cart.setBitmap(getResources().getDrawable(R.mipmap.logo));
        lsit.add(cart);
        cart = new CartModel();
        cart.setName("毛衣");
        cart.setNum("2");
        cart.setPrice("120");
        cart.setBitmap(getResources().getDrawable(R.mipmap.logo));
        lsit.add(cart);
        cart = new CartModel();
        cart.setName("牛仔裤");
        cart.setNum("8");
        cart.setPrice("89");
        cart.setBitmap(getResources().getDrawable(R.mipmap.logo));
        lsit.add(cart);
        cart = new CartModel();
        cart.setName("运动鞋");
        cart.setNum("1");
        cart.setPrice("85.9");
        cart.setBitmap(getResources().getDrawable(R.mipmap.logo));
        lsit.add(cart);

        adapter = new CommonAdapter(this, lsit, R.layout.cart_item) {

            @Override
            public void convert(final CommonViewHolder holder, Object o, final int position) {
                CartModel cart = (CartModel) o;
                holder.setText(R.id.cart_name, cart.getName());
                holder.setText(R.id.cart_num, cart.getNum());
                holder.setText(R.id.cart_pice, cart.getPrice());
                holder.setImageDrawable(R.id.cart_image, cart.getBitmap());
                holder.setOnClickListener(R.id.btn_add, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = holder.getText(R.id.cart_num);
                        int num = (Integer.valueOf(str)) + 1;
                        holder.setText(R.id.cart_num, num + "");
                    }
                });
                holder.setOnClickListener(R.id.btn_reduction, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = holder.getText(R.id.cart_num);
                        int num = (Integer.valueOf(str)) - 1;
                        if (num <= 0) {
                            holder.setText(R.id.cart_num, "1");
                        } else {
                            holder.setText(R.id.cart_num, num + "");
                        }
                    }
                });
                holder.setOnClickListener(R.id.btn_delete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lsit.remove(position);
                        lv.setAdapter(adapter);
                    }
                });
            }
        };

        lv.setAdapter(adapter);
    }

    @Override
    public void initEvents() {

    }
}
