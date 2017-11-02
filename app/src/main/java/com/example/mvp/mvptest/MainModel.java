package com.example.mvp.mvptest;

import rx.Observable;

/**
 * Created by vadim on 02.11.2017.
 */

public interface MainModel {
	Observable<MainModelState> getState();
}
