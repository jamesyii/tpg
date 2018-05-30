package com.my.q.three.sample;

import java.util.Random;

public class SampleClass2 {

	
	public void fixedTimeMethod2() {
		sleep(1000);
	}
	
	public void variableTimeMethod2(int milliseconds) {
		sleep(milliseconds);
	}
	
	public void randomTimeMethod2() {
		Random r = new Random();
		int milliseconds = r.nextInt(4000) + 1000;
		sleep(milliseconds);
	}
	
	private void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
