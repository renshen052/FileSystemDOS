package com.command.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import com.command.Command;
import com.controller.FileController;

public class Copy implements Command {

	@Override
	public int excute(String[] command) {

		// 验证命令合法
		if (command.length != 3) {
			return Command.ERRORCOMMAND;
		}

		// 目标地址
		File target = null;
		if (command[2].equals(".")) {// "."代表当前路径
			target = new File(FileController.basePath);
		} else {
			// 绝对或相对路径
			if (command[2].contains(":")) {
				target = new File(command[2]);
			} else {
				target = new File(FileController.basePath + "/" + command[2]);
			}
		}

		// 目标路径不是一个目录
		if (!target.isDirectory()) {
			return Command.ERROR;
		}

		//复制单个文件
		if(!command[1].contains("+")) {
			
			if(command[1].contains(":")) {
				copy(new File(command[1]), target);
			}else {
				copy(new File( FileController.basePath + "/" + command[1]), target);
			}
			
		}else {//复制多个文件
			
			// 解析得到要复制的文件列表
			File[] files = paraseFile(command[1]);

			// 源文件为空
			if (files == null) {
				return Command.NOTFINDSECONDE;
			}

			// 依次拷贝
			for (File file : files) {
				copy(file, target);
			}
			
		}

		return Command.SUCCESS;
	}

	/**
	 * 拷贝文件file到targe目录
	 * 
	 * @param file
	 * @param target
	 */
	private void copy(File file, File target) {

		File file_new = new File(target.getPath() + "/" + file.getName());

		try (InputStream is = new FileInputStream(file);
				Reader reader = new InputStreamReader(is);
				BufferedReader bufferedReader = new BufferedReader(reader);

				OutputStream os = new FileOutputStream(file_new);
				Writer writer = new OutputStreamWriter(os);
				BufferedWriter bufferedWriter = new BufferedWriter(writer);

		) {
			// 读出来，然后写进目标
			String str;
			while ((str = bufferedReader.readLine()) != null) {
				bufferedWriter.write(str);
			}
		} catch (Exception e) {
			System.out.println("拷贝" + file.getName() + "失败！");
		}
	}

	/**
	 * 解析源目标文件
	 * 
	 * @param string
	 * @return
	 */
	private File[] paraseFile(String sources) {

		String[] strs = sources.split("\\+");
		File[] files = new File[strs.length];
		for (int i = 0; i < strs.length; i++) {
			if (!strs[i].contains(":")) {
				files[i] = new File(FileController.basePath + "/" + strs[i]);
			} else {
				files[i] = new File(strs[i]);
			}
			// 只要有一个文件不存在，那就终止复制
			if (!files[i].exists()) {
				return null;
			}
		}
		return files;
	}

	/**
	 * copy 帮助
	 */
	public void help() {
		StringBuilder str = new StringBuilder();
		str.append("----------------copy help-----------\n");
		str.append("命令格式：\n");
		str.append("coyp 源目标文件 目标地址    复制源文件到目标路径   \n\n");
		str.append("数个文件(用 file1+file2+file3 格式)");

		str.append("如: copy D:\\fileJavaSystem\\222.txt E:\\fileJavaSystem\\k\\m \n\n");

		System.out.println(str);

	}

}
