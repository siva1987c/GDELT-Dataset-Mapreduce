package com.telenav.hadoop.monthprotests;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;


public class MonthProtestsReducer extends MapReduceBase implements
        Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable protests=new IntWritable();
    
    public void reduce(Text key, Iterator<IntWritable> values,
            OutputCollector<Text, IntWritable> output, Reporter reporter)
            throws IOException {

        int sum = 0;
        while (values.hasNext()) {
            sum += values.next().get();
        }
        protests.set(sum);
        output.collect(key, protests);
    }
}
