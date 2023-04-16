package com.ys.sub;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

class ParserTest {

  @Test
  void parse() throws IOException {

  }

  @Test
  void should_parsed_8_blocks(){
    Parser parser = new Parser();
    String path = this.getClass().getClassLoader().getResource("test1.srt").getPath();
    try {
      List<Block> blocks = parser.parseBlocks(path);
     assertEquals(8,blocks.size());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void should_merge_blocks(){
    Parser parser = new Parser();
    String path = this.getClass().getClassLoader().getResource("test1.srt").getPath();
    try {
      List<Block> blocks = parser.parseBlocks(path);
      List<Block> merged = parser.mergeBlocks(blocks);
      assertEquals(8,blocks.size());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void should_parse() throws IOException {

    Parser parser = new Parser();
    String path = this.getClass().getClassLoader().getResource("test1.srt").getPath();
    String expectPath = this.getClass().getClassLoader().getResource("test1_expect_merged.srt").getPath();
    String targetPath = this.getClass().getClassLoader().getResource("test1_merged.srt").getPath();
    List<Block> blocks = parser.parseBlocks(path);
    List<Block> merged = parser.mergeBlocks(blocks);
     parser.write2File(merged, targetPath);

    List<String> expectLines = FileUtils.readLines(new File(expectPath));
    List<String> targetLines = FileUtils.readLines(new File(targetPath));
    for (int i = 0; i < expectLines.size(); i++) {
      assertEquals(expectLines.get(i),targetLines.get(i),"第 "+ (i+1) +" 行，不同");
    }
  }
}