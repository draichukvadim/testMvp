package com.example.mvp.mvptest.main;

import com.example.mvp.mvptest.main.MainModel;
import com.example.mvp.mvptest.main.MainModelState;
import com.example.mvp.mvptest.main.MainPresenter;
import com.example.mvp.mvptest.main.MainPresenterImpl;
import com.example.mvp.mvptest.main.MainView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.Mockito.verify;


/**
 * Created by vadim on 02.11.2017.
 */
public class MainPresenterImplTest {
	@Mock
	MainModel model;
	@Mock
	MainView view;

	private final String STUB_MAIN_STATE = "Oslik";
	private MainPresenter mainPresenter;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		Mockito.when(model.getState()).thenReturn(Observable.just(new MainModelState(STUB_MAIN_STATE)));
		this.mainPresenter = new MainPresenterImpl(model);
		this.mainPresenter.onPresenterAttached(view);
	}

	@Test
	public void testGetState() {
		this.mainPresenter.onStartStateLoading();
		verify(this.view).renderState(Mockito.any(MainModelState.class));
	}
}