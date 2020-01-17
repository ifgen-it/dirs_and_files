package com.evgen.dao.impl;

import com.evgen.dao.DirectoryDAO;
import com.evgen.entity.DirFileEntity;
import com.evgen.entity.DirectoryEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional
public class DirectoryDAOImpl implements DirectoryDAO {

    @PersistenceContext(unitName = "emf")
    private EntityManager em;

    @Override
    public List<DirectoryEntity> getAllDirectories() {

        return em.createQuery("select d from DirectoryEntity d order by d.directoryId asc").getResultList();
    }

    @Override
    public DirectoryEntity getByPath(String path) {

        String queryString = "select d from DirectoryEntity d where d.path = :path";
        Query query = em.createQuery(queryString);
        query.setParameter("path", path);
        List<DirectoryEntity> dirs = (List<DirectoryEntity>) query.getResultList();

        if (dirs.size() == 0)
            return null;
        else
            return dirs.get(0);
    }

    @Override
    public List<DirFileEntity> getFiles(int directoryId) {

        String queryString = "select f from DirFileEntity f " +
                "where f.directory.directoryId = :dirId " +
                "order by f.isDirectory desc, f.fileName asc ";

        TypedQuery<DirFileEntity> query = em.createQuery(queryString, DirFileEntity.class);
        query.setParameter("dirId", directoryId);

        List<DirFileEntity> files = query.getResultList();
        return files;
    }

    @Override
    public DirectoryEntity getDirectory(int directoryId) {

        return em.find(DirectoryEntity.class, directoryId);
    }

    @Override
    public int addDirectory(DirectoryEntity directory) {

        em.persist(directory);
        return directory.getDirectoryId();
    }

    @Override
    public int addFile(DirFileEntity file) {

        em.persist(file);
        return file.getFileId();
    }

    @Override
    public int deleteDirectories() {

        String queryString = "delete from DirectoryEntity";
        Query query = em.createQuery(queryString);
        return query.executeUpdate();
    }
}
