package com.example.mvp.mvptest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;

/**
 * Created by vadim on 14.11.2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, shadows = {SuperCalcShadow.class})
public class SuperCalcTest {

	@Test
	public void testShadow() {
		assertEquals(5, SuperCalc.doMegaMath());
	}
}