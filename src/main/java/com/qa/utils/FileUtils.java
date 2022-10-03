package com.qa.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 文件的工具类
 * 
 */
public class FileUtils {

	/**
	 * 文件路径（包含文件）或者文件的目录<br>
	 * fileName的长度>0,返回文件路径 ,例:AAA/BBB/C.txt<br>
	 * fileName=null或者长度为0,返回文件目录,例:AAA/BBB/<br>
	 * 
	 * @param dir
	 *            目录路径
	 * @param fileName
	 *            文件名称
	 * @param dir
	 * @param fileName
	 * @return
	 **/
	public static String getFilePath(String dir, String fileName) {
		if (fileName != null && fileName.length() > 0) {
			return dir + File.separator + fileName;
		} else {
			return dir + File.separator;
		}
	}

	/**
	 * 判断目录是否存在
	 * 存在:true,不存在:false
	 * 
	 * @param dir
	 * @return
	 */
	public static boolean dirExist(String dir) {
		File file = new File(getFilePath(dir, null));
		if (file.isDirectory()) {
			return true;
		}
		return false;
	}

	/**
	 * 获取文件大小
	 * 
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public static int fileLen(String dir, String fileName) {
		FileInputStream fis = null;
		try {
			if (dirExist(dir) && fileExist(dir, fileName)) {
				File file = new File(getFilePath(dir, fileName));
				fis = new FileInputStream(file);
				return fis.available();
			}
		} catch (Exception e) {
			ExceptionUtil.printlnSo(e);
			return 0;
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
			}
		}
		return 0;
	}

	/**
	 * 文件是否存在<br>
	 * 存在：ture,不存在:false
	 * 
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public static boolean fileExist(String dir, String fileName) {
		if (dirExist(dir)) {
			File file = new File(getFilePath(dir, fileName));
			if (file.exists()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 删除文件
	 * 
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public static boolean delFile(String dir, String fileName) {
		if (fileExist(dir, fileName)) {
			File file = new File(getFilePath(dir, fileName));
			return file.delete();
		}
		return false;
	}

	/**
	 * @date 2014年5月20日 下午6:11:15
	 * @author Tom
	 * @description 删除文件目录
	 */
	public static void deleteFileDir(File file) {
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				file.delete(); // delete()方法 你应该知道 是删除的意思;
			} else if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					deleteFileDir(files[i]); // 把每个文件 用这个方法进行迭代
				}
			}
			file.delete();
		}
	}

	public static void deleteFileDir(String filePath) {
		FileUtils.deleteFileDir(new File(filePath));
	}

	/**
	 * @date 2014年5月20日 上午9:49:39
	 * @author Tom
	 * @description 读取文件夹下的文件，并以map形式返回
	 */
	public static void rdFile(Map<String, String> map, String filePath) {
		File file = new File(filePath);

		if (!file.isDirectory()) {
			System.out.println("文件名：" + file.getName());
		} else if (file.isDirectory()) {
			String[] li = file.list();
			for (int i = 0; i < li.length; i++) {
				File files = new File(filePath + File.separator + li[i]);
				if (!files.isDirectory()) {
					String path = files.getAbsolutePath();
					map.put(path,
							files.getParent().substring(
									files.getParent().lastIndexOf(
											File.separator) + 1));
					// System.out.println("文件名：" + File.pathSeparator);
				} else if (files.isDirectory()) {
					rdFile(map, filePath + File.separator + li[i]);
				}
			}
		}
	}

	/**
	 * @date 2014年7月14日 下午6:58:17
	 * @author Tom
	 * @return Map<String, String> fileName, filePath
	 * @description 读取文件夹下的文件，
	 */
	public static Map<String, String> getFileMap(String filePath) {
		Map<String, String> map = new HashMap<String,String>();

		File file = new File(filePath);

		if (file.isDirectory()) {
			File[] li = file.listFiles();
			for (File f : li) {
				map.put(f.getName(), f.getPath());
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 解压一个压缩文档 到指定位置
	 * 
	 * @param zipFileString
	 *            压缩包的名字
	 * @param outPathString
	 *            指定的路径
	 * @throws Exception
	 */
	public static void UnZipFolder(String zipFileString, String outPathString)
			throws Exception {
		long startTime = System.currentTimeMillis();
		try {
			ZipInputStream Zin = new ZipInputStream(new FileInputStream(
					zipFileString));// 输入源zip路径
			BufferedInputStream Bin = new BufferedInputStream(Zin);
			File Fout = null;
			ZipEntry entry;
			try {
				while ((entry = Zin.getNextEntry()) != null
						&& !entry.isDirectory()) {
        final File zipEntryFile = new File(outPathString, entry.getName());
        if (!zipEntryFile.toPath().normalize().startsWith(outPathString)) {
            throw new IOException("Bad zip entry");
        }
        Fout = zipEntryFile;
					if (!Fout.exists()) {
						(new File(Fout.getParent())).mkdirs();
					}
					FileOutputStream out = new FileOutputStream(Fout);
					BufferedOutputStream Bout = new BufferedOutputStream(out);
					int b;
					while ((b = Bin.read()) != -1) {
						Bout.write(b);
					}
					Bout.close();
					out.close();
					System.out.println(Fout + "解压成功");
				}
				Bin.close();
				Zin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				ExceptionUtil.printlnSo(e);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			ExceptionUtil.printlnSo(e);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("耗费时间： " + (endTime - startTime) + " ms");
	}

	/**
	 * 压缩文件,文件夹
	 * 
	 * @param srcFileString
	 *            要压缩的文件/文件夹名字
	 * @param zipFileString
	 *            指定压缩的目的和名字
	 * @throws Exception
	 */
	public static void ZipFolder(String srcFileString, String zipFileString)
			throws Exception {

		// 创建Zip包
		ZipOutputStream outZip = new ZipOutputStream(new FileOutputStream(
				zipFileString));

		// 打开要输出的文件
		File file = new File(srcFileString);

		// 压缩
		ZipFiles(file.getParent() + File.separator, file.getName(), outZip);

		// 完成,关闭
		outZip.finish();
		outZip.close();

	}

	/**
	 * 压缩文件
	 * 
	 * @param folderString
	 * @param fileString
	 * @param zipOutputSteam
	 * @throws Exception
	 */
	private static void ZipFiles(String folderString, String fileString,
			ZipOutputStream zipOutputSteam) throws Exception {
		if (zipOutputSteam == null)
			return;

		File file = new File(folderString + fileString);

		// 判断是不是文件
		if (file.isFile()) {

			ZipEntry zipEntry = new ZipEntry(fileString);
			FileInputStream inputStream = new FileInputStream(file);
			zipOutputSteam.putNextEntry(zipEntry);

			int len;
			byte[] buffer = new byte[4096];

			while ((len = inputStream.read(buffer)) != -1) {
				zipOutputSteam.write(buffer, 0, len);
			}

			zipOutputSteam.closeEntry();
		} else {

			// 文件夹的方式,获取文件夹下的子文件
			String fileList[] = file.list();

			// 如果没有子文件, 则添加进去即可
			if (fileList.length <= 0) {
				ZipEntry zipEntry = new ZipEntry(fileString + File.separator);
				zipOutputSteam.putNextEntry(zipEntry);
				zipOutputSteam.closeEntry();
			}

			// 如果有子文件, 遍历子文件
			for (int i = 0; i < fileList.length; i++) {
				ZipFiles(folderString, fileString + File.separator
						+ fileList[i], zipOutputSteam);
			}// end of for

		}// end of if

	}// end of func

	/**
	 * @date 2014年5月20日 上午9:59:28
	 * @description 得到图片的路径map 数据格式如下： Map<D:\temp\100001\bedRoom1\1.jpg,
	 *              bedRoom1>
	 */
	public static Map<String, String> getImageMap(String fileFolder) {
		Map<String, String> map = new HashMap<String, String>();

		FileUtils.rdFile(map, fileFolder);

		return map;
	}

	/***
	 * 合并字节数组
	 * 
	 * @param a
	 * @return
	 */
	public static byte[] mergeArray(byte[]... a) {
		// 合并完之后数组的总长度
		int index = 0;
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum = sum + a[i].length;
		}
		byte[] result = new byte[sum];
		for (int i = 0; i < a.length; i++) {
			int lengthOne = a[i].length;
			if (lengthOne == 0) {
				continue;
			}
			// 拷贝数组
			System.arraycopy(a[i], 0, result, index, lengthOne);
			index = index + lengthOne;
		}
		return result;
	}

	/***
	 * Has been tested
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static byte[] readBytes(InputStream in) throws IOException {
		byte[] temp = new byte[in.available()];
		byte[] result = new byte[0];
		int size = 0;
		while ((size = in.read(temp)) != -1) {
			byte[] readBytes = new byte[size];
			System.arraycopy(temp, 0, readBytes, 0, size);
			result = mergeArray(result, readBytes);
		}
		return result;
	}

	public static byte[] changeOutputStream2Bytes(OutputStream outputStream)
			throws IOException {
		return outputStream.toString().getBytes();
		// ByteArrayOutputStream byteArrayOutputStream = new
		// ByteArrayOutputStream();
		//
		// new ByteArrayInputStream(outputStream., 0, outputStream.getCount());
		//
		//
		// byteArrayOutputStream.writeTo(outputStream);
		// return byteArrayOutputStream.toByteArray();
	}

	public static BufferedOutputStream changeInputStream2OutputStream(
			InputStream in) {

		try {
			byte[] a = readBytes(in);
			ByteArrayOutputStream bais = new ByteArrayOutputStream();
			BufferedOutputStream bis = new BufferedOutputStream(bais);
			bis.write(a);
			return bis;
		} catch (IOException e) {
			ExceptionUtil.printlnSo(e);
			return null;
		}

	}
}
