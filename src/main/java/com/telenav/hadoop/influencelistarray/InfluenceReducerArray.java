package com.telenav.hadoop.influencelistarray;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import com.telenav.hadoop.utils.MRArrayWritable;


public class InfluenceReducerArray extends MapReduceBase implements
        Reducer<Text, Text, Text, ArrayWritable> {

    private Set<String> countrySet=new HashSet<String>();
    
    public void reduce(Text key, Iterator<Text> values, OutputCollector<Text,ArrayWritable> output,
            Reporter reporter) throws IOException {
        
        if(values.hasNext()){
            String country=values.next().toString();
            countrySet.add(country);
        }
        
        String[] countries=(String[]) countrySet.toArray();
  
      
        output.collect(key,new MRArrayWritable(countries));
     

    }

}
