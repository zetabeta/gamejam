package com.gamejam.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import com.gamejam.core.FieldBattle;

public class FieldBattleHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assets().setPathPrefix("project/");
    PlayN.run(new FieldBattle());
  }
}
