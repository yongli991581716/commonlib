
package com.common.lib.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * File opera tools
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class FileUtils
{
    /**
     * write text file in Android System锛孎ile save /data/data/PACKAGE_NAME/files directory
     * 
     * @param context
     * @param msg
     */
    public static void write(Context context, String fileName, String content)
    {
        if (content == null)
            content = "";

        try
        {
            FileOutputStream fos = context.openFileOutput(fileName,
                    Context.MODE_PRIVATE);
            fos.write(content.getBytes());

            fos.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * read text file
     * 
     * @param context
     * @param fileName
     * @return
     */
    public static String read(Context context, String fileName)
    {
        try
        {
            FileInputStream in = context.openFileInput(fileName);
            return readInStream(in);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }

    private static String readInStream(FileInputStream inStream)
    {
        try
        {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[512];
            int length = -1;
            while ((length = inStream.read(buffer)) != -1)
            {
                outStream.write(buffer, 0, length);
            }

            outStream.close();
            inStream.close();
            return outStream.toString();
        } catch (IOException e)
        {
            Log.i("FileTest", e.getMessage());
        }
        return null;
    }

    public static File createFile(String folderPath, String fileName)
    {
        File destDir = new File(folderPath);
        if (!destDir.exists())
        {
            destDir.mkdirs();
        }
        return new File(folderPath, fileName + fileName);
    }

    /**
     * to phone write image
     * 
     * @param buffer
     * @param folder
     * @param fileName
     * @return
     */
    public static boolean writeFile(byte[] buffer, String folder,
            String fileName)
    {
        boolean writeSucc = false;

        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(
                        android.os.Environment.MEDIA_MOUNTED);

        String folderPath = "";
        if (sdCardExist)
        {
            folderPath = Environment.getExternalStorageDirectory()
                    + File.separator + folder + File.separator;
        }
        else
        {
            writeSucc = false;
        }

        File fileDir = new File(folderPath);
        if (!fileDir.exists())
        {
            fileDir.mkdirs();
        }

        File file = new File(folderPath + fileName);
        FileOutputStream out = null;
        try
        {
            out = new FileOutputStream(file);
            out.write(buffer);
            writeSucc = true;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                out.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return writeSucc;
    }

    /**
     * as absolute write file
     * 
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath)
    {
        if (TextUtils.isEmpty(filePath))
            return "";
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
    }

    /**
     * as absolute get file path ,but uninclue expand name
     * 
     * @param filePath
     * @return
     */
    public static String getFileNameNoFormat(String filePath)
    {
        if (TextUtils.isEmpty(filePath))
        {
            return "";
        }
        int point = filePath.lastIndexOf('.');
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1,
                point);
    }

    /**
     *get file expand name
     * 
     * @param fileName
     * @return
     */
    public static String getFileFormat(String fileName)
    {
        if (TextUtils.isEmpty(fileName))
            return "";

        int point = fileName.lastIndexOf('.');
        return fileName.substring(point + 1);
    }

    /**
     * get file size
     * 
     * @param filePath
     * @return
     */
    public static long getFileSize(String filePath)
    {
        long size = 0;

        File file = new File(filePath);
        if (file != null && file.exists())
        {
            size = file.length();
        }
        return size;
    }

    /**
     * get file size
     * 
     * @param size byte
     * @return
     */
    public static String getFileSize(long size)
    {
        if (size <= 0)
            return "0KB";
        java.text.DecimalFormat df = new java.text.DecimalFormat("##.#");
        float temp = (float) size / 1024;
        if (temp >= 1024)
        {
            return df.format(temp / 1024) + "MB";
        }
        else
        {
            return df.format(temp) + "KB";
        }
    }

    /**
     * change file size
     * 
     * @param fileS
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(long fileS)
    {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024)
        {
            fileSizeString = df.format((double) fileS) + "B";
        }
        else if (fileS < 1048576)
        {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        }
        else if (fileS < 1073741824)
        {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        }
        else
        {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * get diretory file size
     * 
     * @param dir
     * @return
     */
    public static long getDirSize(File dir)
    {
        if (dir == null)
        {
            return 0;
        }
        if (!dir.isDirectory())
        {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files)
        {
            if (file.isFile())
            {
                dirSize += file.length();
            }
            else if (file.isDirectory())
            {
                dirSize += file.length();
                dirSize += getDirSize(file); // 閫掑綊璋冪敤缁х画缁熻
            }
        }
        return dirSize;
    }

    /**
     * get directory file num
     * 
     * @param f
     * @return
     */
    public static long getFileList(File dir)
    {
        long count = 0;
        File[] files = dir.listFiles();
        count = files.length;
        for (File file : files)
        {
            if (file.isDirectory())
            {
                count = count + getFileList(file);// 閫掑綊
                count--;
            }
        }
        return count;
    }

    /**
     * root all files
     * 
     * @param path
     * @return absolute path
     */
    public static List<File> listFile(String root)
    {
        List<File> files = new ArrayList<File>();
        SecurityManager checker = new SecurityManager();
        File path = new File(root);
        checker.checkRead(root);
        if (path.isDirectory())
        {
            for (File f : path.listFiles())
            {
                if (f.isFile())
                {
                    files.add(f);
                }
            }
        }
        return files;
    }

    public static byte[] toBytes(InputStream in) throws IOException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int ch;
        while ((ch = in.read()) != -1)
        {
            out.write(ch);
        }
        byte buffer[] = out.toByteArray();
        out.close();
        return buffer;
    }

    /**
     * validate file exists
     * 
     * @param name
     * @return
     */
    public static boolean checkFileExists(String name)
    {
        boolean status;
        if (!name.equals(""))
        {
            File path = Environment.getExternalStorageDirectory();
            File newPath = new File(path.toString() + name);
            status = newPath.exists();
        }
        else
        {
            status = false;
        }
        return status;
    }

    /**
     * validate file path exists
     * 
     * @param path
     * @return
     */
    public static boolean checkFilePathExists(String path)
    {
        return new File(path).exists();
    }

    /**
     * caculate SD expair space
     * 
     * @return return -1锛宯ot sd
     */
    public static long getFreeDiskSpace()
    {
        String status = Environment.getExternalStorageState();
        long freeSpace = 0;
        if (status.equals(Environment.MEDIA_MOUNTED))
        {
            try
            {
                File path = Environment.getExternalStorageDirectory();
                StatFs stat = new StatFs(path.getPath());
                @SuppressWarnings("deprecation")
                long blockSize = stat.getBlockSize();
                @SuppressWarnings("deprecation")
                long availableBlocks = stat.getAvailableBlocks();
                freeSpace = availableBlocks * blockSize / 1024;
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            return -1;
        }
        return (freeSpace);
    }

    /**
     * crete directory
     * 
     * @param directoryName
     * @return
     */
    public static boolean createDirectory(String directoryName)
    {
        boolean status;
        if (!directoryName.equals(""))
        {
            File path = Environment.getExternalStorageDirectory();
            File newPath = new File(path.toString() + directoryName);
            status = newPath.mkdir();
            status = true;
        }
        else
            status = false;
        return status;
    }

    /**
     * valiedate install
     * 
     * @return
     */
    public static boolean checkSaveLocationExists()
    {
        String sDCardStatus = Environment.getExternalStorageState();
        boolean status;
        if (sDCardStatus.equals(Environment.MEDIA_MOUNTED))
        {
            status = true;
        }
        else
            status = false;
        return status;
    }

    /**
     * delete directory
     * 
     * @param fileName
     * @return
     */
    public static boolean deleteDirectory(String fileName)
    {
        boolean status;
        SecurityManager checker = new SecurityManager();

        if (!fileName.equals(""))
        {

            File path = Environment.getExternalStorageDirectory();
            File newPath = new File(path.toString() + fileName);
            checker.checkDelete(newPath.toString());
            if (newPath.isDirectory())
            {
                String[] listfile = newPath.list();
                // delete all files within the specified directory and then
                // delete the directory
                try
                {
                    for (int i = 0; i < listfile.length; i++)
                    {
                        File deletedFile = new File(newPath.toString() + "/"
                                + listfile[i].toString());
                        deletedFile.delete();
                    }
                    newPath.delete();
                    status = true;
                } catch (Exception e)
                {
                    e.printStackTrace();
                    status = false;
                }

            }
            else
                status = false;
        }
        else
            status = false;
        return status;
    }

    /**
     * delete file special
     * 
     * @param file
     */
    public static void deleteDirectory(File file) {
        if (file.isFile()) {
            file.delete();
        } else {
            File[] subFiles = file.listFiles();
            for (File subFile : subFiles) {
                deleteDirectory(subFile);
            }
            // 閬垮厤鍒犻櫎鐩綍鍐嶆缂撳瓨鎵句笉鍒版枃浠跺す瀵艰嚧缂撳瓨涓嶆垚鍔�
            // file.delete();
        }
    }

    /**
     * delete file
     * 
     * @param fileName
     * @return
     */
    public static boolean deleteFile(String fileName)
    {
        boolean status;
        SecurityManager checker = new SecurityManager();

        if (!fileName.equals(""))
        {

            File path = Environment.getExternalStorageDirectory();
            File newPath = new File(path.toString() + fileName);
            checker.checkDelete(newPath.toString());
            if (newPath.isFile())
            {
                try
                {

                    newPath.delete();
                    status = true;
                } catch (SecurityException se)
                {
                    se.printStackTrace();
                    status = false;
                }
            }
            else
                status = false;
        }
        else
            status = false;
        return status;
    }

    /**
     * delete directory return 0 succssful ,1 not delte permission, 2 not empty dirctory,3 unkown errors
     * 
     * @return
     */
    public static int deleteBlankPath(String path)
    {
        File f = new File(path);
        if (!f.canWrite())
        {
            return 1;
        }
        if (f.list() != null && f.list().length > 0)
        {
            return 2;
        }
        if (f.delete())
        {
            return 0;
        }
        return 3;
    }

    /**
     * rename
     * 
     * @param oldName
     * @param newName
     * @return
     */
    public static boolean reNamePath(String oldName, String newName)
    {
        File f = new File(oldName);
        return f.renameTo(new File(newName));
    }

    /**
     * delete file
     * 
     * @param filePath
     */
    public static boolean deleteFileWithPath(String filePath)
    {
        SecurityManager checker = new SecurityManager();
        File f = new File(filePath);
        checker.checkDelete(filePath);
        if (f.isFile())
        {
            f.delete();
            return true;
        }
        return false;
    }

    /**
     * get sdk root diretory
     * 
     * @return
     */
    public static String getSDRoot()
    {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     * list root all directory
     * 
     * @param path
     * @return
     */
    public static List<String> listPath(String root)
    {
        List<String> allDir = new ArrayList<String>();
        SecurityManager checker = new SecurityManager();
        File path = new File(root);
        checker.checkRead(root);
        if (path.isDirectory())
        {
            for (File f : path.listFiles())
            {
                if (f.isDirectory())
                {
                    allDir.add(f.getAbsolutePath());
                }
            }
        }
        return allDir;
    }

    public enum PathStatus
    {
        SUCCESS, EXITS, ERROR
    }

    /**
     * create directory
     * 
     * @param path
     */
    public static PathStatus createPath(String newPath)
    {
        File path = new File(newPath);
        if (path.exists())
        {
            return PathStatus.EXITS;
        }
        if (path.mkdir())
        {
            return PathStatus.SUCCESS;
        }
        else
        {
            return PathStatus.ERROR;
        }
    }

    /**
     * sub path name
     * 
     * @return
     */
    public static String getPathName(String absolutePath)
    {
        int start = absolutePath.lastIndexOf(File.separator) + 1;
        int end = absolutePath.length();
        return absolutePath.substring(start, end);
    }

    /**
     * seirials
     * @return
     */
    public static boolean writeObjectToFile(File file, Object obj) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(obj);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     *seirials
     * 
     * @param file
     * @return
     */
    public static Object readObjectFromFile(File file) {
        if (file != null && file.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                Object obj = ois.readObject();
                ois.close();
                return obj;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
