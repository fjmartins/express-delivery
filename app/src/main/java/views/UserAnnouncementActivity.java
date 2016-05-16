package views;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.anderson.expressdelivery.R;

import java.util.List;

import controllers.AnnouncementController;
import controllers.UserAuthController;
import models.Announcement;
import services.IResult;
import utils.AnnouncementUtils;
import views.adapters.AnnouncementAdapter;
import views.adapters.RecyclerItemClickListener;

/**
 * Created by anderson on 10/05/16.
 * Refactoring morte on 16/05/16.
 */
public class UserAnnouncementActivity extends MainActivity {

    private NavigationView navigationView;
    private RecyclerView mRecyclerView;
    private AnnouncementAdapter mAdapter;
    private List<Announcement> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        this.isUserAuth(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        overridePendingTransition(R.anim.register_activity_enter, R.anim.main_activity_exit);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        this.navigationView = (NavigationView) findViewById(R.id.nav_view);
        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                menuOptions(item.getItemId(), UserAnnouncementActivity.this);
                return true;
            }
        });

        setVisibleMenuItem(this.navigationView);

        updateAnnouncementList();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AnnouncementAdapter(this.mList, getBaseContext(), this.mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        redirect(UserAnnouncementActivity.this, AnnouncementDetailsActivity.class,
                                AnnouncementUtils.sendExtras(mList.get(position)));
                        finish();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                }));
    }


    @Override
    protected void onResume() {
        super.onResume();
        this.isUserAuth(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }

    public void updateAnnouncementList() {
        AnnouncementController.getMy(UserAuthController.getCurrentUser(), new IResult<Announcement>() {
            @Override
            public void onSuccess(List<Announcement> list) {
                mList = list;
            }

            @Override
            public void onSuccess(Announcement obj) {

            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
