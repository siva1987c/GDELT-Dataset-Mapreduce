package com.telenav.hadoop.wordcount;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class WordCountMapper extends MapReduceBase
    implements Mapper<LongWritable, Text, Text, IntWritable> {

  private final IntWritable one = new IntWritable(1);
  private Text word = new Text();

  public void map(LongWritable key, Text value,
      OutputCollector<Text,IntWritable> output, Reporter reporter) throws IOException {

    String line = value.toString();
    String[] tokens=line.split("\\t");
    for(String token:tokens) {
      word.set(token);
      output.collect(word, one);
    }
  }
}