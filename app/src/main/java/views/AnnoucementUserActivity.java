package views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anderson.expressdelivery.R;

import java.util.List;

import controllers.AnnouncementController;
import controllers.UserAuthController;
import models.Announcement;
import services.IResult;
import services.IResultGeneric;
import views.adapters.AnnouncementAdapter;
import views.adapters.OnLoadMoreListener;
import views.adapters.RecyclerItemClickListener;

/**
 * Created by anderson on 10/05/16.
 */
public class AnnoucementUserActivity extends GenericActivity {

    private NavigationView navigationView;
    private RecyclerView mRecyclerView;
    private AnnouncementAdapter mAdapter;
    private List<Announcement> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        this.navigationView = (NavigationView) findViewById(R.id.nav_view);
        setVisibleMenuItem(this.navigationView);


        this.isUserAuth(this);

        updateAnnouncment();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AnnouncementAdapter(this.mList, getBaseContext(), this.mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Context contexto = getApplicationContext();
                        Intent intent = new Intent(contexto, AnnouncementDetailActivity.class);
                        Announcement announcement = mList.get(position);
                        intent.putExtra("description", announcement.getDescription());
                        intent.putExtra("address", announcement.getAddress());
                        intent.putExtra("picture", announcement.getPicture());
                        intent.putExtra("phone", announcement.getPhone());
                        intent.putExtra("tittle", announcement.getTitle());
                        intent.putExtra("id", announcement.getId());
                        intent.putExtra("username", announcement.getUser());
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Context contexto = getApplicationContext();
                        String texto = "LONGO";
                        int duracao = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(contexto, texto, duracao);
                        toast.show();
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
                        AnnouncementController.getMy(UserAuthController.getCurrentUser(), new IResult<Announcement>() {
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

    }

    public void updateAnnouncment() {
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

    @Override
    protected void onResume() {
        super.onResume();
        this.isUserAuth(this);
    }

    public void setVisibleMenuItem(NavigationView navigationView) {
        if (this.getUsername().equals("")) {
            this.setVisible(navigationView, false, true, false, false, true);
        } else {
            this.setVisible(navigationView, true, false, false, true, false);
        }
    }


    public void setVisible(NavigationView navigationView, boolean... value) {
        for (int i = 0; i < value.length; i++) {
            navigationView.getMenu().getItem(i).setVisible(value[i]);
        }
    }

}
