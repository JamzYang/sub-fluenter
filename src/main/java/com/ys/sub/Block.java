package com.ys.sub;

/**
 * @author yang
 * @createTime 2023年04月16日 12:22:00
 */
public class Block {
  private Integer index;
  private TimeLine timeLine;
  private String text;


  public Block(Integer index, TimeLine timeLine, String text) {
    this.index = index;
    this.timeLine = timeLine;
    this.text = text;
  }


  public Block(TimeLine timeLine, String text) {
    this.timeLine = timeLine;
    this.text = text;
  }


  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public TimeLine getTimeLine() {
    return timeLine;
  }

  public void setTimeLine(TimeLine timeLine) {
    this.timeLine = timeLine;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
