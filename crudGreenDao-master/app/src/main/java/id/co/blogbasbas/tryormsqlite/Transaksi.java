package id.co.blogbasbas.tryormsqlite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import id.co.blogbasbas.tryormsqlite.db.DaoSession;
import id.co.blogbasbas.tryormsqlite.db.User;
import id.co.blogbasbas.tryormsqlite.db.UserDao;

/**
 * Created by User on 07/12/2017.
 */

public class Transaksi extends AppCompatActivity {

    DaoSession daoSession;
    private static Transaksi instance;
    public static Transaksi getInstance(){
        if (instance == null){
            instance = new Transaksi();

        }
        return instance;

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        daoSession = ((App) getApplication()).getDaoSession();
    }

    public UserDao userDaoSession(){
        UserDao userDao = daoSession.getUserDao();
        return userDao;
    }


    private void insertItem() {
        UserDao userDao = daoSession.getUserDao();
        User user = new User();
        userDao.insert(user);
    }
}
