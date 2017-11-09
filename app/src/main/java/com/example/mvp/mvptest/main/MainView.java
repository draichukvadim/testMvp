package com.example.mvp.mvptest.main;

/**
 * Created by vadim on 02.11.2017.
 */

public interface MainView {
	void renderState(MainModelState mainModelState);

	void openSettingsActivity();

	String getStatesName();

	void setPresenter(MainPresenter mainPresenter);
}
