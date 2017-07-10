package uk.co.ribot.androidboilerplate.ui.auth;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.util.AndroidComponentUtil;
import uk.co.ribot.androidboilerplate.util.DialogFactory;
import uk.co.ribot.androidboilerplate.util.ViewUtil;

public class AuthActivity extends BaseActivity implements AuthMvpView, View.OnClickListener, View.OnTouchListener {

    @Inject AuthPresenter mAuthPresenter;

    @BindView(R.id.sign_in_layout) RelativeLayout relativeLayout;
    @BindView(R.id.sign_in_email) EditText etMail;
    @BindView(R.id.sign_in_password) EditText etPassword;
    @BindView(R.id.sign_in_hint_email) TextInputLayout tilHintMail;
    @BindView(R.id.sign_in_hint_password) TextInputLayout tilHintPassword;
    @BindView(R.id.sign_in_enter) Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       // etMail.addTextChangedListener();
        //etPassword.setOnFocusChangeListener(this);
        btnEnter.setOnClickListener(this);
        etPassword.setOnTouchListener(this);

        mAuthPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mAuthPresenter.detachView();
    }




    /***** MVP View methods implementation *****/

    @Override
    public void showPasswordHelp() {
        DialogFactory.createGenericErrorDialog(this, R.string.password_help)
                .show();
    }

    @Override
    public void showError(String message) {
        DialogFactory.createGenericErrorDialog(this, message)
                .show();
    }

    @Override
    public void onSuccess(String weather) {
        Snackbar.make(relativeLayout, weather, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in_enter:
                ViewUtil.hideKeyboard(this);
                mAuthPresenter.tryEnter(etMail.getText().toString(), etPassword.getText().toString());
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.sign_in_password:
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (etPassword.getRight() - etPassword.getCompoundDrawables()[2].getBounds().width())) {
                        DialogFactory.createGenericErrorDialog(this, R.string.password_help)
                                .show();

                        return true;
                    }
                }
                break;
        }
        return false;
    }
}
