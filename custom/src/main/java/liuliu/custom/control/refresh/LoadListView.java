package liuliu.custom.control.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import liuliu.custom.R;

/**
 * Created by Administrator on 2015/12/11.
 */
public class LoadListView extends ListView implements AbsListView.OnScrollListener {
    public LoadListView(Context context) {
        super(context);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    View footer;
    LinearLayout footerView;

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        footer = inflater.inflate(R.layout.loadmore_footer, null);//获得底部布局
        footerView = (LinearLayout) footer.findViewById(R.id.load_more_layout);
        footerView.setVisibility(GONE);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (totalItemCount == lastVisibleItem && scrollState == SCROLL_STATE_IDLE) {//滚动停止，且滚动到底部
            if (!isLoading) {
                isLoading = true;
                footerView.setVisibility(VISIBLE);
                listener.onLoad();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    int lastVisibleItem;
    int totalItemCount;
    boolean isLoading;
    public onLoadListener listener;

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    //加载更多数据的回调接口
    public interface onLoadListener {
        void onLoad();
    }

    //加载完毕
    public void loadComplete() {
        isLoading = false;
//        footerView.setVisibility(GONE);
    }

    public void setOnLoadListener(onLoadListener listener) {
        this.listener = listener;
    }

}
