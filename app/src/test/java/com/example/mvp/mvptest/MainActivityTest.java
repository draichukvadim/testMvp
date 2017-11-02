package com.example.mvp.mvptest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
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
	public void testRenderView() throws Exception {
		MainModelState state = new MainModelState(STUB_STATE_NAME);
		this.mainView.renderState(state);
		assertEquals(STUB_STATE_NAME, this.mainView.getStatesName());
	}
}