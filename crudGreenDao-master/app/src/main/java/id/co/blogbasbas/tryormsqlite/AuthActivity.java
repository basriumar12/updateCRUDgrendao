package id.co.blogbasbas.tryormsqlite;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import id.co.blogbasbas.tryormsqlite.db.DaoSession;
import id.co.blogbasbas.tryormsqlite.db.User;
import id.co.blogbasbas.tryormsqlite.db.UserDao;
import id.co.blogbasbas.tryormsqlite.fragment.FragmentCallback;
import id.co.blogbasbas.tryormsqlite.fragment.LoginFragment;
import id.co.blogbasbas.tryormsqlite.fragment.RegisterFragment;

public class AuthActivity extends AppCompatActivity implements FragmentCallback{
    User user;
    DaoSession daoSession;
    boolean createNew = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        if (savedInstanceState == null) {
            replaceFragment(new LoginFragment());
        }


    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    @Override
    public void toRegisterFragment() {
       // replaceFragment(new RegisterFragment());
        startActivity(new Intent(this,RegisterActivity.class));
    }

    @Override
    public void toLoginFragment() {
        replaceFragment(new LoginFragment());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
