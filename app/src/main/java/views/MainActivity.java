package views;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.anderson.expressdelivery.R;

import java.util.List;

import controllers.AnnouncementController;
import controllers.UserAuthController;
import models.Announcement;
import models.User;
import services.IResult;
import services.IResultGeneric;
import services.IResultUser;
import utils.AnnouncementUtils;
import views.adapters.AnnouncementAdapter;
import views.adapters.OnLoadMoreListener;
import views.adapters.RecyclerItemClickListener;

public class MainActivity extends GenericActivity {

    private NavigationView navigationView;
    private RecyclerView mRecyclerView;
    private AnnouncementAdapter mAdapter;
    private List<Announcement> mList;
    private int maxListSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
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
                menuOptions(item.getItemId(), MainActivity.this);
                return true;
            }
        });

        updateAnnouncment(5, 0);

        AnnouncementController.getSize(new IResultGeneric() {
            @Override
            public void onSuccess(Integer value) {
                maxListSize = value;
            }

            @Override
            public void onError(String msg) {

            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AnnouncementAdapter(this.mList, getBaseContext(), this.mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent i = new Intent(MainActivity.this, AnnouncementDetailsActivity.class);
                        i.putExtra("bundle", AnnouncementUtils.sendExtras(mList.get(position)));
                        startActivity(i);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
//                        showToastMessage(getApplicationContext(), "Texto", Toast.LENGTH_LONG);
                    }
                }));


        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                mList.add(null);
                mAdapter.notifyItemInserted(mList.size() - 1);

                //Load more data for reyclerview
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //Remove loading item
                        mList.remove(mList.size() - 1);
                        mAdapter.notifyItemRemoved(mList.size());

                        //Load data
                        AnnouncementController.get(5, mList.size(), new IResult<Announcement>() {
                            @Override
                            public void onSuccess(List<Announcement> list) {
                                mList.addAll(list);
                            }

                            @Override
                            public void onSuccess(Announcement obj) {

                            }

                            @Override
                            public void onError(String msg) {

                            }
                        });
                        mAdapter.notifyDataSetChanged();
                        mAdapter.setLoaded();
                    }
                }, 2500);
            }
        });

        this.checkAddress();
    }

    public void updateAnnouncment(int size, int skip) {
        AnnouncementController.get(size, skip, new IResult<Announcement>() {
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


    @Override
    protected void onResume() {
        super.onResume();
        this.setVisibleMenuItem(this.navigationView);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void menuOptions(int id, final Activity context) {

        if (id == R.id.nav_manage_cadastro_anuncio) {
            redirect(context, AnnouncementRegisterActivity.class);
            finish();
        } else if (id == R.id.nav_manage_cadastro_usuario) {
            redirect(this, UserRegisterActivity.class);
        } else if (id == R.id.nav_manage_logout) {
            UserAuthController.getCurrentUser(new IResultUser<User>() {
                @Override
                public void onSuccess(User obj) {
                    UserAuthController.logOut(obj, new IResultUser<User>() {
                        @Override
                        public void onSuccess(User obj) {
                            Intent i = new Intent(context, UserLoginActivity.class);
                            startActivity(i);
                        }

                        @Override
                        public void onError(String msg) {

                        }
                    });
                }

                @Override
                public void onError(String msg) {

                }
            });
        } else if (id == R.id.nav_login) {
            redirect(context, UserLoginActivity.class);
        } else if (id == R.id.nav_my_announcements) {
            redirect(context, UserAnnouncementActivity.class);
        } else if (id == R.id.nav_register_address) {
            redirect(context, UserRegisterAddressActivity.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void setVisibleMenuItem(NavigationView navigationView) {
        if (this.getCurrentUser() == null) {
            this.setVisible(navigationView, false, true, false, false, false, true);
        } else {
            this.setVisible(navigationView, true, false, true, true, true, false);
        }
    }

    public void setVisible(NavigationView navigationView, boolean... value) {
        for (int i = 0; i < value.length; i++) {
            navigationView.getMenu().getItem(i).setVisible(value[i]);
        }
    }

    private void checkAddress() {
        final Bundle extras = getIntent().getExtras();

        if (extras != null && extras.containsKey("isNew") && extras.getBoolean("isNew")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);

            dialog.setMessage(R.string.add_address)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick (DialogInterface dialog,int which){
                            redirect(MainActivity.this, UserRegisterAddressActivity.class, extras);
                            finish();
                        }
                    }).
                    setNegativeButton(R.string.after, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick (DialogInterface dialog,int which){

                        }
                    }).show();
        }
    }
}
