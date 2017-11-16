package com.example.mvp.mvptest;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/**
 * Created by vadim on 14.11.2017.
 */

@Implements(SuperCalc.class)
public class SuperCalcShadow {
	@Implementation
	public static int doMegaMath() {
		return 5;
	}
}
