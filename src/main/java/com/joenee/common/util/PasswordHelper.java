package com.joenee.common.util;

import com.joenee.common.model.SysUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";
	private int hashIterations = 2;

	public void encryptPassword(SysUser sysUser) {
		String salt = randomNumberGenerator.nextBytes().toHex();
		sysUser.setSalt(salt);
		String newPassword = new SimpleHash(algorithmName, sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getAccountName()+salt), hashIterations).toHex();
		sysUser.setPassword(newPassword);
		System.out.println("newPassword : " + newPassword + ",salt : " + salt);
	}

	/**
	 * 修改密码使用，用于判断原密码是否正确
	 * @param oldPassword
	 * @param sysUser
	 * @return
	 */
	public boolean equalPassword(String oldPassword,SysUser sysUser){
		String oldMD5Password = new SimpleHash(algorithmName, oldPassword, ByteSource.Util.bytes(sysUser.getAccountName() + sysUser.getSalt()), hashIterations).toHex();
		if(oldMD5Password.equals(sysUser.getPassword())){
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		PasswordHelper passwordHelper = new PasswordHelper();
		SysUser sysUser = new SysUser();
		sysUser.setPassword("111111");
		sysUser.setAccountName("helizheng");
		passwordHelper.encryptPassword(sysUser);
		System.out.println(sysUser.getPassword());
	}
}
