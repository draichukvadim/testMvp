package com.example.mvp.mvptest;

import rx.Observable;

/**
 * Created by vadim on 02.11.2017.
 */

public class MainModelImpl implements MainModel {
	@Override
	public Observable<MainModelState> getState() {
		return Observable.just(new MainModelState("Oslik"));
	}
}
