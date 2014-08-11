package com.song.xk_one.utils;

public class PinyinUtilsTwo {

	public PinyinUtilsTwo() {
		super();
	}

	public static String getPinyinStr(String str) {
		String[] strings = PinyinUtil.stringToPinyin(str);
		String string = PinyinUtil.stringArrayToString(strings);
		return string;
	}
}
