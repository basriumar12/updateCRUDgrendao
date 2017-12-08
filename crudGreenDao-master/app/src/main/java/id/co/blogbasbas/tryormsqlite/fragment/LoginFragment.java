package id.co.blogbasbas.tryormsqlite.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import id.co.blogbasbas.tryormsqlite.App;
import id.co.blogbasbas.tryormsqlite.MainActivity;
import id.co.blogbasbas.tryormsqlite.R;
import id.co.blogbasbas.tryormsqlite.RegisterActivity;
import id.co.blogbasbas.tryormsqlite.db.DaoSession;
import id.co.blogbasbas.tryormsqlite.db.User;
import id.co.blogbasbas.tryormsqlite.db.UserDao;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    @BindView(R.id.etEmail)
    TextInputEditText etEmail;
    @BindView(R.id.inputEmail)
    TextInputLayout inputEmail;
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;
    @BindView(R.id.inputPassword)
    TextInputLayout inputPassword;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.btnSignin)
    Button btnSignin;
    @BindView(R.id.btnToRegister)
    Button btnToRegister;
    Unbinder unbinder;
    UserDao userDao;

    FragmentCallback fragmentCallback;
    InputMethodManager inputMethodManager;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnSignin, R.id.btnToRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSignin:
                String em = etEmail.getText().toString();
                String ps = etPassword.getText().toString();
                if (em.isEmpty()){
                    inputEmail.setError("Input Username");
                } else if (ps.isEmpty()){
                    inputPassword.setError("Input Password");
                } else {

                    DaoSession daoSession = App.getDaoSession();
                    UserDao userDao = daoSession.getUserDao();

                    User useremail = userDao.queryBuilder().where(UserDao.Properties.Email.eq(em),
                            UserDao.Properties.Email.eq(em)).unique();
                    User userpass = userDao.queryBuilder().where(UserDao.Properties.Password.eq(ps),
                            UserDao.Properties.Password.eq(ps)).unique();
                    if (useremail != null && userpass != null){
                        //login
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }else{
                        //not match
                        Snackbar.make(view,"Gagal Login",Snackbar.LENGTH_LONG).show();
                    }


                    ///if (em.equals())

                }


                break;
            case R.id.btnToRegister:
       fragmentCallback.toRegisterFragment();


                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentCallback = (FragmentCallback) context;
        inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
    }
}
