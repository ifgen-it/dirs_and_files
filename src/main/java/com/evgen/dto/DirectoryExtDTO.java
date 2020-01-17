package com.evgen.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DirectoryExtDTO implements Serializable {

    private int directoryId;

    private String path;

    private LocalDateTime added;

    private int numberOfDirectories;

    private int numberOfFiles;

    private Long sizeOfFiles;

    public DirectoryExtDTO() {
    }

    public int getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(int directoryId) {
        this.directoryId = directoryId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getAdded() {
        return added;
    }

    public void setAdded(LocalDateTime added) {
        this.added = added;
    }

    public int getNumberOfDirectories() {
        return numberOfDirectories;
    }

    public void setNumberOfDirectories(int numberOfDirectories) {
        this.numberOfDirectories = numberOfDirectories;
    }

    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }

    public Long getSizeOfFiles() {
        return sizeOfFiles;
    }

    public void setSizeOfFiles(Long sizeOfFiles) {
        this.sizeOfFiles = sizeOfFiles;
    }

    @Override
    public String toString() {
        return "DirectoryExtDTO{" +
                "directoryId=" + directoryId +
                ", path='" + path + '\'' +
                ", added=" + added +
                ", numberOfDirectories=" + numberOfDirectories +
                ", numberOfFiles=" + numberOfFiles +
                ", sizeOfFiles=" + sizeOfFiles +
                '}';
    }
}
