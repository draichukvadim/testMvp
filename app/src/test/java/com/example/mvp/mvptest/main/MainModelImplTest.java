package com.example.mvp.mvptest.main;

import com.example.mvp.mvptest.main.MainModel;
import com.example.mvp.mvptest.main.MainModelImpl;
import com.example.mvp.mvptest.main.MainModelState;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;

/**
 * Created by vadim on 02.11.2017.
 */
public class MainModelImplTest {
	private final String STUB_NAME = "Oslik";
	MainModel mainModel;

	@Before
	public void setUp() {
		mainModel = new MainModelImpl();
	}

	@Test
	public void testGetMainModelState() {
		TestSubscriber<MainModelState> subscriber = TestSubscriber.create();
		mainModel.getState().subscribe(subscriber);
		subscriber.assertNoErrors();
		subscriber.assertCompleted();
		Assert.assertEquals(subscriber.getOnNextEvents().get(0).getName(), STUB_NAME);
	}
}