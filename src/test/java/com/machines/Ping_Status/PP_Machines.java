package com.machines.Ping_Status;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class PP_Machines {
	
	 @Test(priority=1)
	    public void pp1() throws Exception {
	        connect("pp1.humanbrain.in");
	        
	    }

	    @Test(priority=2)
	    public void pp2() throws Exception {
	        connect("pp2.humanbrain.in");
	        
	    }

	    @Test(priority=3)
	    public void pp3v5() throws Exception {
	       connect("pp3v5.humanbrain.in");
	       
	    }
	    
	    @Test(priority=4)
	    public void pp4v5() throws Exception {
	    	connect("pp4v5.humanbrain.in");
	    	
	    }
	    
	    @Test(priority=5)
	    public void pp4v15() throws Exception {
	    	connect("pp4v15.humanbrain.in");
	    	
	    }
	    
	    @Test(priority=6)
	    public void pp5v5() throws Exception {
	    	connect("pp5v5.humanbrain.in");
	    	
	    }
	    
	    @Test(priority=7)
	    public void pp5v15() throws Exception {
	    	connect("pp5v15.humanbrain.in");
	    	
	    }
	    @Test(priority=8)
	    public void pp7v5() throws Exception {
	    	connect("pp7v5.humanbrain.in");
	    	
	    }
	    
	    @Test(priority=9)
	    public void pp7v15() throws Exception {
	    	connect("pp7v15.humanbrain.in");
	    	
	    }

  public static void connect(String... values) throws Exception {
		    
		    Session session = null;
		    ChannelExec channel = null;

		    try {
		        session = new JSch().getSession("hbp", "apollo2.humanbrain.in", 22);
		        session.setPassword("Health#123");
		        session.setConfig("StrictHostKeyChecking", "no");
		        session.connect();
		        
		        for(String s:values){
		        if (channel != null) {
		            channel.disconnect();
		        } 
		        channel = (ChannelExec) session.openChannel("exec"); 
		        channel.setCommand("ping -c 7 "+s);
		        ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
		        channel.setOutputStream(responseStream);
		        channel.connect();
		        
		        while (channel.isConnected()) {
		            Thread.sleep(100);
		        }
		        
		        String responseString = new String(responseStream.toByteArray());
		        // System.out.println(responseString);
		        String[] lines = responseString.split("\\r?\\n|\\r");
		        try{
		        String line = lines[lines.length-2];
		        String[] str = line.split(", ", 3);
		        System.out.println(lines[lines.length-3]);
		        System.out.println(line);
		        // System.out.println(str[1].charAt(0));
		        int r = Integer.parseInt(String.valueOf(str[1].charAt(0)));
		        Assert.assertNotEquals(r, 0);
		        // System.out.println(r);
		    }
		        catch(Exception e){
		            System.err.println("Unable to ping : "+s);
		        }

		    }
		    } finally {
		        if (session != null) {
		            session.disconnect();
		        }
		    }
		}
		}
