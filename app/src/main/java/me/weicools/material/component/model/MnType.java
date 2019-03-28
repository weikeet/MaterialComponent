package me.weicools.material.component.model;

import androidx.core.view.PointerIconCompat;

public enum MnType {
  NOR(1001), HEAD(1002), SUB(PointerIconCompat.TYPE_HELP), DIV(PointerIconCompat.TYPE_WAIT);

  private final int value;

  private MnType (int i) {
    this.value = i;
  }

  public int getValue () {
    return this.value;
  }
}
