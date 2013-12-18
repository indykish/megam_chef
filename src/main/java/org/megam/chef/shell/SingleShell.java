/* 
 ** Copyright [2012-2013] [Megam Systems]
 **
 ** Licensed under the Apache License, Version 2.0 (the "License");
 ** you may not use this file except in compliance with the License.
 ** You may obtain a copy of the License at
 **
 ** http://www.apache.org/licenses/LICENSE-2.0
 **
 ** Unless required by applicable law or agreed to in writing, software
 ** distributed under the License is distributed on an "AS IS" BASIS,
 ** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ** See the License for the specific language governing permissions and
 ** limitations under the License.
 */
package org.megam.chef.shell;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.RecursiveAction;

import org.megam.chef.exception.SourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.megam.chef.exception.ShellException;

/**
 * 
 * @author rajthilak
 * 
 */
public class SingleShell extends RecursiveAction implements Stoppable {

	private Command cmd;
	private Logger logger = LoggerFactory.getLogger(SingleShell.class);
	private ProcessBuilder shellProc;
	/**
	 * 
	 * @param tempcmd
	 */
	public SingleShell(Command tempcmd) {
		this.cmd = tempcmd;
	}

	/**
	 * Processed the command using ProcessBuilder class Print the output's are
	 * wrote in the some file
	 */

	public void compute() {
		try {
			logger.debug("------------------->" + cmd.getCommandList());
			shellProc = new ProcessBuilder(cmd.getCommandList());
			shellProc.redirectOutput(Redirect.appendTo(cmd.getRedirectOutputFile()));
			shellProc.redirectError(Redirect.appendTo(cmd.getRedirectErrorFile()));
			shellProc.start();
			logger.debug("-------> An instance was started");
		} catch (IOException npe) {
			try {
				throw new ShellException(npe);
			} catch (ShellException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.debug("-------> An instance was started, exited.");
	}
	
	public void halt() {
		//do nothing for now, but we need to cancel the process that runs, and start the 
		//Rollback. Needs design in herk, to handle it.
		
		
	}

}
