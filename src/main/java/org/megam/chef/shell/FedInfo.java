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

/**
 * @author subash
 *
 */
public class FedInfo {
	
	private String name;
    private String shellString;
    public FedInfo(String name, String ShellString)
    {
    	this.name=name;
    	this.shellString=ShellString;
    	System.out.println("name:"+name);
    	System.out.println("command:"+shellString);
    }

      //return the name
	 
	public String getName() {
		return name;
	}

	
	  //set the name 
	 
	public void setName(String name) {
		this.name = name;
	}

	//return the shellString
	
	public String getShellString() {
		return shellString;
}

	
	// set the shellString 
	 
	public void setShellString(String shellString) {
		this.shellString = shellString;
	}
    
}
