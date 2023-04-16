package com.ys.sub;

/**
 * @author yang
 * @createTime 2023年04月16日 12:23:00
 */
public class TimeLine {
  private String startTimeCode;
  private String endTimeCode;
  private  final  String separator = " --> ";

  private String fullTimeCode;

  public TimeLine(String startTimeCode, String endTimeCode){
    this.startTimeCode = startTimeCode;
    this.endTimeCode = endTimeCode;
    this.fullTimeCode = startTimeCode+ separator + endTimeCode;
  }

  public TimeLine(String fullTimeCode){
    String[] split = fullTimeCode.split(separator);
    this.startTimeCode = split[0];
    this.endTimeCode = split[1];
    this.fullTimeCode = fullTimeCode;
  }

  public String getFullTimeCode() {
    return fullTimeCode;
  }

  public void setFullTimeCode(String fullTimeCode) {
    this.fullTimeCode = fullTimeCode;
  }

  public String getStartTimeCode() {
    return startTimeCode;
  }

  public void setStartTimeCode(String startTimeCode) {
    this.startTimeCode = startTimeCode;
  }

  public String getEndTimeCode() {
    return endTimeCode;
  }

  public void setEndTimeCode(String endTimeCode) {
    this.endTimeCode = endTimeCode;
  }
}
