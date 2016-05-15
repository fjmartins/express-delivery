package views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.anderson.expressdelivery.R;

import java.util.List;

import controllers.AnnouncementController;
import controllers.ProposalController;
import controllers.UserAuthController;
import models.Announcement;
import models.Proposal;
import services.IResult;
import views.adapters.AnnouncementAdapter;
import views.adapters.ProposalAdapter;
import views.adapters.RecyclerItemClickListener;


/**
 * Created by morte on 15/05/2016.
 */
public class UserProposalListActivity extends GenericActivity {

    private NavigationView navigationView;
    private RecyclerView mRecyclerView;
    private ProposalAdapter mAdapter;
    private List<Proposal> mList;
    private Announcement announcement;

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

        updateProposal();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ProposalAdapter(this.mList, getBaseContext(), this.mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Context contexto = getApplicationContext();
                        String texto = "CURTO";
                        int duracao = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(contexto, texto, duracao);
                        toast.show();
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
    }


    public void updateProposal() {


        Bundle extras = getIntent().getExtras();

        Bitmap bitmap = extras.getParcelable("picture");

        this.announcement = new Announcement(null, extras.get("tittle").toString(),
                extras.get("description").toString(), extras.get("address").toString(),
                extras.get("phone").toString(), bitmap);
        announcement.setUser(extras.getString("username"));
        announcement.setId(extras.get("id").toString());

        ProposalController.getByAnnouncement(this.announcement, new IResult<Proposal>() {
            @Override
            public void onSuccess(List<Proposal> list) {
                mList = list;
            }

            @Override
            public void onSuccess(Proposal obj) {

            }

            @Override
            public void onError(String msg) {

            }
        });
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
