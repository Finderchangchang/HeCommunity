package liuliu.he.community.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import liuliu.he.community.R;


/**
 * Created by Administrator on 2015/11/17.
 */
public class MyItemView extends RelativeLayout {
    ImageView leftImage, rightImage;
    TextView leftText, rightText;

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

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyItemView);
        Drawable d = array.getDrawable(R.styleable.MyItemView_myitem_leftimage);
        leftImage.setImageDrawable(d);
        d = array.getDrawable(R.styleable.MyItemView_myitem_rightimage);
        rightImage.setImageDrawable(d);
        leftText.setText(array.getText(R.styleable.MyItemView_myitem_lefttext));
        rightText.setText(array.getText(R.styleable.MyItemView_myitem_righttext));
    }

}
