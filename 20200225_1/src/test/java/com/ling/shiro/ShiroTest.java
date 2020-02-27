package com.ling.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class ShiroTest {
	// ����ʹ��Shiro�ĵ���ǳ�
	@Test
	public void testLoginOut() throws Exception{
		// 1.����SecurityManager����,ͨ����ȡ shiro.ini�����ļ�����
		IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		
		// 2.����SecurityManager��ȫ����������
		SecurityManager securityManager = factory.createInstance();
		
		// 3.����ȫ�������������õ���ǰ������
		SecurityUtils.setSecurityManager(securityManager);
		
		// 4.������֤��ͨ�׽������ǵ�½�������ã�һ�㶼ʹ���˺��������ƣ�
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhangsan1","123");
		
		// 5.��������������ڵ�½
		Subject subject = SecurityUtils.getSubject();
		
		// 5.1�ж��Ƿ���֤����½����û����֤�Ϳ�ʼ��֤
		boolean isAuthenticated = subject.isAuthenticated();
		System.out.println("�Ƿ�ͨ����֤��"+isAuthenticated);
		if(!isAuthenticated) {
			try {
				subject.login(usernamePasswordToken);
			} catch (UnknownAccountException  e) {
				System.out.println("�˺Ų����ڣ�");
			}catch(IncorrectCredentialsException e) {
				System.out.println("�������");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		// �ж��Ƿ��½
		System.out.println("�Ƿ�ͨ����֤��"+subject.isAuthenticated());
		// �˳�
		subject.logout();
		System.out.println("�Ƿ�ͨ����֤��"+subject.isAuthenticated());
		
	
	
	
	}

}
