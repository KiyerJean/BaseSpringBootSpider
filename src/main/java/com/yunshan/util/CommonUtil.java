package com.yunshan.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


public class CommonUtil {

	//判断是否是数字
	public static boolean isInt(String str) {
		if(str!=null){
			boolean isInt = Pattern.compile("0|([-]?[1-9][0-9]*)").matcher(str).find();
			return isInt;
		}else{
			return false;
		}
	}
	
	//判断是否是double
	public static boolean isDouble(String str){
		boolean bo=false;
		if(str!=null){
			 Pattern pattern= Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
			 Matcher match=pattern.matcher(str); 
		     bo = match.matches();
		}
		return bo;
	}
	
	//判断是否是空字符串
	public static boolean isNull(String str){
		if(str!=null){
			return str.trim().isEmpty();
		}else{
			return true;
		}
	}
	
	//获取时间戳 秒
	public static long getTimeSeconds(){
		return System.currentTimeMillis();
	}
	
	//获取日期字符串"yyyy-MM-dd"
	public static String getDayString(String pattern){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(pattern);
		return df.format(date);
	}
	
	//获取秘钥
	public static String getSignature(String str){
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    String signature=MD5Util.getHash(str+calendar.getTimeInMillis()/1000,"md5");
	    return signature;
	}
	
	//获取当前月份往前推或者往后推的时间戳
	public static long getOffsetTime(int offset) {
		
		Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,offset);
		calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        return calendar.getTimeInMillis();
	}
	
	 /** 
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 
     * 
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
     * 
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 
     * 192.168.1.100 
     * 
     * 用户真实IP为： 192.168.1.110 
     * 
     * @param request 
     * @return 
     */
    public static String getIpAddress(HttpServletRequest request) { 
      String ip = request.getHeader("x-forwarded-for"); 
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
        ip = request.getHeader("Proxy-Client-IP"); 
      } 
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
        ip = request.getHeader("WL-Proxy-Client-IP"); 
      } 
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
        ip = request.getHeader("HTTP_CLIENT_IP"); 
      } 
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
        ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
      } 
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
        ip = request.getRemoteAddr(); 
      } 
      return ip; 
    }
    
    /** 
     * 发送POST请求 
     *  
     * @param url 目的地址
     *             
     * @param parameters 请求参数，Map类型。
     *             
     * @return 远程响应结果 
     */  
    public static String sendPost(String url, Map<String, String> parameters) {  
        String result = "";// 返回的结果  
        BufferedReader in = null;// 读取响应输入流  
        PrintWriter out = null;  
        StringBuffer sb = new StringBuffer();// 处理请求参数  
        String params = "";// 编码之后的参数  
        try {  
            // 编码请求参数  
            if (parameters.size() == 1) {  
                for (String name : parameters.keySet()) {  
                    sb.append(name).append("=").append(URLEncoder.encode(parameters.get(name),"GBK"));  
                }  
                params = sb.toString();  
            } else {  
                for (String name : parameters.keySet()) {  
                    sb.append(name).append("=").append(URLEncoder.encode(parameters.get(name),"GBK")).append("&");  
                }  
                String temp_params = sb.toString();  
                params = temp_params.substring(0, temp_params.length() - 1);  
            }  
            // 创建URL对象  
            URL connURL = new URL(url);  
            // 打开URL连接  
            HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();  
            // 设置通用属性  
            //httpConn.setRequestProperty("Accept", "*/*");  
            //httpConn.setRequestProperty("Connection", "Keep-Alive");  
            //httpConn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");  
            // 设置POST方式  
            httpConn.setDoInput(true);  
            httpConn.setDoOutput(true);  
            // 获取HttpURLConnection对象对应的输出流  
            out = new PrintWriter(httpConn.getOutputStream());  
            // 发送请求参数  
            out.write(params);  
            // flush输出流的缓冲  
            out.flush();  
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式  
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "GBK"));  
            String line;  
            // 读取返回的内容  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {
	        	if (in != null) {  
	                  in.close();  
	            }  
                if (out != null) {  
                    out.close();  
                }  
              
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }
    
    // @描述：是否是2003的excel，返回true是2003 
    public static boolean isExcel2003(String filePath)  {  
        return filePath.matches("^.+\\.(?i)(xls)$");  
    }  
  
     //@描述：是否是2007的excel，返回true是2007 
    public static boolean isExcel2007(String filePath)  {  
        return filePath.matches("^.+\\.(?i)(xlsx)$");  
    }  
	
}
