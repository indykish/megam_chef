package org.megam.chef.parser;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.megam.chef.core.Condition;
import org.megam.chef.core.ScriptFeeder;
import org.megam.chef.shell.FedInfo;

/**
 * 
 * @author rajthilak
 * 
 */
public class ComputeInfo implements DataMap, ScriptFeeder, Condition {

	private static final String GROUPS = "groups";
	private static final String IMAGE = "image";
	private static final String FLAVOR = "flavor";
	private static final String SSHKEY = "ssh-key";
	private static final String DSSHKEY = "ssh_key";
	private static final String IDENTITYFILE = "identity-file";
	private static final String DIDENTITYFILE = "identity_file";
	private static final String SSHUSER = "ssh-user";
	private static final String DSSHUSER = "ssh_user";
	private List<String> inputavailablereason = new ArrayList<String>();

	/**
	 * create Map name as cc (cross cloud) from config.json file
	 */
	private String cctype;
	private Map<String, String> cc = new HashMap<String, String>();
	private Map<String, String> access = new HashMap<String, String>();
	private FedInfo fed;

	public ComputeInfo() {
	}

	public String getCCType() {
		return cctype;
	}

	/**
	 * @return ec2 map
	 */
	public Map<String, String> map() {
		if (!cc.keySet().containsAll(access.keySet())) {
			cc.putAll(access);
		}
		return cc;
	}

	

	/**
	 * 
	 * @return sshkey
	 */
	public String getSshKey() {
		return map().get(DSSHKEY);
	}

	/**
	 * 
	 * @return identity file
	 */
	public String getIdentityFile() {
		return map().get(DIDENTITYFILE);
	}

	/**
	 * 
	 * @return ssh user
	 */
	public String getSshUser() {
		return map().get(DSSHUSER);
	}

	/**
	 * toString method for ec2 map
	 */
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		for (Map.Entry<String, String> entry : map().entrySet()) {
			formatter.format("%10s = %s%n", entry.getKey(), entry.getValue());
		}
		formatter.close();
		return strbd.toString();
	}

	/*
	 * public String toString() {
	 * 
	 * return ( getGroups() + " " + getImage() + " " + getFlavor() + " " + "--"
	 * + SSHKEY + " " + getSshKey() + " " + "--" + IDENTITYFILE + " " +
	 * getIdentityFile() + " " + "--" + SSHUSER + " " + getSshUser());
	 * 
	 * }
	 */
	public String getName() {
		return "cloud";

	}

	public boolean canFeed() {
		return true;
	}

	public FedInfo feed() {
		fed = new FedInfo(getName(), " " + getGroups() + " " + getImage() + " "
				+ getFlavor() + " " + "--" + SSHKEY + " " + getSshKey() + " "
				+ "--" + IDENTITYFILE + " " + getIdentityFile() + " " + "--"
				+ SSHUSER + " " + getSshUser());
		return fed;
	}

	public String getGroups() {
		return "--" + GROUPS + " " + cc.get(GROUPS);
	}

	public String getImage() {
		return "--" + IMAGE + " " + cc.get(IMAGE);
	}

	public String getFlavor() {
		return "--" + FLAVOR + " " + cc.get(FLAVOR);
	}

	public List<String> getReason() {
		return inputavailablereason;
	}

	public boolean ok() {
		boolean isOk = true;
		isOk = isOk && validate("groups", "megam");
		isOk = isOk && validate("image", "ami-56e6a404");
		isOk = isOk && validate("flavor", "m1.small");
		return isOk;
	}

	public boolean validate(String key, String value) {
		for (Map.Entry<String, String> entry : map().entrySet()) {
			if (entry.getKey().equals(key)) {
				if (entry.getValue().equals(value)) {
					return true;
				} else {
					inputavailablereason.add(key + " is not valid ");
				}
			}
		}
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.core.Condition#inputAvailable()
	 */
	public boolean inputAvailable() {
		boolean isAvailable = true;
		isAvailable = isAvailable && notNull("groups");
		isAvailable = isAvailable && notNull("image");
		isAvailable = isAvailable && notNull("flavor");
		return isAvailable;
	}

	public boolean notNull(String str) {
		if (map().containsKey(str)) {
			return true;
		} else {
			inputavailablereason.add(str + " is Missing");
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.core.Condition#name()
	 */
	public String name() {
		return "ComputeInfo";
	}

}