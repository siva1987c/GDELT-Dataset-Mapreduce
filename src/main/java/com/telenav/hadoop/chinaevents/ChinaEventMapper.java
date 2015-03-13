package com.telenav.hadoop.chinaevents;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class ChinaEventMapper extends MapReduceBase implements
        Mapper<LongWritable, Text, Text, IntWritable> {

    private final IntWritable one = new IntWritable(1);
    private Text dateOut = new Text();
    private Calendar cal=new GregorianCalendar();


    public void map(LongWritable key, Text value,
            OutputCollector<Text, IntWritable> output, Reporter reporter)
            throws IOException {

        String line = value.toString();
        int startIndex = line.indexOf(",") + 1;
        int endIndex = line.indexOf(",", startIndex + 1);
        String date = value.toString().substring(startIndex, endIndex);
        DateFormat df = new SimpleDateFormat("yyyymmdd", Locale.ENGLISH);
        Date fDate;
        try {
            fDate = df.parse(date);
            cal.setTime(fDate);
            String calDate = cal.get(Calendar.YEAR)+",week "+cal.get(Calendar.WEEK_OF_YEAR);
            dateOut.set(calDate);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        output.collect(dateOut, one);
    }
}
