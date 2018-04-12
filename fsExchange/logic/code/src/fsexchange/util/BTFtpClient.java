package fsexchange.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;

public class BTFtpClient extends FTPClient {

	// private static final Logger logger = Logger.getLogger(BTFtpClient.class);

	public BTFtpClient() {
		super();
	}

	public BTFtpClient(String host) throws IOException {
		super();
		this.connect(host);
	}

	public BTFtpClient(String host, int port) throws IOException {
		super();
		this.connect(host, port);
	}

	public boolean isDirExist(String dir) throws IOException {
		this.pwd();
		String pwd = this.getReplyString();
		try {
			return changeWorkingDirectory(dir);
		} finally {
			changeWorkingDirectory(pwd);
		}

	}

	/**
	 * 上传目录
	 * 
	 * @param dirName
	 * @param ftpRoot
	 * @throws Exception
	 */
	public void uploadDir(String dirName, String ftpRoot) throws Exception {
		if (ftpRoot == null) {
			ftpRoot = "/";
		}
		File dir = new File(dirName);
		if (!dir.exists()) {
			throw new FileNotFoundException(dirName + "不存在");
		}
		if (!dir.isDirectory()) {
			throw new FileNotFoundException(dirName + "不是目录");
		}
		// 创建FTP根目录
		if (!isDirExist(ftpRoot)) {
			this.makeDirectory(ftpRoot);
		}
		File[] sourceFiles = dir.listFiles();
		for (File sourceFile : sourceFiles) {
			if (sourceFile.isDirectory()) {
				String ftpDir = ftpRoot + "/" + sourceFile.getName();
				uploadDir(sourceFile.getAbsolutePath(), ftpDir);
			} else {
				System.out.println("upload " + sourceFile.getAbsolutePath() + " to " + ftpRoot + "/" + sourceFile.getName());
				uploadFile(sourceFile.getAbsolutePath(), ftpRoot + "/" + sourceFile.getName());
			}
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param fileName
	 * @param ftpFullName
	 * @return
	 * @throws Exception
	 */
	public void uploadFile(String fileName, String ftpFullName) throws Exception {
		File file_in = new File(fileName);// 打开本地待长传的文件
		if (!file_in.exists()) {
			throw new FileNotFoundException(fileName + "不存在");
		}
		if (file_in.isDirectory()) {
			throw new FileNotFoundException(fileName + "不是文件");
		}
		FileInputStream is = null;
		try {
			is = new FileInputStream(file_in);
			uploadFile(is, ftpFullName);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {

			}
		}
	}

	public void uploadFile(InputStream in, String ftpFullName) throws Exception {
		File ftpFile = new File(ftpFullName);
		// 创建目录
		if (ftpFile.getParentFile() != null) {
			if (!isDirExist(ftpFile.getParent())) {
				this.makeDirectory(ftpFile.getParent());
			}
		}
		storeFile(ftpFullName, in);
	}

	public static void main(String args[]) {
		BTFtpClient ftp = new BTFtpClient();
		try {
			ftp.connect("19.129.80.36");
			ftp.login("fs_tysp", "taijisoft");
			ftp.enterLocalPassiveMode();
			ftp.uploadFile("d:\\test.txt", "test/test6.txt");
			// ftp.uploadFile("d:\\test.txt", "/test/test/test1/test1.txt");
			// ftp.uploadDir("d:\\temp", "test");
			ftp.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * FTP远程命令列表 USER PORT RETR ALLO DELE SITE XMKD CDUP FEAT PASS PASV STOR
	 * REST CWD STAT RMD XCUP OPTS ACCT TYPE APPE RNFR XCWD HELP XRMD STOU AUTH
	 * REIN STRU SMNT RNTO LIST NOOP PWD SIZE PBSZ QUIT MODE SYST ABOR NLST MKD
	 * XPWD MDTM PROT
	 * 在服务器上执行命令,如果用sendServer来执行远程命令(不能执行本地FTP命令)的话，所有FTP命令都要加上\r\n
	 * ftpclient.sendServer("XMKD /test/bb\r\n"); //执行服务器上的FTP命令
	 * ftpclient.readServerResponse一定要在sendServer后调用
	 * nameList("/test")获取指目录下的文件列表 XMKD建立目录，当目录存在的情况下再次创建目录时报错 XRMD删除目录
	 * DELE删除文件
	 */
}
