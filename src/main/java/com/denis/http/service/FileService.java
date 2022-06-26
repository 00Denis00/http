package com.denis.http.service;

import com.denis.http.model.FileModel;
import com.denis.http.repository.FileRepository;
import com.denis.http.repository.hibernate.HibernateFileRepositoryImpl;

import java.util.List;

public class FileService
{
    FileRepository fileRepository = new HibernateFileRepositoryImpl(){};
    public static final String directoryPath = "src\\main\\resources\\uploads\\";

    public FileModel findById(Integer id)
    {
        return fileRepository.findById(id);
    }
    public void delete(FileModel file)
    {
        fileRepository.delete(file);
    }
    public List<FileModel> findAll()
    {
        return fileRepository.findAll();
    }
    public FileModel save(FileModel file)
    {
        return fileRepository.save(file);
    }
    public FileModel update(FileModel file)
    {
        return fileRepository.update(file);
    }
}
