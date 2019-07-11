package com.cqcej.web;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DESCRIPTION
 *
 * @author Li HuanLing
 * @date 2018-04-03 17:00
 * @email 503580622@qq.com
 */
public class UnitTest {
	private static final String formatStr = "HH:mm";
	
	private static SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
	
	@Test
	public void contextLoads() throws ParseException {
		String str = "28:ED:34:7D:C7:67:9F:27:FC:0D:C4:68:AF:A7:7C:3C";
		System.out.println(str.replace(":", "").toLowerCase());
	}
	
	@Test
	public void waitTimeTest(){
		
		int sumWaitTime = 0;
		for (int i = 0; i < 200; i++){
			sumWaitTime += (int)(Math.random() *11);
		}
		BigDecimal d = new BigDecimal(sumWaitTime/200f);
		System.err.println("总时长:"+sumWaitTime+"\t平均等待时间为:"+d.setScale(1,   RoundingMode.HALF_UP));
		
	}
	
	@Test
	public void test(){
	
	
	}
	
	
	public void testString(String... strings){
		System.err.println(strings.getClass().getTypeName());
	}
	
	@Test
	public void test1(){
		testString("a,","b");
	}
	
	public static void main(String[] args) {
		//双色球
		List<Integer> ballNums = new ArrayList<>();
		//红球
		for (int i = 0; i < 6; i++) {
			ballNums.add((int) (Math.random() * 33 + 1));
			Collections.sort(ballNums);
			for (int j = 1; j < ballNums.size(); j++) {
				if (ballNums.get(j) == ballNums.get(j - 1)) {//判断重复
					i--;
					ballNums.remove(j);
					break;
				}
			}
		}
		//蓝球
		ballNums.add((int) (Math.random() * 16 + 1));
		String s = ballNums.toString();
		System.err.println(s);
		//打印
		for (int i = 0; i < ballNums.size(); i++) {
			System.err.print(ballNums.get(i) + "\t");
		}
	}
}
