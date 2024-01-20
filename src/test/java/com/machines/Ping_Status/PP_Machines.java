package com.machines.Ping_Status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PP_Machines {
	
	 @Test(priority=1)
	    public void pp1() {
	        testPing("pp1.humanbrain.in");
	        testPing("172.20.24.1");
	    }

	    @Test(priority=2)
	    public void pp2() {
	        testPing("pp2.humanbrain.in");
	        testPing("172.20.24.2");
	    }

	    @Test(priority=3)
	    public void pp3v5() {
	        testPing("pp3v5.humanbrain.in");
	        testPing("172.20.24.3");
	    }
	    
	    @Test(priority=4)
	    public void pp4v5() {
	    	testPing("pp4v5.humanbrain.in");
	    	testPing("172.20.24.4");
	    }
	    
	    @Test(priority=5)
	    public void pp4v15() {
	    	testPing("pp4v15.humanbrain.in");
	    	testPing("172.20.24.14");
	    }
	    
	    @Test(priority=6)
	    public void pp5v5() {
	    	testPing("pp5v5.humanbrain.in");
	    	testPing("172.20.24.5");
	    }
	    
	    @Test(priority=7)
	    public void pp5v15() {
	    	testPing("pp5v15.humanbrain.in");
	    	testPing("172.20.24.15");
	    }
	    @Test(priority=8)
	    public void pp7v5() {
	    	testPing("pp7v5.humanbrain.in");
	    	testPing("172.20.24.7");
	    }
	    
	    @Test(priority=9)
	    public void pp7v15() {
	    	testPing("pp7v15.humanbrain.in");
	    	testPing("172.20.24.17");
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
