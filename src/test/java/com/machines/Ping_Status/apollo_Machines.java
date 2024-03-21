package com.machines.Ping_Status;

import java.io.ByteArrayOutputStream;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
public class apollo_Machines {
	
	  @Test(priority=1)
	    public void apollo1() throws Exception
	    {
	        connect("apollo1.humanbrain.in");
	    }
	  
	  @Test(priority=2)
	  public void apollo2() throws Exception
	  {
		  connect("apollo2.humanbrain.in");
	  }
	  
	  @Test(priority=3)
	  public void ap3() throws Exception
	  {
		  connect("ap3.humanbrain.in");
		  
	  }
	  
	  @Test(priority=4)
	  public void  ap6() throws Exception
	  {
		  connect("ap6.humanbrain.in");
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



