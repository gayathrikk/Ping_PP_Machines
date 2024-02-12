package com.machines.Ping_Status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class dgx_Machines {
	

	 @Test(priority=1)
	    public void dgx1() {
	        testPing("dgx1.humanbrain.in");
	        testPing("172.20.24.102");
	    }

	    @Test(priority=2)
	    public void dgx2() {
	        testPing("dgx2.humanbrain.in");
	        testPing("172.20.23.50");
	    }
	    

	    @Test(priority=2)
	    public void dgx3() {
	        testPing("dgx3.humanbrain.in");
	        testPing("172.20.23.53");
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
