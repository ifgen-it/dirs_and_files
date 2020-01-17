package com.evgen.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dir_file")
public class DirFileEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id", nullable = false)
    private int fileId;

    @Column(name = "file_name", length = 300, nullable = false)
    private String fileName;

    @Column(name = "is_directory", nullable = false)
    private boolean isDirectory;

    @Column(name = "size")
    private Long size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directory_id", nullable = false)
    private DirectoryEntity directory;

    public DirFileEntity() {
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

    public DirectoryEntity getDirectory() {
        return directory;
    }

    public void setDirectory(DirectoryEntity directory) {
        this.directory = directory;
    }

    @Override
    public String toString() {
        return "DirFileEntity{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", isDirectory=" + isDirectory +
                ", size=" + size +
                ", directory=" + directory.getPath() +
                '}';
    }
}
