package com.example.mvp.mvptest;

/**
 * Created by vadim on 02.11.2017.
 */

public interface MainView {
	void renderState(MainModelState mainModelState);

	String getStatesName();

	void setPresenter(MainPresenter mainPresenter);
}
