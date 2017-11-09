package com.example.mvp.mvptest.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp.mvptest.R;
import com.example.mvp.mvptest.settigns.SettingsActivity;

public class MainActivity extends Activity implements MainView {
	private TextView tvName;
	private Button btSettings;
	private Button btMessage;
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

		findViews();
		initListeners();
	}

	private void findViews() {
		this.tvName = findViewById(R.id.tvName);
		this.btSettings = findViewById(R.id.btSettings);
		this.btMessage = findViewById(R.id.btMessage);
	}

	private void initListeners() {
		this.btSettings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startSettingsActivity();
			}
		});
		this.btMessage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "zebra", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void renderState(MainModelState mainModelState) {
		this.tvName.setText(mainModelState.getName());
	}

	@Override
	public void openSettingsActivity() {
		startSettingsActivity();
	}

	private void startSettingsActivity() {
		startActivity(new Intent(MainActivity.this, SettingsActivity.class));
	}

	@Override
	public String getStatesName() {
		return this.tvName.getText().toString();
	}
}
