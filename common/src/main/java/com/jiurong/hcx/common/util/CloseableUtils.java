package com.jiurong.hcx.common.util;

import java.io.Closeable;
import java.util.Date;

/**
 * @author soyeajr
 * @date 2019-2-26
 * @Description
 */
public class CloseableUtils {
	public static void close(Closeable... closes) {
		if (closes == null || closes.length == 0) {
			return;
		}
		try {
			for (Closeable closeable : closes) {
				if (closeable != null) {
					closeable.close();
				}
			}
		} catch (Exception e) {
			System.out.println(new Date().toString() + "CloseableUtil.close(...)异常:" + e.getMessage());
		}
	}
}
