package com.st.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期范围
 * 
 */
public class DateRange {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private final Date from;// 起始日期
	private final Date to;// 结束日期

	public DateRange(Date from, Date to) {
		super();
		this.from = from;
		this.to = to;
	}

	/**
	 * 静态方法
	 * 
	 * @param source
	 * @return
	 */
	public static DateRange of(String source, String split) {
		if (source == null || source.trim().length() == 0) {
			return null;
		}

		String[] tokens = source.split(split);

		Date from = null;
		Date to = null;

		if (tokens.length > 0) {
			try {
				from = sdf.parse(tokens[0].trim());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (tokens.length > 1) {
			try {
				to = sdf.parse(tokens[1].trim());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return new DateRange(from, to);

	}

	public Date getFrom() {
		return from;
	}

	public Date getTo() {
		return to;
	}

	/**
	 * 将日期范围转换成字符串
	 * 
	 * @param split
	 * @return
	 */
	public String toString(String split) {
		return sdf.format(from) + split + sdf.format(to);
	}

	@Override
	public String toString() {
		return toString(" - ");
	}

}
