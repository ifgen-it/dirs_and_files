package com.evgen.service;

import com.evgen.dto.DirFileDTO;
import com.evgen.dto.DirectoryDTO;
import com.evgen.dto.DirectoryExtDTO;

import java.util.List;

public interface DirectoryService {

    DirectoryDTO getByPath(String path);

    List<DirectoryExtDTO> getAllDirectoriesExt();

    int addDirectory(DirectoryDTO directory);

    List<DirFileDTO> getFiles(int directoryId);

    DirectoryDTO getDirectory(int directoryId);

    int deleteDirectories();
}
