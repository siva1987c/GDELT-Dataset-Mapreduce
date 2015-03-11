package com.telenav.hadoop.influencelist;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import com.telenav.hadoop.utils.MRArrayWritable;


public class InfluenceCount {

    public static void main(String[] args) {
        JobClient client = new JobClient();
        JobConf conf = new JobConf(InfluenceCount.class);
        
        /**
         * Just some text to test git commits
         */
        
  
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(MRArrayWritable.class);
        
        conf.setMapOutputKeyClass(Text.class);
        conf.setMapOutputValueClass(Text.class);

        // specify input and output dirs
        FileInputFormat.addInputPath(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        // specify a mapper
        conf.setMapperClass(InfluenceMapper.class);

        // specify a reducer
        conf.setReducerClass(InfluenceReducer.class);
        //conf.setCombinerClass(InfluenceReducer.class);

        client.setConf(conf);
        try {
            JobClient.runJob(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
