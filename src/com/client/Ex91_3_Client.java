package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ex91_3_Client { //客户端程序

	public static void main(String[] args) {
		Socket server = null;
		try {
			String inputString;
			server = new Socket("127.0.0.1", 4444); //向本机4444端口发出客户请求
			System.out.println("请输入信息：");
			
			//由系统标准输入设备构造BufferedReader对象
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			//由socket对象得到输出流，并构造PrintWriter对象
			PrintWriter os = new PrintWriter(server.getOutputStream());
			//由socket对象得到输入流，并构造BufferedReader对象
			BufferedReader is = new BufferedReader(new InputStreamReader(server.getInputStream()));
			inputString = sin.readLine(); //从标准输入读入一字符串
			//如果该字符串为quit，则停止循环
			while (inputString != null && !inputString.trim().equals("quit")) { 
				os.println(inputString); //向server端输出该字符串
				os.flush(); //刷新输出流，使server端马上收到该字符串
				System.out.println("Client发送的信息为：" + inputString); //在屏幕上显示读入的字符串
				//从server读入一字符串，并打印到标准输出上
				System.out.println("Server发送的信息为：" + is.readLine()); 
				inputString = sin.readLine(); //从系统标准输入读入一字符串
			}
			os.close(); //关闭Socket输出流
			is.close(); //关闭socket输入流
			server.close(); //关闭ServerSocket
			System.out.println("聊天结束");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
