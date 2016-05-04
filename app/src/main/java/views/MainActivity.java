package views;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anderson.expressdelivery.R;

import controllers.AnnouncementController;
import controllers.UserAuthController;
import models.User;
import services.IResult;
import services.IResultUser;
import views.adapters.OnLoadMoreListener;
import views.adapters.RecyclerItemClickListener;
import models.Announcement;

import java.util.List;

public class MainActivity extends GenericActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private AnnouncementAdapter mAdapter;
    private boolean mLayoutGrid;
    private List<Announcement> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLayoutGrid = false;
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        AnnouncementController.getAll(5, new IResult<Announcement>() {
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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AnnouncementAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mList.add(null);
                mAdapter.notifyItemInserted(mList.size() - 1);

                new Handler().post(new Runnable() {
                    @Override
                    public void run() {

                        mList.remove(mList.size() - 1);
                        mAdapter.notifyItemRemoved(mList.size());

                        int index = mList.size();

                        AnnouncementController.getAll(index + 5, new IResult<Announcement>() {
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

                        mAdapter.notifyDataSetChanged();
                        mAdapter.setLoaded();
                    }
                });
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Context contexto = getApplicationContext();
                Intent intent = new Intent(contexto, AnnouncementDetailActivity.class);
                Announcement announcement = mList.get(position);
                intent.putExtra("description", announcement.getDescription());
                intent.putExtra("endereco", announcement.getEndereco());
                intent.putExtra("picture", announcement.getPicture());
                intent.putExtra("telefone", announcement.getTelefone());
                intent.putExtra("tittle", announcement.getTitle());
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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    class AnnouncementAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final int VIEW_TYPE_ITEM = 0;
        private final int VIEW_TYPE_LOADING = 1;

        private OnLoadMoreListener mOnLoadMoreListener;

        private boolean isLoading;
        private int visibleThreshold = 5;
        private int lastVisibleItem, totalItemCount;

        public AnnouncementAdapter() {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (mOnLoadMoreListener != null) {
                            mOnLoadMoreListener.onLoadMore();
                        }
                        isLoading = true;
                    }
                }
            });
        }

        public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
            this.mOnLoadMoreListener = mOnLoadMoreListener;
        }

        @Override
        public int getItemViewType(int position) {
            return mList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_ITEM) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.main_activity_adapter, parent, false);
                return new AnuncioViewHolder(view);
            } else if (viewType == VIEW_TYPE_LOADING) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_loading_item, parent, false);
                return new LoadingViewHolder(view);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof AnuncioViewHolder) {

                Announcement announcement = mList.get(position);
                AnuncioViewHolder announcementViewHolder = (AnuncioViewHolder) holder;
                announcementViewHolder.imageAnuncio.setImageBitmap(announcement.getPicture());
                announcementViewHolder.viewTitulo.setText(announcement.getTitle());
                announcementViewHolder.viewDescricao.setText(announcement.getDescription());

            } else if (holder instanceof LoadingViewHolder) {
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                loadingViewHolder.progressBar.setIndeterminate(true);
            }
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        public void setLoaded() {
            isLoading = false;
        }
    }

    static class AnuncioViewHolder extends RecyclerView.ViewHolder {

        protected TextView viewTitulo;
        protected TextView viewDescricao;
        protected ImageView imageAnuncio;

        public AnuncioViewHolder(View itemView) {
            super(itemView);

            imageAnuncio = (ImageView) itemView.findViewById(R.id.img_main_announcment);
            viewTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
            viewDescricao = (TextView) itemView.findViewById(R.id.txtDescricao);
        }
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_main_announcement);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_manage_cadastro_anuncio) {
            redirect(this, AnnouncementRegisterActivity.class);
        } else if (id == R.id.nav_manage_cadastro_usuario) {
            redirect(this, UserRegisterActivity.class);
        } else if (id == R.id.nav_manage_logout) {
            UserAuthController.getCurrentUser(new IResultUser<User>() {
                @Override
                public void onSuccess(User obj) {
                    UserAuthController.logOut(obj, new IResultUser<User>() {
                        @Override
                        public void onSuccess(User obj) {
                            redirect(MainActivity.this, UserLoginActivity.class);
                        }

                        @Override
                        public void onError(String msg) {
                            showToastMessage(MainActivity.this, msg);
                        }
                    });
                }

                @Override
                public void onError(String msg) {
                    showToastMessage(MainActivity.this, msg);
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
