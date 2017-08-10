package com.ccsp.claims.model;

import java.io.Serializable;

public final class CustomMessage implements Serializable {

	private static final long serialVersionUID = 6651251297021529765L;
	private String text;
	private int priority;
	private boolean secret;

	public CustomMessage() {
	}

	public CustomMessage(String text, int priority, boolean secret) {
		this.text = text;
		this.priority = priority;
		this.secret = secret;
	}

	public String getText() {
		return text;
	}

	public int getPriority() {
		return priority;
	}

	public boolean isSecret() {
		return secret;
	}

	@Override
	public String toString() {
		return "CustomMessage{" + "text='" + text + '\'' + ", priority=" + priority + ", secret=" + secret + '}';
	}
}