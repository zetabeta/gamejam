package com.gamejam.core;

import java.util.HashMap;
import java.util.Map;

public class Content {
	
	private Map<Name,String> imgs;
	
	public Content(){
		this.imgs = new HashMap<Content.Name, String>();
		this.imgs.put(Name.MONSTER, "images/monster.png");
		this.imgs.put(Name.TRAP, "images/trap.png");
		this.imgs.put(Name.EMPTY, "images/empty.png");
		this.imgs.put(Name.BATTLE, "images/battle.png");
		this.imgs.put(Name.TREASURE, "images/treasure.png");
	}

	public String getImage(Name name){
		return this.imgs.get(name);
	}
	public enum Name {
		MONSTER,
		TRAP,
		EMPTY,
		BATTLE,
		TREASURE;
	}

}
