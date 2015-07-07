package org.zywx.wbpalmstar.plugin.uexscreenlocklistener;

import org.zywx.wbpalmstar.engine.EBrowserView;
import org.zywx.wbpalmstar.engine.universalex.EUExBase;
import org.zywx.wbpalmstar.engine.universalex.EUExCallback;
import org.zywx.wbpalmstar.plugin.uexscreenlocklistener.ScreenObserver.ScreenStateListener;

import android.content.Context;
import android.util.Log;

public class EUExScreenLockListener extends EUExBase {

	private final static String tag = "uexScreenLockListener";

	public static final String onStatusChange = "uexScreenLockListener.onStatusChange";
	public static final String onScreenOn = "0";
	public static final String onScreenOff = "1";
	public static final String onUserPresent = "2";
	private static ScreenObserver observer;

	public EUExScreenLockListener(Context context, EBrowserView inParent) {
		super(context, inParent);
	}

	public void open(String[] parm) {
		Log.i(tag, "open");
		try {
			if (observer != null) {
				observer.stopScreenStateUpdate();
			}
			observer = new ScreenObserver(mContext);
			observer.requestScreenStateUpdate(new ScreenStateListener() {

				@Override
				public void onUserPresent() {
					Log.i(tag, "onUserPresent");
					jsCallback(onStatusChange, 0, EUExCallback.F_C_TEXT,
							onUserPresent);
				}

				@Override
				public void onScreenOn() {
					Log.i(tag, "onScreenOn");
					jsCallback(onStatusChange, 0, EUExCallback.F_C_TEXT,
							onScreenOn);
				}

				@Override
				public void onScreenOff() {
					Log.i(tag, "onScreenOff");
					jsCallback(onStatusChange, 0, EUExCallback.F_C_TEXT,
							onScreenOff);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close(String[] param) {
		try {
			if (observer != null) {
				observer.stopScreenStateUpdate();
				observer = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void onApplicationCreate(Context context) {

	}

	public static void onActivityCreate(Context context) {

	}

	public static void onActivityStart(Context context) {

	}

	public static void onActivityReStart(Context context) {

	}

	public static void onActivityResume(Context context) {

	}

	public static void onActivityPause(Context context) {

	}

	public static void onActivityStop(Context context) {

	}

	public static void onActivityDestroy(Context context) {
		try {
			if (observer != null) {
				observer.stopScreenStateUpdate();
				observer = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean clean() {
		return true;
	}

}
