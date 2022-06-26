package com.denis.http.repository.hibernate;

import com.denis.http.model.FileModel;
import com.denis.http.model.History;
import com.denis.http.repository.HistoryRepository;
import com.denis.http.utils.HibernateUtilities;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class HibernateHistoryRepositoryImpl implements HistoryRepository
{
    public History findById(Integer id)
    {
        try (Session session = HibernateUtilities.getSession())
        {
            History history = session.get(History.class, id);
            history.getFiles().size();
            return history;
        }
    }

    public History save(History history)
    {
        try (Session session = HibernateUtilities.getSession())
        {
            Transaction tx1 = session.beginTransaction();
            session.save(history);
            tx1.commit();
            return findById(history.getId());
        }
    }

    public History update(History history)
    {
        try (Session session = HibernateUtilities.getSession())
        {
            List<FileModel> newFiles = history.getFiles();

            Transaction tx1 = session.beginTransaction();
            History result = session.get(History.class, history.getId());
            List<FileModel> files = result.getFiles();
            for(int i = 0; i < newFiles.size(); i++)
            {
                FileModel file = newFiles.get(i);
                files.add(file);
            }
            result.setFiles(files);
            session.update(result);
            tx1.commit();
            return findById(history.getId());
        }
    }

    public void delete(History history)
    {
        try (Session session = HibernateUtilities.getSession())
        {
            Transaction tx1 = session.beginTransaction();
            session.delete(history);
            tx1.commit();
        }
    }

    public List<History> findAll()
    {
        try (Session session = HibernateUtilities.getSession())
        {
            List<History> histories = session.createQuery("from History").list();
            for(int i = 0; i < histories.size(); i++)
            {
                History history = histories.get(i);
                history.getFiles().size();
            }
            return histories;
        }
    }
}
