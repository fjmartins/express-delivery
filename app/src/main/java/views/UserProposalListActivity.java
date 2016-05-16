package views;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.anderson.expressdelivery.R;

import java.util.List;

import controllers.ProposalController;
import controllers.UserAuthController;
import models.Announcement;
import models.Proposal;
import models.User;
import services.IResult;
import services.IResultUser;
import views.adapters.ProposalAdapter;


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
        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_manage_cadastro_anuncio) {
                    redirect(UserProposalListActivity.this, AnnouncementRegisterActivity.class);
                    finish();
                } else if (id == R.id.nav_manage_logout) {
                    UserAuthController.getCurrentUser(new IResultUser<User>() {
                        @Override
                        public void onSuccess(User obj) {
                            UserAuthController.logOut(obj, new IResultUser<User>() {
                                @Override
                                public void onSuccess(User obj) {
                                    redirect(UserProposalListActivity.this, UserLoginActivity.class);
                                }

                                @Override
                                public void onError(String msg) {
                                    showToastMessage(UserProposalListActivity.this, msg);
                                }
                            });
                        }

                        @Override
                        public void onError(String msg) {
                            showToastMessage(UserProposalListActivity.this, msg);
                        }
                    });
                }else if (id == R.id.nav_my_announcements) {
                    redirect(UserProposalListActivity.this, UserAnnouncementActivity.class);
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        this.isUserAuth(this);

        updateProposal();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ProposalAdapter(this.mList, getBaseContext(), this.mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

//        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView,
//                new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        Context contexto = getApplicationContext();
//                        String texto = "CURTO";
//                        int duracao = Toast.LENGTH_SHORT;
//                        Toast toast = Toast.makeText(contexto, texto, duracao);
//                        toast.show();
//                    }
//
//                    @Override
//                    public void onItemLongClick(View view, int position) {
//                        Context contexto = getApplicationContext();
//                        String texto = "LONGO";
//                        int duracao = Toast.LENGTH_SHORT;
//                        Toast toast = Toast.makeText(contexto, texto, duracao);
//                        toast.show();
//                    }
//                }));
    }

    public void refuse(View view){
        showToastMessage(this, "RECUSADO");
    }

    public void accept(View view){
        showToastMessage(this, "ACEITO");
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
            this.setVisible(navigationView, true, false, true, true, false);
        }
    }


    public void setVisible(NavigationView navigationView, boolean... value) {
        for (int i = 0; i < value.length; i++) {
            navigationView.getMenu().getItem(i).setVisible(value[i]);
        }
    }
}
