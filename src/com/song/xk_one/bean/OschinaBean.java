package com.song.xk_one.bean;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class OschinaBean {
	public String tweetCount;
	public String pagesize;
	public ArrayList<TweetBean> tweets = new ArrayList<TweetBean>();

	public void XMLParseResponse(XmlPullParser pullParser) {
		int evtType;
		TweetBean tweetBean = null;
		try {
			evtType = pullParser.getEventType();
			while (evtType != XmlPullParser.END_DOCUMENT) {
				String tag = pullParser.getName();
				switch (evtType) {

				case XmlPullParser.START_TAG:
					if ("tweetCount".equals(tag)) {
						tweetCount = pullParser.nextText();
					} else if ("pagesize".equals(tag)) {
						pagesize = pullParser.nextText();
					} else if ("tweets".equals(tag)) {
						tweets = new ArrayList<TweetBean>();
					} else if ("tweet".equals(tag)) {
						tweetBean = new TweetBean();
					} else if (tweetBean != null) {
						if ("id".equals(tag)) {
							tweetBean.id = pullParser.nextText();
						} else if ("portrait".equals(tag)) {
							tweetBean.portrait = pullParser.nextText();
						} else if ("author".equals(tag)) {
							tweetBean.author = pullParser.nextText();
						} else if ("authorid".equals(tag)) {
							tweetBean.author = pullParser.nextText();
						} else if ("body".equals(tag)) {
							tweetBean.body = pullParser.nextText();
							;
						} else if ("appclient".equals(tag)) {
							tweetBean.appclient = pullParser.nextText();
						} else if ("commentCount".equals(tag)) {
							tweetBean.commentCount = pullParser.nextText();
						} else if ("pubDate".equals(tag)) {
							tweetBean.pubDate = pullParser.nextText();
						}
					}
					break;
				case XmlPullParser.END_TAG:
					if (pullParser.getName().equals("tweet")) {
						tweets.add(tweetBean);
					}
					break;
				}
				evtType = pullParser.next();
			}
		} catch (XmlPullParserException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return "OschinaBean [tweetCount=" + tweetCount + ", pagesize="
				+ pagesize + ", tweets=" + tweets + "]";
	}

}
