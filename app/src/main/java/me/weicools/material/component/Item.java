package me.weicools.material.component;

import me.weicools.material.component.model.MnType;

/**
 * @author Weicools Create on 2019.03.27
 *
 * desc:
 */
public class Item extends ListItem {
  public Class<?> Act = null;
  public int Icon = -1;
  public int Id = -1;
  public boolean New = false;
  public String Parent;
  public String Text;

  public Item (int i, String str, MnType mnType) {
    super(mnType.getValue());
    this.Id = i;
    this.Text = str;
    this.Text = null;
  }

  public Item (int i, String str, int i2, MnType mnType) {
    super(mnType.getValue());
    this.Id = i;
    this.Text = str;
    this.Icon = i2;
  }

  public Item (int i, String str, int i2, boolean z, MnType mnType) {
    super(mnType.getValue());
    this.Id = i;
    this.Text = str;
    this.Icon = i2;
    this.New = z;
  }

  public Item (int i, String str, String str2, MnType mnType, Class<?> cls) {
    super(mnType.getValue());
    this.Id = i;
    this.Text = str2;
    this.Act = cls;
    this.Parent = str;
  }

  public Item (int i, String str, String str2, boolean z, MnType mnType, Class<?> cls) {
    super(mnType.getValue());
    this.Id = i;
    this.Text = str2;
    this.New = z;
    this.Act = cls;
    this.Parent = str;
  }
}
