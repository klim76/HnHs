package uk.co.ribot.androidboilerplate.ui.auth;

import uk.co.ribot.androidboilerplate.ui.base.MvpView;

/**
 * Created by klim on 10.07.2017.
 */

public interface AuthMvpView extends MvpView {

    void showPasswordHelp();

    void showError(String message);

    void onSuccess(String weather);
}
