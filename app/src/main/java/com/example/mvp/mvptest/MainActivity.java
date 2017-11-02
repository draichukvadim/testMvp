package com.example.mvp.mvptest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements MainView {
	private TextView tvName;
	private MainPresenter mainPresenter;

	@Override
	public void setPresenter(MainPresenter presenter) {
		if (this.mainPresenter != null) {
			this.mainPresenter.onPresenterDeattached();
		}
		this.mainPresenter = presenter;
		this.mainPresenter.onPresenterAttached(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		this.mainPresenter.onPresenterAttached(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		this.mainPresenter.onPresenterDeattached();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.mainPresenter = new MainPresenterImpl(new MainModelImpl());
		this.tvName = findViewById(R.id.tvName);
	}

	@Override
	public void renderState(MainModelState mainModelState) {
		this.tvName.setText(mainModelState.getName());
	}

	@Override
	public String getStatesName() {
		return this.tvName.getText().toString();
	}
}
