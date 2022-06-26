package com.denis.http.repository.hibernate;

import com.denis.http.model.FileModel;
import com.denis.http.repository.FileRepository;
import com.denis.http.utils.HibernateUtilities;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateFileRepositoryImpl implements FileRepository
{
    public FileModel findById(Integer id)
    {
        try (Session session = HibernateUtilities.getSession())
        {
            return session.get(FileModel.class, id);
        }
    }

    public FileModel save(FileModel file)
    {
        try (Session session = HibernateUtilities.getSession())
        {
            Transaction tx1 = session.beginTransaction();
            session.save(file);
            tx1.commit();
            return file;
        }
    }

    public FileModel update(FileModel file)
    {
        try (Session session = HibernateUtilities.getSession())
        {
            Transaction tx1 = session.beginTransaction();
            session.update(file);
            tx1.commit();
            return file;
        }
    }

    public void delete(FileModel file)
    {
        try (Session session = HibernateUtilities.getSession())
        {
            Transaction tx1 = session.beginTransaction();
            session.delete(file);
            tx1.commit();
        }
    }

    public List<FileModel> findAll()
    {
        try (Session session = HibernateUtilities.getSession())
        {
            return session.createQuery("from FileModel").list();
        }
    }
}
