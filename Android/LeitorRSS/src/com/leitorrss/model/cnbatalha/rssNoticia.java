package com.leitorrss.model.cnbatalha;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("rss")
public class rssNoticia {

	@XStreamAlias("version")
	@XStreamAsAttribute
	public String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public channel getchannel() {
		return channel;
	}

	public void setchannels(channel channel) {
		this.channel = channel;
	}
	
	@XStreamAlias("channel")
	public channel channel;
}
