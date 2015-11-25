package liuliu.he.community.ui.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;

/**
 * Created by Administrator on 2015/11/15.
 */
public class MenuActiviy extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    public void initViews() {
        setContentView(R.layout.right_menu_item);
    }

    @Override
    public void initEvents() {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
