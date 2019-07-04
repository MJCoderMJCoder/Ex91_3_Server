package com.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex91_3_Server { //����˳���
	public static void main(String[] args) {
		try{
			ServerSocket server = null;
			try {
				server = new ServerSocket(4444); //����һ��ServerSocket�ڶ˿�4444�����ͻ�����
			} catch(Exception e) {
				System.out.println("Error" + e); //��Ļ��ӡ������Ϣ
				System.exit(-1); //ǿ����ֹ��ǰ�������е�java�������������Ϊ0,��ʾ������ֹ;����,�쳣��ֹ
			}
			Socket client = null;
			try {
				client = server.accept(); //ʹ��accept()�����ȴ��ͻ�����;�пͻ������������һ��Socket����
			} catch(Exception e) {
				System.out.println("��������ʧ�ܣ�");
				System.exit(-1);
			}
			String inputString;
			
			//��Socket����õ�����������������Ӧ��BufferedReader����
			BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			//��Socket����õ��������������PrintWriter����
			PrintWriter os = new PrintWriter(client.getOutputStream());
			//��ϵͳ��׼�����豸����BufferdReader����
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			//�ڱ�׼����ϴ�ӡ�ӿͻ��˶�����ַ���
			System.out.println("Client���͵���ϢΪ��" + is.readLine());
			inputString = sin.readLine(); //�ӱ�׼�������һ�ַ���
			//������ַ���Ϊquit����ֹͣѭ��
			while (inputString != null && !inputString.trim().equals("quit")) {
				os.println(inputString); //��ͻ���������ַ���
				os.flush(); //ˢ���������ʹClient�����յ����ַ���
				System.out.println("Server���͵���ϢΪ��" + inputString); //����Ļ����ʾ������ַ���
				//��Client����һ�ַ���������ӡ����׼�����
				System.out.println("Client���͵���ϢΪ��" + is.readLine());
				inputString = sin.readLine(); //��ϵͳ��׼�������һ�ַ���
			} //����ѭ��
			os.close(); //�ر�Socket�����
			is.close(); //�ر�Sochet������
			client.close(); //�ر�Socket
			server.close(); //�ر�ServerSocket
			System.out.println("���������");
		} catch (Exception e) {
			System.out.println("Error��" + e);
		}		
	}
} //�������˳������
