package uk.co.ribot.androidboilerplate.ui.auth;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.ui.main.MainMvpView;
import uk.co.ribot.androidboilerplate.util.RxUtil;

/**
 * Created by klim on 10.07.2017.
 */
@ConfigPersistent
public class AuthPresenter extends BasePresenter<AuthMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public AuthPresenter(DataManager dataManager){
        mDataManager = dataManager;
    }

    @Override
    public void attachView(AuthMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void logIn() {
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
       /* mSubscription = mDataManager.getRibots()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Ribot>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the ribots.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Ribot> ribots) {
                        if (ribots.isEmpty()) {
                            getMvpView().showRibotsEmpty();
                        } else {
                            getMvpView().showRibots(ribots);
                        }
                    }
                });*/
    }

    public void tryEnter(String mail, String password){
        if(!checkMail(mail)){
            getMvpView().showError("Не верно указана почта!");
            return;
        }

        if(!checkPassword(password)){
            getMvpView().showError("Пароль не отвечает требованиям безопасности!");
            return;
        }
        logIn();
    }

    public boolean checkPassword(String testPassword){
        return testPassword.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$");
    }

    public boolean checkMail(String testedMail){
        return testedMail.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }
}
