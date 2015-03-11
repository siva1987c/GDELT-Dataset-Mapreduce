package com.telenav.hadoop.influencelistarray;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;


public class InfluenceCountArray {

    public static void main(String[] args) {
        JobClient client = new JobClient();
        JobConf conf = new JobConf(InfluenceCountArray.class);
        
  
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);

        // specify input and output dirs
        FileInputFormat.addInputPath(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        // specify a mapper
        conf.setMapperClass(InfluenceMapperArray.class);

        // specify a reducer
        conf.setReducerClass(InfluenceReducerArray.class);
        //conf.setCombinerClass(InfluenceReducer.class);

        client.setConf(conf);
        try {
            JobClient.runJob(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
