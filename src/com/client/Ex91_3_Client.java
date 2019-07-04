package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ex91_3_Client { //�ͻ��˳���

	public static void main(String[] args) {
		Socket server = null;
		try {
			String inputString;
			server = new Socket("127.0.0.1", 4444); //�򱾻�4444�˿ڷ����ͻ�����
			System.out.println("��������Ϣ��");
			
			//��ϵͳ��׼�����豸����BufferedReader����
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			//��socket����õ��������������PrintWriter����
			PrintWriter os = new PrintWriter(server.getOutputStream());
			//��socket����õ���������������BufferedReader����
			BufferedReader is = new BufferedReader(new InputStreamReader(server.getInputStream()));
			inputString = sin.readLine(); //�ӱ�׼�������һ�ַ���
			//������ַ���Ϊquit����ֹͣѭ��
			while (inputString != null && !inputString.trim().equals("quit")) { 
				os.println(inputString); //��server��������ַ���
				os.flush(); //ˢ���������ʹserver�������յ����ַ���
				System.out.println("Client���͵���ϢΪ��" + inputString); //����Ļ����ʾ������ַ���
				//��server����һ�ַ���������ӡ����׼�����
				System.out.println("Server���͵���ϢΪ��" + is.readLine()); 
				inputString = sin.readLine(); //��ϵͳ��׼�������һ�ַ���
			}
			os.close(); //�ر�Socket�����
			is.close(); //�ر�socket������
			server.close(); //�ر�ServerSocket
			System.out.println("�������");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
