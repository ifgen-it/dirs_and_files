package com.evgen.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "directory")
public class DirectoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "directory_id", nullable = false)
    private int directoryId;

    @Column(name = "path", length = 400, nullable = false)
    private String path;

    @Column(name = "added", nullable = false)
    private LocalDateTime added;

    @OneToMany(mappedBy = "directory", fetch = FetchType.LAZY)
    private List<DirFileEntity> dirFiles = new ArrayList<>();

    public DirectoryEntity() {
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

    public List<DirFileEntity> getDirFiles() {
        return dirFiles;
    }

    public void setDirFiles(List<DirFileEntity> dirFiles) {
        this.dirFiles = dirFiles;
    }

    @Override
    public String toString() {
        return "DirectoryEntity{" +
                "directoryId=" + directoryId +
                ", path='" + path + '\'' +
                ", added=" + added +
                ", dirFiles=" + dirFiles +
                '}';
    }
}
