package com.telenav.hadoop.countryevents;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

/**
 * Task: count how many events happened in each country
 * 
 * @author TChira
 * @version $Revision$
 */
public class EventCount {
    
    public static void main(String[] args){
        JobClient client=new JobClient();
        JobConf conf=new JobConf(EventCount.class);
     // specify output types
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        // specify input and output dirs
        FileInputFormat.addInputPath(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        // specify a mapper
        conf.setMapperClass(EventCountMapper.class);

        // specify a reducer
        conf.setReducerClass(EventCountReducer.class);
        conf.setCombinerClass(EventCountReducer.class);

        client.setConf(conf);
        try {
          JobClient.runJob(conf);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}
