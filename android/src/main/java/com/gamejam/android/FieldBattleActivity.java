package com.gamejam.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import com.gamejam.core.FieldBattle;

public class FieldBattleActivity extends GameActivity {

  @Override
  public void main(){
    platform().assets().setPathPrefix("com/gamejam/resources");
    PlayN.run(new FieldBattle());
  }
}
