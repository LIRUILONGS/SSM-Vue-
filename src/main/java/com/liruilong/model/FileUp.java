package com.liruilong.model;



/**
 * @Description :
 * @Author: Liruilong
 * @Date: 2020/3/2 19:25
 */
public class FileUp {
    private int fileld;
    private String fileName;
    private String filePath;
    private String fileSize;
    private String fileType;
    private String fileDate;

    public int getFileld() {
        return fileld;
    }

    public void setFileld(int fileld) {
        this.fileld = fileld;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }

    public FileUp() {
    }

    public FileUp(String fileName, String filePath, String fileSize, String fileType, String fileDate) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.fileDate = fileDate;
    }

    @Override
    public String toString() {
        return "FileUp{" +
                "fileld=" + fileld +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileDate='" + fileDate + '\'' +
                '}';
    }
}
