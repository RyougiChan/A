package com.shetuan.Abstract_class;

import android.os.Handler;
import android.os.HandlerThread;

public class HandlerFactory extends HandlerThread {

	public HandlerFactory(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public HandlerFactory(String name, int priority) {
		super(name, priority);
		// TODO Auto-generated constructor stub
	}

	public static Handler newHandlerInOtherThread(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
