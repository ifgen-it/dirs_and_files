package com.evgen.service.impl;

import com.evgen.dao.DirectoryDAO;
import com.evgen.dto.DirFileDTO;
import com.evgen.dto.DirectoryDTO;
import com.evgen.dto.DirectoryExtDTO;
import com.evgen.entity.DirFileEntity;
import com.evgen.entity.DirectoryEntity;
import com.evgen.service.DirectoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DirectoryServiceImpl implements DirectoryService {

    @Autowired
    private DirectoryDAO directoryDAO;

    @Override
    public DirectoryDTO getByPath(String path) {

        DirectoryEntity directoryEntity = directoryDAO.getByPath(path);
        ModelMapper modelMapper = new ModelMapper();
        if (directoryEntity == null)
            return null;
        DirectoryDTO directoryDTO = modelMapper.map(directoryEntity, DirectoryDTO.class);

        return directoryDTO;
    }

    @Override
    public List<DirectoryExtDTO> getAllDirectoriesExt() {

        List<DirectoryExtDTO> dtos = new ArrayList<>();
        List<DirectoryEntity> entities = directoryDAO.getAllDirectories();

        for (DirectoryEntity entity : entities){
            DirectoryExtDTO dto = new DirectoryExtDTO();
            dto.setDirectoryId(entity.getDirectoryId());
            dto.setAdded(entity.getAdded());
            dto.setPath(entity.getPath());

            int numberOfDirectories = 0;
            int numberOfFiles = 0;
            Long sizeOfFiles = 0L;
            List<DirFileEntity> dirFileEntities = entity.getDirFiles();
            for (DirFileEntity dirFileEntity : dirFileEntities){
                if (dirFileEntity.isDirectory()){
                    numberOfDirectories++;
                }
                else {
                    numberOfFiles++;
                    sizeOfFiles += dirFileEntity.getSize();
                }
            }
            dto.setNumberOfDirectories(numberOfDirectories);
            dto.setNumberOfFiles(numberOfFiles);
            dto.setSizeOfFiles(sizeOfFiles);
            dtos.add(dto);
        }

        dtos.sort((o1, o2) -> o2.getAdded().compareTo(o1.getAdded()));

        return dtos;
    }

    @Override
    public int addDirectory(DirectoryDTO directory) {

        // GET FILES
        File dir = new File(directory.getPath());
        File[] files = dir.listFiles();

        // SAVE DIRECTORY
        ModelMapper modelMapper = new ModelMapper();
        DirectoryEntity directoryEntity = modelMapper.map(directory, DirectoryEntity.class);
        int directoryId = directoryDAO.addDirectory(directoryEntity);

        DirectoryDTO directoryDTOWithId = getDirectory(directoryId);
        DirectoryEntity directoryEntityWithId = modelMapper.map(directoryDTOWithId, DirectoryEntity.class);

        // SAVE FILES
        for (File file : files){
            DirFileDTO dirFile = new DirFileDTO();
            dirFile.setFileName(file.getName());
            dirFile.setDirectory(directoryDTOWithId);

            if (file.isDirectory()){
                dirFile.setIsDirectory(true);
            }
            else {
                dirFile.setIsDirectory(false);
                dirFile.setSize(file.length());
            }

            // MAPPING
            DirFileEntity dirFileEntity = new DirFileEntity();
            dirFileEntity.setFileName(dirFile.getFileName());
            dirFileEntity.setDirectory(directoryEntityWithId);
            dirFileEntity.setIsDirectory(dirFile.isDirectory());
            dirFileEntity.setSize(dirFile.getSize());
            directoryDAO.addFile(dirFileEntity);
        }

        return directoryId;
    }

    @Override
    public List<DirFileDTO> getFiles(int directoryId) {

        List<DirFileEntity> entities = directoryDAO.getFiles(directoryId);
        List<DirFileDTO> dtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        entities.forEach(item -> dtos.add(modelMapper.map(item, DirFileDTO.class)));

        return dtos;
    }

    @Override
    public DirectoryDTO getDirectory(int directoryId) {

        ModelMapper modelMapper = new ModelMapper();
        DirectoryEntity entity = directoryDAO.getDirectory(directoryId);
        if (entity == null)
            return null;

        return modelMapper.map(entity, DirectoryDTO.class);
    }

    @Override
    public int deleteDirectories() {

        return directoryDAO.deleteDirectories();
    }
}
