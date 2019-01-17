package com.thymeleaf.thymeleaf.utils;

import org.devlib.schmidt.imageinfo.ImageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtil {
    /**
     * 判断文件是否为真实的图片
     * @param filePath
     * @return
     * @throws Exception
     */
    public static boolean imgCheck(String filePath) throws Exception{
        ImageInfo ii = new ImageInfo();
        RandomAccessFile is = new RandomAccessFile(filePath,"r");
        try {
            ii.setInput(is);
            ii.setDetermineImageNumber(true);
            ii.setCollectComments(true);
            if(ii.check()){
                return true;
            }else {
                return false;
            }
        }finally {
            if(is != null){
                is.close();
            }
        }
    }

    public static boolean imgCheck(File file) throws Exception{
        ImageInfo ii = new ImageInfo();
        RandomAccessFile is = new RandomAccessFile(file,"r");
        try {
            ii.setInput(is);
            ii.setDetermineImageNumber(true);
            ii.setCollectComments(true);
            if(ii.check()){
                return true;
            }
            return false;
        }finally {
            if(is != null){
                is.close();
            }
        }
    }

    /**
     * MultipartFile转File
     * @param mFile
     * @return
     */
    public static File multipartFileToFile(MultipartFile mFile) throws IOException{
        File file = null;
        if(mFile == null || mFile.equals("")||mFile.getSize() <= 0){
            file = null;
        }else {
            InputStream is = mFile.getInputStream();
            file = new File(mFile.getOriginalFilename());
            inputStreamToFile(is,file);
        }
        return file;
    }
    /**
     * InputStream转File
     * @param is
     * @return
     */
    public static void inputStreamToFile(InputStream is,File file) throws FileNotFoundException,IOException{
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = is.read(buffer,0,8192)) != -1){
                os.write(buffer,0,bytesRead);
            }
        }finally {
            if (os != null){
                os.close();
            }
            if(is != null){
                is.close();
            }
        }
    }
}
