package com.practice.mobliesafe.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

public class SystemInfoUtils {

	public static boolean isServiceRunning(Context context, String className) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> infos = am.getRunningServices(200);
		for (RunningServiceInfo info : infos) {
			String serviceClassName = info.service.getClassName();
			if (className.equals(serviceClassName)) {
				return true;
			}
		}
		return false;
	}

	public static int getProcessCount(Context context) {
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> runningAppProcesses = activityManager
				.getRunningAppProcesses();

		return runningAppProcesses.size();
	}

	public static long getAvailMem(Context context) {
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(context.ACTIVITY_SERVICE);
		MemoryInfo memoryInfo = new MemoryInfo();

		activityManager.getMemoryInfo(memoryInfo);
		return memoryInfo.availMem;
	}

	public static long getTotalMem(Context context) {
		try {
			FileInputStream fis = new FileInputStream(new File("/proc/meminfo"));

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					fis));

			String readLine = reader.readLine();

			StringBuffer sb = new StringBuffer();

			for (char c : readLine.toCharArray()) {
				if (c >= '0' && c <= '9') {
					sb.append(c);
				}
			}
			return Long.parseLong(sb.toString()) * 1024;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

}
