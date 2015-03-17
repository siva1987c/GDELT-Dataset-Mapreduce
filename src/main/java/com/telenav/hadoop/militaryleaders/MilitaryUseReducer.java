package com.telenav.hadoop.militaryleaders;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class MilitaryUseReducer extends MapReduceBase
    implements Reducer<Text, IntWritable, Text, IntWritable> {

  public void reduce(Text key, Iterator<IntWritable> values,
      OutputCollector<Text,IntWritable> output, Reporter reporter) throws IOException {

    int forceUse = 0;
    while (values.hasNext()) {
      IntWritable value = (IntWritable) values.next();
      forceUse += value.get(); // process value
    }

    output.collect(key, new IntWritable(forceUse));
  }
}