package com.song.xk_one.bean;

public class TweetBean {
	public String id;
	public String portrait;
	public String author;
	public String authorid;
	public String body;
	public String appclient;
	public String commentCount;
	public String pubDate;
	public String imgSmall;
	public String imgBig;
	
	@Override
	public String toString() {
		return "TweetBean [id=" + id + ", portrait=" + portrait + ", author="
				+ author + ", authorid=" + authorid + ", body=" + body
				+ ", appclient=" + appclient + ", commentCount=" + commentCount
				+ ", pubDate=" + pubDate + ", imgSmall=" + imgSmall
				+ ", imgBig=" + imgBig + "]";
	}
}
