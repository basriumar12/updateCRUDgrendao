package id.co.blogbasbas.tryormsqlite.fragment;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.co.blogbasbas.tryormsqlite.R;
import id.co.blogbasbas.tryormsqlite.db.DaoSession;
import id.co.blogbasbas.tryormsqlite.db.User;
import id.co.blogbasbas.tryormsqlite.db.UserDao;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.btn_save)
    Button btnSave;
    User user;
    DaoSession daoSession;
    boolean createNew = false;
    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, view);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String us = username.getText().toString();
                String ps = password.getText().toString();
                String em = email.getText().toString();
                if (us.isEmpty()){
                    username.setError("Username tidak bisa kosong");
                }else if (ps.isEmpty()){
                    password.setError("password tidak bisa kosong");
                } else if (em.isEmpty()){
                    email.setError("email tidak bisa kosong");
                } else {
                    UserDao userDao = daoSession.getUserDao();
                    User user = new User();
                    user.setUsername(username.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.setEmail(email.getText().toString());
                    userDao.insert(user);
                     Snackbar.make(view,"berhasil input",Snackbar.LENGTH_LONG).show();
                    //Toast.makeText(getActivity(), "Berhasil Regis", Toast.LENGTH_LONG).show();

                   /* List<User> users = daoSession.getUserDao().queryBuilder().list();
                    Log.e("MainActivity", "OnCreate");
                    for (User usera : users) {
                        Log.e("MainActivity.java", usera.getEmail() + " | " + usera.getUsername());
                    }*/

                }

                }
        });






        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
