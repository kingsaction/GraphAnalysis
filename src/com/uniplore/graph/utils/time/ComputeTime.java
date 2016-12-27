package com.uniplore.graph.utils.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ComputeTime {

	public static long computeTime(Date time1,Date time2) throws Exception{
		
		//计算两个时间差，并将其换算成分钟数
		long time1_long = time1.getTime();
		long time2_long = time2.getTime();
		long diff = time2_long -time1_long;
		long minute = diff/(1000*60);
		return minute;
	}
}