package views;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.anderson.expressdelivery.R;
import controllers.UserAuthController;
import models.User;
import services.IResultUser;

public class MainActivity extends GenericActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;

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
        this.navigationView.setNavigationItemSelectedListener(this);
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
        } else if (id == R.id.nav_login) {
            redirect(this, UserLoginActivity.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setVisibleMenuItem(NavigationView navigationView) {
        if (this.getUsername().equals("")) {
            this.setVisible(navigationView, false, true, false, true);
        } else {
            this.setVisible(navigationView, true, false, true, false);
        }
    }

    private void setVisible(NavigationView navigationView, boolean... value) {
        for (int i = 0; i < value.length; i++) {
            navigationView.getMenu().getItem(i).setVisible(value[i]);
        }
    }
}
