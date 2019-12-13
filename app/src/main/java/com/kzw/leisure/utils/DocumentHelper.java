package com.kzw.leisure.utils;

import android.graphics.Bitmap;
import android.net.Uri;

import com.kzw.leisure.base.BaseApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.documentfile.provider.DocumentFile;


/**
 * Created by PureDark on 2016/9/24.
 */

public class DocumentHelper {

    public static boolean isFileExist(String fileName, String rootPath, String... subDirs) {
        return DocumentUtil.isFileExist(BaseApplication.getInstance(), fileName, rootPath, subDirs);
    }

    public static DocumentFile getDirDocument(String rootPath, String... subDirs) {
        return DocumentUtil.getDirDocument(BaseApplication.getInstance(), rootPath, subDirs);
    }

    public static DocumentFile createFileIfNotExist(String fileName, String path, String... subDirs) {
        if (!path.startsWith("content://"))
            path = "file://" + Uri.decode(path);
        return DocumentUtil.createFileIfNotExist(BaseApplication.getInstance(), fileName, path, subDirs);
    }

    public static DocumentFile createDirIfNotExist(String path, String... subDirs) {
        if (!path.startsWith("content://"))
            path = "file://" + Uri.decode(path);
        return DocumentUtil.createDirIfNotExist(BaseApplication.getInstance(), path, subDirs);
    }

    public static boolean deleteFile(String fileName, String rootPath, String... subDirs) {
        if (!rootPath.startsWith("content://"))
            rootPath = "file://" + Uri.decode(rootPath);
        return DocumentUtil.deleteFile(BaseApplication.getInstance(), fileName, rootPath, subDirs);
    }

    public static boolean writeString(String string, DocumentFile file) {
        return DocumentUtil.writeBytes(BaseApplication.getInstance(), string.getBytes(), file);
    }

    public static boolean writeString(String string, String fileName, String rootPath, String... subDirs) {
        if (!rootPath.startsWith("content://"))
            rootPath = "file://" + Uri.decode(rootPath);
        return DocumentUtil.writeBytes(BaseApplication.getInstance(), string.getBytes(), fileName, rootPath, subDirs);
    }

    public static String readString(String fileName, String rootPath, String... subDirs) {
        byte[] data = DocumentUtil.readBytes(BaseApplication.getInstance(), fileName, rootPath, subDirs);
        String string = null;
        try {
            string = new String(data, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static String readString(Uri uri) {
        byte[] data = DocumentUtil.readBytes(BaseApplication.getInstance(), uri);
        String string = null;
        try {
            string = new String(data, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static String readString(DocumentFile file) {
        byte[] data = DocumentUtil.readBytes(BaseApplication.getInstance(), file);
        String string = null;
        try {
            string = new String(data, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static boolean writeBytes(byte[] data, String fileName, String rootPath, String... subDirs) {
        if (!rootPath.startsWith("content://"))
            rootPath = "file://" + Uri.decode(rootPath);
        return DocumentUtil.writeBytes(BaseApplication.getInstance(), data, fileName, rootPath, subDirs);
    }

    public static boolean writeBytes(byte[] data, DocumentFile file) {
        if (file == null)
            return false;
        return DocumentUtil.writeBytes(BaseApplication.getInstance(), data, file);
    }

    public static boolean writeFromFile(File fromFile, DocumentFile file) {
        if (file == null)
            return false;
        try {
            return DocumentUtil.writeFromInputStream(BaseApplication.getInstance(), new FileInputStream(fromFile), file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writeFromInputStream(InputStream inStream, DocumentFile file) {
        if (file == null)
            return false;
        return DocumentUtil.writeFromInputStream(BaseApplication.getInstance(), inStream, file);
    }

    public static void saveBitmapToFile(Bitmap bitmap, DocumentFile file) throws IOException {
        saveBitmapToFile(bitmap, file.getUri());
    }

    public static void saveBitmapToFile(Bitmap bitmap, Uri fileUri) throws IOException {
        OutputStream out = BaseApplication.getInstance().getContentResolver().openOutputStream(fileUri);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        assert out != null;
        out.flush();
        out.close();
    }

    public static OutputStream getFileOutputSteam(String fileName, String rootPath, String... subDirs) {
        if (!rootPath.startsWith("content://"))
            rootPath = "file://" + Uri.decode(rootPath);
        return DocumentUtil.getFileOutputSteam(BaseApplication.getInstance(), fileName, rootPath, subDirs);
    }

    public static InputStream getFileInputSteam(String fileName, String rootPath, String... subDirs) {
        if (!rootPath.startsWith("content://"))
            rootPath = "file://" + Uri.decode(rootPath);
        return DocumentUtil.getFileInputSteam(BaseApplication.getInstance(), fileName, rootPath, subDirs);
    }

    public static String filenameFilter(String str) {
        return DocumentUtil.filenameFilter(str);
    }

    public static byte[] getBytes(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

}
