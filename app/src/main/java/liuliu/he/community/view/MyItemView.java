package liuliu.he.community.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import liuliu.he.community.R;


/**
 * Created by Administrator on 2015/11/17.
 */
public class MyItemView extends RelativeLayout {
    ImageView leftImage, rightImage;
    TextView leftText, rightText;
    boolean isBottom, isTop, isCenter;
    LinearLayout top_big_line, top_small_line, bottom_big_line, bottom_small_line;

    public MyItemView(Context context) {
        super(context);
    }

    public MyItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.my_iem_view, this, true);
        leftImage = (ImageView) view.findViewById(R.id.my_item_left_image);
        rightImage = (ImageView) view.findViewById(R.id.my_item_right_image);
        leftText = (TextView) view.findViewById(R.id.my_item_left_text);
        rightText = (TextView) view.findViewById(R.id.my_item_right_text);

        top_big_line = (LinearLayout) view.findViewById(R.id.item_view_top_big_line);
        top_small_line = (LinearLayout) view.findViewById(R.id.item_view_top_small_line);
        bottom_big_line = (LinearLayout) view.findViewById(R.id.item_view_bottom_big_line);
        bottom_small_line = (LinearLayout) view.findViewById(R.id.item_view_bottom_small_line);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyItemView);
        Drawable d = array.getDrawable(R.styleable.MyItemView_myitem_leftimage);
        leftImage.setImageDrawable(d);
        d = array.getDrawable(R.styleable.MyItemView_myitem_rightimage);
        rightImage.setImageDrawable(d);
        leftText.setText(array.getText(R.styleable.MyItemView_myitem_lefttext));
        rightText.setText(array.getText(R.styleable.MyItemView_myitem_righttext));
        isTop = array.getBoolean(R.styleable.MyItemView_is_top, false);
        isBottom = array.getBoolean(R.styleable.MyItemView_is_bottom, false);
        isCenter = array.getBoolean(R.styleable.MyItemView_is_center, false);
        setLine();
    }

    public void setRightNumber(String mes) {
        rightText.setText(mes);
    }

    private void setLine() {
        if (isTop) {//顶部有短的
            setVisible(true, false, false, true);
        } else if (isBottom) {//底部有短的
            setVisible(false, false, true, false);
        } else if (isCenter) {
            setVisible(false, false, false, true);
        } else {
            setVisible(true, false, true, false);
        }
    }

    private void setVisible(boolean topbig, boolean topsmall, boolean bottombig, boolean bottomsmall) {
        if (topbig) {
            top_big_line.setVisibility(VISIBLE);
        } else {
            top_big_line.setVisibility(GONE);
        }
        if (topsmall) {
            top_small_line.setVisibility(VISIBLE);
        } else {
            top_small_line.setVisibility(GONE);
        }
        if (bottombig) {
            bottom_big_line.setVisibility(VISIBLE);
        } else {
            bottom_big_line.setVisibility(GONE);
        }
        if (bottomsmall) {
            bottom_small_line.setVisibility(VISIBLE);
        } else {
            bottom_small_line.setVisibility(GONE);
        }
    }
}
