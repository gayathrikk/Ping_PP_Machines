package com.machines.Ping_Status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Dev_Machines {
	
	 @Test(priority=1)
	    public void dev2nathan() {
	        testPing("dev2nathan.humanbrain.in");
	        testPing("172.20.23.4");
	    }

	    @Test(priority=2)
	    public void dev2mani() {
	        testPing("dev2mani.humanbrain.in");
	        testPing("172.20.23.2");
	    }

	    @Test(priority=3)
	    public void dev2sam() {
	        testPing("dev2sam.humanbrain.in");
	        testPing("172.20.23.3");
	    }
	    
	    @Test(priority=4)
	    public void dev2meena() {
	    	testPing("dev2meena.humanbrain.in");
	    	testPing("172.20.23.6");
	    }
	    
	    @Test(priority=5)
	    public void dev2sasi() {
	    	testPing("dev2sasi.humanbrain.in");
	    	testPing("172.20.23.103");
	    }
	    
	    @Test(priority=6)
	    public void dev2gayathri() {
	    	testPing("dev2gayathri.humanbrain.in");
	    	testPing("172.20.23.7");
	    }
	    
	    @Test(priority=7)
	    public void dev2kamal() {
	    	testPing("dev2kamal.humanbrain.in");
	    	testPing("172.20.23.8");
	    }
	   

	    private void testPing(String machine) {
	        try {
	            // Command to execute
	            String command = "ping -c 4 " + machine; // -c 4 sets the count of ping requests

	            // Create ProcessBuilder
	            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);

	            // Start the process
	            Process process = processBuilder.start();

	            // Read the output of the command
	            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            String line;
	            while ((line = reader.readLine()) != null) {
	                System.out.println(line);
	            }

	            // Wait for the process to complete
	            int exitCode = process.waitFor();
	            System.out.println("Exited with error code " + exitCode);

	            // Check the exit code to determine success or failure
	            if (exitCode == 0) {
	                System.out.println(machine + " is pingable.");
	            } else {
	                System.out.println(machine + " is not pingable.");
	                Assert.fail(machine + " is not pingable.");
	            }

	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	            Assert.fail("Exception occurred while pinging " + machine);
	        }
	    }

}
