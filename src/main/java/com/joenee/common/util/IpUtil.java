package com.joenee.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * IP工具类
 * 
 * 
 */
public class IpUtil {
	private static final Logger logger = LoggerFactory.getLogger(IpUtil.class);
	/**
	 * 获取登录用户的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request){
		String ipAddress = request.getHeader("x-forwarded-for");
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
				//根据网卡取本机配置的IP
				InetAddress inet=null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					logger.error(e.getMessage(),e);
				}
				ipAddress= inet.getHostAddress();
			}
		}
		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
			if(ipAddress.indexOf(",")>0){
				ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * 通过IP获取地址(需要联网，调用淘宝的IP库)
	 * 
	 * @param ip
	 * @return
	 */
//	public static String getIpInfo(String ip) {
//		if (ip.equals("本地")) {
//			ip = "127.0.0.1";
//		}
//		String info = "";
//		try {
//			URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
//			HttpURLConnection htpcon = (HttpURLConnection) url.openConnection();
//			htpcon.setRequestMethod("GET");
//			htpcon.setDoOutput(true);
//			htpcon.setDoInput(true);
//			htpcon.setUseCaches(false);
//
//			InputStream in = htpcon.getInputStream();
//			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
//			StringBuffer temp = new StringBuffer();
//			String line = bufferedReader.readLine();
//			while (line != null) {
//				temp.append(line).append("\r\n");
//				line = bufferedReader.readLine();
//			}
//			bufferedReader.close();
//			JSONObject obj = (JSONObject) JSON.parse(temp.toString());
//			if (obj.getIntValue("code") == 0) {
//				JSONObject data = obj.getJSONObject("data");
//				info += data.getString("country") + " ";
//				info += data.getString("region") + " ";
//				info += data.getString("city") + " ";
//				info += data.getString("isp");
//			}
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (ProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return info;
//	}

}
