package com.evgen.dto;

import java.io.Serializable;

public class DirFileDTO implements Serializable {

    private int fileId;

    private String fileName;

    private boolean isDirectory;

    private Long size;

    private DirectoryDTO directory;

    public DirFileDTO() {
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setIsDirectory(boolean directory) {
        isDirectory = directory;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public DirectoryDTO getDirectory() {
        return directory;
    }

    public void setDirectory(DirectoryDTO directory) {
        this.directory = directory;
    }

    @Override
    public String toString() {
        return "DirFileDTO{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", isDirectory=" + isDirectory +
                ", size=" + size +
                ", directory=" + directory.getPath() +
                '}';
    }
}
