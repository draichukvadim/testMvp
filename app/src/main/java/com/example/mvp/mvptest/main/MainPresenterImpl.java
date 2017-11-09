package com.example.mvp.mvptest.main;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by vadim on 02.11.2017.
 */

public class MainPresenterImpl implements MainPresenter {
	private MainView mainView;
	private MainModel mainModel;

	private Subscription subscription;

	public MainPresenterImpl(MainModel model) {
		this.mainModel = model;
	}

	@Override
	public void onStartStateLoading() {
		this.mainModel.getState();
	}

	@Override
	public void onPresenterAttached(MainView mainView) {
		this.mainView = mainView;
		createModelSubscription();
	}

	@Override
	public void onPresenterDeattached() {
		this.mainView = null;
		releaseModelSubscription();

	}

	private void createModelSubscription() {
		this.subscription = this.mainModel.getState().subscribe(renderState);
	}

	private void releaseModelSubscription() {
		if (subscription != null && !subscription.isUnsubscribed()) {
			subscription.unsubscribe();
		}
	}

	private Action1<MainModelState> renderState = new Action1<MainModelState>() {
		@Override
		public void call(MainModelState mainModelState) {
			if (mainView != null) {
				mainView.renderState(mainModelState);
			}
		}
	};
}
