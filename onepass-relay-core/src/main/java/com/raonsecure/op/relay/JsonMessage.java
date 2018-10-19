package com.raonsecure.op.relay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class JsonMessage {
	public String toJsonString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}
	
	public String toString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}

}
