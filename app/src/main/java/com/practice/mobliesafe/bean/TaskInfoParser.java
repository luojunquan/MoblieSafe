package com.practice.mobliesafe.bean;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Debug.MemoryInfo;

import com.practice.mobliesafe.R;

import java.util.ArrayList;
import java.util.List;

public class TaskInfoParser {

	public static List<TaskInfo> getTaskInfos(Context context) {

		PackageManager packageManager = context.getPackageManager();

		List<TaskInfo> TaskInfos = new ArrayList<TaskInfo>();

		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();

		for (RunningAppProcessInfo runningAppProcessInfo : appProcesses) {

			TaskInfo taskInfo = new TaskInfo();

			String processName = runningAppProcessInfo.processName;

			taskInfo.setPackageName(processName);

			try {
				MemoryInfo[] memoryInfo = activityManager
						.getProcessMemoryInfo(new int[]{runningAppProcessInfo.pid});
				int totalPrivateDirty = memoryInfo[0].getTotalPrivateDirty() * 1024;
				
				

				taskInfo.setMemorySize(totalPrivateDirty);

				PackageInfo packageInfo = packageManager.getPackageInfo(
						processName, 0);

				Drawable icon = packageInfo.applicationInfo
						.loadIcon(packageManager);

				taskInfo.setIcon(icon);
				String appName = packageInfo.applicationInfo.loadLabel(
						packageManager).toString();

				taskInfo.setAppName(appName);
				
				System.out.println("-------------------");
				System.out.println("processName="+processName);
				System.out.println("appName="+appName);
				int flags = packageInfo.applicationInfo.flags;
				if((flags & ApplicationInfo.FLAG_SYSTEM) != 0 ){
					taskInfo.setUserApp(false);
				}else{
					taskInfo.setUserApp(true);
					
				}
				
				
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				taskInfo.setAppName(processName);
				taskInfo.setIcon(context.getResources().getDrawable(
						R.drawable.ic_launcher));
			}
			
			TaskInfos.add(taskInfo);
		}

		return TaskInfos;
	}

}
