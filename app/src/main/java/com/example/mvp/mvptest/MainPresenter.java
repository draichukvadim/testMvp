package com.example.mvp.mvptest;

/**
 * Created by vadim on 02.11.2017.
 */

public interface MainPresenter {
	void onStartStateLoading();

	void onPresenterAttached(MainView mainView);

	void onPresenterDeattached();
}