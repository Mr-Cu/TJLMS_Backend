package edu.tongji.tjlms.dto;

public class AddFileDto {
    String fileName;
    String extendFile;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getExtendFile() {
        return extendFile;
    }

    public void setExtendFile(String extendFile) {
        this.extendFile = extendFile;
    }

    public void addExtendFile(String str) {
        this.extendFile += str;
    }
}
