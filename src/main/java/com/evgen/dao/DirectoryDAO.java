package com.evgen.dao;

import com.evgen.entity.DirFileEntity;
import com.evgen.entity.DirectoryEntity;

import java.util.List;

public interface DirectoryDAO {

    List<DirectoryEntity> getAllDirectories();

    DirectoryEntity getByPath(String path);

    List<DirFileEntity> getFiles(int directoryId);

    DirectoryEntity getDirectory(int directoryId);

    int addDirectory(DirectoryEntity directory);

    int addFile(DirFileEntity file);

    int deleteDirectories();
}
