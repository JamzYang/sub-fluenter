package com.ys.sub;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * @author yang
 * @createTime 2023年04月16日 12:01:00
 */
public class Parser {
  private final List<Character> lineEndings = List.of(',','.','?');
  private String filePath;


  //转换成块： 每个块包含两行。第一行时间轴，第二行脚本
  public List<Block> parseBlocks(String filePath) throws IOException {
    this.filePath = filePath;
    List<Block> blocks = new ArrayList<>(500);
    List<String> lines = FileUtils.readLines(new File(filePath), Charset.defaultCharset());
    Integer curIndex = 1;
    for (int i = 0; i < lines.size(); i++) {
      if(!lines.get(i).equals(curIndex.toString())){
        continue;
      }
      String fullTimeCode = lines.get(++i);
      String text = lines.get(++i);
      Block block = new Block(new TimeLine(fullTimeCode), text);
      blocks.add(block);
      curIndex++;
    }
    return blocks;
  }

  //合并块：记录开始块的开始时间戳， 判断每个块的脚本行 是否以 , . ? 结束， 否则读取下一个块，直到读取到以目标 符号结束的块， 记录结束块
  //的结束时间戳 。 生成新块的 时间轴和 脚本
  public List<Block> mergeBlocks(List<Block> sourceBlocks){
    List<Block> targetBlocks = new ArrayList<>(sourceBlocks.size());
    String startTimeCode = null;
    String endTimeCode = null;
    String text = null;
    for (Block sourceBlock : sourceBlocks) {
      if(lineEndings.contains(sourceBlock.getText().charAt(sourceBlock.getText().length()-1))){
        endTimeCode = sourceBlock.getTimeLine().getEndTimeCode();
        if(startTimeCode == null){
          startTimeCode = sourceBlock.getTimeLine().getStartTimeCode();
          text = sourceBlock.getText();
        }else {
          text = text + " "+sourceBlock.getText();
        }
        Block targetBlock = new Block(new TimeLine(startTimeCode,endTimeCode), text);
        targetBlocks.add(targetBlock);
        startTimeCode = null;
        endTimeCode = null;
        text = null;
      }else {
        if(startTimeCode == null){
          startTimeCode = sourceBlock.getTimeLine().getStartTimeCode();
          text = sourceBlock.getText();
        }else {
          text = text + " "+sourceBlock.getText();
        }
      }
    }
    return targetBlocks;
  }

  public void write2File(List<Block> blocks, String targetFilePath) throws IOException {
    StringBuilder sb = new StringBuilder();
    Integer curIndex = 1;
    for (Block block : blocks) {
      sb.append(curIndex).append("\r\n")
          .append(block.getTimeLine().getFullTimeCode()).append("\r\n")
          .append(block.getText()).append("\r\n\r\n");
      curIndex ++;
    }
    FileUtils.write(new File(targetFilePath),sb.toString());
  }
}
