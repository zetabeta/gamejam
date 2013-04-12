package com.gamejam.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import com.gamejam.core.FieldBattle;

public class FieldBattleJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assets().setPathPrefix("com/gamejam/resources");
    PlayN.run(new FieldBattle());
  }
}
