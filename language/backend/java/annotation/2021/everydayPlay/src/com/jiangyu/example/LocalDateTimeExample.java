package com.jiangyu.example;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**  
 * @ClassName: Example_day16
 * @Description: TODO(jdk8新时间类操作)
 * @author Jy
 * @date 2021-01-12 10:59:15 
*/  
public class LocalDateTimeExample {
	public static void main(String[] args) {
//		demo01();
//		demo02();
//		demo03();
//		demo04();
//	    demo05();
//		demo06();
//		demo07();
// 	 	demo08();
//		demo09();
//		demo10();
//		demo11();
//		demo12();
//		demo13();
//		demo14();
//		LocalDateTime date2 = LocalDateTime.now();
//		System.out.println(date2.getHour());
//		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//		LocalDateTime date2 = LocalDateTime.now();
//		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		String date2Str = formatter2.format(date2);
//		System.out.println(date2Str);
//		String datetime =  "2018-01-13 21:27:30";  
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate now = LocalDate.now();
//		LocalDate plusDays = now.plusDays(1L);
//
//		System.out.println(LocalDate.now().isBefore(plusDays));
		//System.out.println(dtf);
//		LocalDateTime today_start = LocalDateTime.of(LocalDate.now(),LocalTime.MIN);
//
//	        LocalDateTime today_end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
//		System.out.println(today_end);
//		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		


		//计算两个时间的间隔
		System.out.println("-------------------------------");
		LocalDateTime date3 = LocalDateTime.now();
		LocalDateTime date4 = LocalDateTime.of(2021, 1, 15, 22, 30, 10);
		Duration duration = Duration.between(date4,date3);
		System.out.println(date3 + " 与 " + date4 + " 间隔  " + "\n"
		        + " 天 :" + duration.toDays() + "\n"
		        + " 时 :" + duration.toHours() + "\n"
		        + " 分 :" + duration.toMinutes() + "\n"
		        + " 毫秒 :" + duration.toMillis() + "\n"
		        + " 纳秒 :" + duration.toNanos() + "\n"
		        );
	}
	//long毫秒值转换为日期
	private static void demo14() {
		System.out.println("---------long毫秒值转换为日期---------");
		DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String longToDateTime = df.format(LocalDateTime.ofInstant(
		Instant.ofEpochMilli(System.currentTimeMillis()),ZoneId.of("Asia/Shanghai")));
		System.out.println(longToDateTime);
	}

	private static void demo13() {
		//新的格式化API中，格式化后的结果都默认是String，有时我们也需要返回经过格式化的同类型对象
		LocalDateTime ldt1 = LocalDateTime.now();
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String temp = dtf1.format(ldt1);
		LocalDateTime formatedDateTime = LocalDateTime.parse(temp, dtf1);
		System.out.println(formatedDateTime);
	}
	
	//将时间字符串形式转化为日期对象
	private static void demo12() {
		String datetime =  "2018-01-13 21:27:30";  
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime ldt = LocalDateTime.parse(datetime, dtf);  
		System.out.println(ldt);
	}
	private static void demo11() {
		//使用jdk自身配置好的日期格式
		DateTimeFormatter formatter1 = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime date1 = LocalDateTime.now();
		System.out.println(date1);
		//反过来调用也可以 : date1.format(formatter1);
		String date1Str = formatter1.format(date1);
		System.out.println(date1Str);
		//使用自定义格式
		LocalDateTime date2 = LocalDateTime.now();
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String date2Str = formatter2.format(date2);
		System.out.println(date2Str);
//		LocalDate只能设置仅含年月日的格式
//		LocalTime只能设置仅含时分秒的格式
//		LocalDateTime可以设置含年月日时分秒的格式
//		代码如下：
		DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		formatter3.format(LocalDate.now());
		System.out.println("-------------------------------");
		DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("HH:mm:ss");
		System.out.println(formatter4.format(LocalTime.now()));
	}
	//当计算程序的运行时间时，应当使用时间戳Instant
	private static void demo10() {
		Instant ins1 = Instant.now();
		for (int i = 0; i < 10000000; i++) {
		            //循环一百万次
		}
		Instant ins2 = Instant.now();
		Duration duration = Duration.between(ins1, ins2);
		System.out.println("程序运行耗时为 ： " + duration.toMillis() + "毫秒");
	}
	//	计算时间、日期间隔
	//	Duration:用于计算两个“时间”间隔
	//	Period:用于计算两个“日期”间隔
	private static void demo09() {
		//计算两个日期的日期间隔-年月日
		LocalDate date1 = LocalDate.of(2018, 2, 13);
		LocalDate date2 = LocalDate.of(2017, 3, 12);
		//内部是用date2-date1，所以得到的结果是负数
		Period period = Period.between(date1, date2);
		System.out.println("相差年数 ： " + period.getYears());
		System.out.println("相差月数 ： " + period.getMonths());
		System.out.println("相差日数 ： " + period.getDays());
		//还可以这样获取相差的年月日
		System.out.println("-------------------------------");
		long years = period.get(ChronoUnit.YEARS);
		long months = period.get(ChronoUnit.MONTHS);
		long days = period.get(ChronoUnit.DAYS);
		System.out.println("相差的年月日分别为 ： " + years + "," + months + "," + days);
		//注意，当获取两个日期的间隔时，并不是单纯的年月日对应的数字相加减，而是会先算出具体差多少天，在折算成相差几年几月几日的
		        
		//计算两个时间的间隔
		System.out.println("-------------------------------");
		LocalDateTime date3 = LocalDateTime.now();
		LocalDateTime date4 = LocalDateTime.of(2018, 1, 13, 22, 30, 10);
		Duration duration = Duration.between(date3, date4);
		System.out.println(date3 + " 与 " + date4 + " 间隔  " + "\n"
		        + " 天 :" + duration.toDays() + "\n"
		        + " 时 :" + duration.toHours() + "\n"
		        + " 分 :" + duration.toMinutes() + "\n"
		        + " 毫秒 :" + duration.toMillis() + "\n"
		        + " 纳秒 :" + duration.toNanos() + "\n"
		        );
		//注意，并没有获得秒差的，但既然可以获得毫秒，秒就可以自行获取了
	}
	private static void demo08() {
		//		事实上Instant就是java8以前的Date，
		//		可以使用以下两个类中的方法在这两个类型之间进行转换，
		//		比如Date.from(Instant)就是用来把Instant转换成java.util.date的，
		//		而new Date().toInstant()就是将Date转换成Instant的
		
				Instant instant = Instant.now();
				//2019-06-08T16:50:19.174Z
				System.out.println(instant);
				Date date = Date.from(instant);
				Instant instant2 = date.toInstant();
				//Sun Jun 09 00:50:19 CST 2019
				System.out.println(date);
				//2019-06-08T16:50:19.174Z
				System.out.println(instant2);
	}
	private static void demo07() {
		//返回当前时间，根据系统时间和UTC
		Clock clock = Clock.systemUTC();
		// 运行结果： SystemClock[Z]
		System.out.println(clock);
	}
	//判断是否为闰年
	private static void demo06() {
		LocalDate now = LocalDate.now();
		System.out.println("now : " + now + ", is leap year ? ");
	}

	private static void demo05() {
		//时间日期前后的比较与判断
		//判断两个时间点的前后
		LocalDate localDate1 = LocalDate.of(2017, 8, 8);
		LocalDate localDate2 = LocalDate.of(2018, 8, 8);
		boolean date1IsBeforeDate2 = localDate1.isBefore(localDate2);
		System.out.println("date1IsBeforeDate2 : " + date1IsBeforeDate2);
		// date1IsBeforeDate2 == true
	}

	private static void demo04() {
		//将年月日修改为指定值,并返回新的日期（时间）对象
		//析： 其效果与时间日期相加减差不多，如今天是2018-01-13，要想变为2018-01-20有两种方式
		//a. localDate.plusDays(20L) -> 相加指定的天数
		//b. localDate.withDayOfYear(20) -> 直接指定到哪一天
		LocalDate localDate = LocalDate.now();
		//当前时间基础上，指定本年当中的第几天，取值范围为1-365,366
		LocalDate withDayOfYearResult = localDate.withDayOfYear(200);
		//当前时间基础上，指定本月当中的第几天，取值范围为1-29,30,31
		LocalDate withDayOfMonthResult = localDate.withDayOfMonth(5);
		//当前时间基础上，直接指定年份
		LocalDate withYearResult = localDate.withYear(2017);
		//当前时间基础上，直接指定月份
		LocalDate withMonthResult = localDate.withMonth(5);
		System.out.println("当前时间是 : " + localDate + "\n"
		        + "指定本年当中的第200天 : " + withDayOfYearResult + "\n"
		        + "指定本月当中的第5天 : " + withDayOfMonthResult + "\n"
		        + "直接指定年份为2017 : " + withYearResult + "\n"
		        + "直接指定月份为5月 : " + withMonthResult + "\n"
		        );
	}

	private static void demo03() {
		LocalDateTime localDateTime = LocalDateTime.now();
		//以下方法都是long类型,返回值都是LocaldateTime
		LocalDateTime plusYears = localDateTime.plusYears(2L);
		LocalDateTime plusMonths = localDateTime.plusMonths(3l);
		LocalDateTime plusDays = localDateTime.plusDays(7L);
		LocalDateTime plusHours = localDateTime.plusHours(-2L);
		LocalDateTime plusMinutes = localDateTime.plusMinutes(10L);
		LocalDateTime plusSeconds = localDateTime.plusSeconds(10);
		System.out.println("当前时间是 : " + localDateTime + "\n"
		        + "当前时间加2年后为 : " + plusYears + "\n"
		        + "当前时间加3个月后为 : " + plusMonths + "\n"
		        + "当前时间加7日后为 : " + plusDays + "\n"
		        + "当前时间加2小时后为 : " + plusHours + "\n"
		        + "当前时间加10分钟后为 : " + plusMinutes + "\n"
		        + "当前时间加10秒后为 : " + plusSeconds + "\n"
		        );
		//也可以以另一种方式来相加减日期，即plus(long amountToAdd, TemporalUnit unit)
		//      参数1 ： 相加的数量， 参数2 ： 相加的单位
		LocalDateTime nextMonth = localDateTime.plus(1,ChronoUnit.MONTHS);
		LocalDateTime nextYear = localDateTime.plus(1,ChronoUnit.YEARS);
		LocalDateTime nextWeek = localDateTime.plus(1,ChronoUnit.WEEKS);
		System.out.println("now : " + localDateTime + "\n"
		        + "nextYear : " + nextYear + "\n"
		        + "nextMonth : " + nextMonth + "\n"
		        + "nextWeek :" + nextWeek + "\n"
		        );
		        
		//日期的减法用法一样，在此不再举例
	}
	
	private static void demo02() {
		LocalDate localDate = LocalDate.of(2018, 1, 13);
		LocalTime localTime = LocalTime.of(9, 43, 20);
		LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 13, 9, 43, 20);
		System.out.println(localDate);
		System.out.println(localTime);
		System.out.println(localDateTime);
	}

	private static void demo01() {
		LocalDate localDate = LocalDate.now();//获取当前日期
		LocalTime localTime = LocalTime.now();
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDate);
		System.out.println(localTime);
		System.out.println(localDateTime);
	}
}
