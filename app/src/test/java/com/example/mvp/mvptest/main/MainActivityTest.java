package com.example.mvp.mvptest.main;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.mvp.mvptest.BuildConfig;
import com.example.mvp.mvptest.R;
import com.example.mvp.mvptest.settigns.SettingsActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

/**
 * Created by vadim on 02.11.2017.
 */
@RunWith(RobolectricTestRunner.class)

@Config(constants = BuildConfig.class)
public class MainActivityTest {
	private final String STUB_STATE_NAME = "Oslik";
	private MainActivity mainView;

	@Mock
	MainPresenter mainPresenter;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mainView = Robolectric.buildActivity(MainActivity.class).create().start().resume().visible().get();
		this.mainView.setPresenter(this.mainPresenter);
	}

	@Test
	public void testStopView() throws Exception {
		reset(this.mainPresenter);
		this.mainView.onStop();
		verify(this.mainPresenter).onPresenterDeattached();
	}

	@Test
	public void testStartView() throws Exception {
		reset(this.mainPresenter);
		this.mainView.onStart();
		verify(this.mainPresenter).onPresenterAttached(this.mainView);
	}

	@Test
	public void testNavigationButtons() {
		Button btVisibleButton = this.mainView.findViewById(R.id.btVisibleButton);
		Button btGoneButton = this.mainView.findViewById(R.id.btGoneButton);
		Button btInvisibleButton = this.mainView.findViewById(R.id.btInvisibleButton);

		assertNotNull(btVisibleButton);
		assertEquals(View.VISIBLE, btVisibleButton.getVisibility());

		assertNotNull(btGoneButton);
		assertEquals(View.GONE, btGoneButton.getVisibility());

		assertNotNull(btInvisibleButton);
		assertEquals(View.INVISIBLE, btInvisibleButton.getVisibility());
	}

	@Test
	public void buttonClickShouldStartSettingsActivity() throws Exception {
		Button button = this.mainView.findViewById(R.id.btSettings);
		button.performClick();
		Intent intent = Shadows.shadowOf(this.mainView).peekNextStartedActivity();
		assertEquals(SettingsActivity.class.getCanonicalName(), intent.getComponent().getClassName());
	}

	@Test
	public void methodShouldStartSettingsActivity() throws Exception {
		this.mainView.openSettingsActivity();
		Intent intent = Shadows.shadowOf(this.mainView).peekNextStartedActivity();
		assertEquals(SettingsActivity.class.getCanonicalName(), intent.getComponent().getClassName());
	}

	@Test
	public void testMessageButtonClick() throws Exception {
		Button view = this.mainView.findViewById(R.id.btMessage);
		assertNotNull(view);
		view.performClick();
		assertThat(ShadowToast.getTextOfLatestToast(), equalTo("zebra"));
	}

	@Test
	public void testRenderView() throws Exception {
		MainModelState state = new MainModelState(STUB_STATE_NAME);
		this.mainView.renderState(state);
		assertEquals(STUB_STATE_NAME, this.mainView.getStatesName());
	}
}