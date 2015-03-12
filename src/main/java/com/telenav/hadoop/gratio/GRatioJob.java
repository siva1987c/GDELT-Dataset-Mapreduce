package com.telenav.hadoop.gratio;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class GRatioJob {
    
    public static void main(String[] args){
        JobClient client=new JobClient();
        JobConf conf=new JobConf(GRatioJob.class);
     // specify output types
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(FloatWritable.class);
        
        conf.setMapOutputKeyClass(Text.class);
        conf.setMapOutputValueClass(Text.class);

        // specify input and output dirs
        FileInputFormat.addInputPath(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        // specify a mapper
        conf.setMapperClass(GRatioMapper.class);

        // specify a reducer
        conf.setReducerClass(GRatioReducer.class);
        

        client.setConf(conf);
        try {
          JobClient.runJob(conf);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}