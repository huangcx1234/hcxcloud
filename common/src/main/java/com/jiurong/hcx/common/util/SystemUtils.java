package com.jiurong.hcx.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * @author soyeajr
 * @date 2019-2-26
 * @Description
 */
public class SystemUtils {

	private static String osName = System.getProperty("os.name").toLowerCase();

	/**
	 * 判断系统为Linux
	 *
	 * @return 是Linux返回true，否则false
	 */
	public static boolean isLinux() {
		return osName.contains("linux");
	}

	/**
	 * 判断系统为MacOSX
	 *
	 * @return 是MacOSX返回true，否则false
	 */
	public static boolean isMacOSX() {
		return osName.contains("mac") && osName.contains("os") && osName.contains("x");
	}

	/**
	 * 判断系统为Windows
	 *
	 * @return 是Windows返回true，否则false
	 */
	public static boolean isWindows() {
		return osName.contains("windows");
	}

	/**
	 * 获取本机所有网卡的所有IP
	 *
	 * @return IP列表
	 */
	public static ArrayList<String> getLocalIPList() {
		ArrayList<String> ipList = new ArrayList<String>();
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			NetworkInterface networkInterface;
			Enumeration<InetAddress> inetAddresses;
			InetAddress inetAddress;
			String ip;
			while (networkInterfaces.hasMoreElements()) {
				networkInterface = networkInterfaces.nextElement();
				inetAddresses = networkInterface.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					inetAddress = inetAddresses.nextElement();
					if (inetAddress != null) { // IPV4 or IPV6
						ip = inetAddress.getHostAddress();
						ipList.add(ip);
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return ipList;
	}
}
