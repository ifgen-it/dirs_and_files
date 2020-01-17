package com.evgen.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DirectoryDTO implements Serializable {

    private int directoryId;

    private String path;

    private LocalDateTime added;

    public DirectoryDTO() {
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

    @Override
    public String toString() {
        return "DirectoryDTO{" +
                "directoryId=" + directoryId +
                ", path='" + path + '\'' +
                ", added=" + added +
                '}';
    }
}
