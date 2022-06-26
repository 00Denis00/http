package com.denis.http.repository.hibernate;

import com.denis.http.model.History;
import com.denis.http.model.User;
import com.denis.http.repository.UserRepository;
import com.denis.http.utils.HibernateUtilities;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class HibernateUserRepositoryImpl implements UserRepository
{
    public User findById(Integer id)
    {
        try (Session session = HibernateUtilities.getSession())
        {
            User user = session.get(User.class, id);
            if(user.getHistory() != null)
            {
                user.getHistory().getFiles().size();
            }
            return user;
        }
    }

    public User save(User user)
    {
        try (Session session = HibernateUtilities.getSession())
        {
            Transaction tx1 = session.beginTransaction();
            session.save(user);
            tx1.commit();
            return findById(user.getId());
        }
    }

    public User update(User user)
    {
        try (Session session = HibernateUtilities.getSession())
        {
            Transaction tx1 = session.beginTransaction();
            session.update(user);
            tx1.commit();
            return findById(user.getId());
        }
    }

    public void delete(User user)
    {
        try (Session session = HibernateUtilities.getSession())
        {
            User result = findById(user.getId());
            History history = result.getHistory();

            Transaction tx1 = session.beginTransaction();
            session.delete(user);
            session.delete(history);
            tx1.commit();
        }
    }

    public List<User> findAll()
    {
        try (Session session = HibernateUtilities.getSession())
        {
            List<User> users = session.createQuery("from User").list();
            for(int i = 0; i < users.size(); i++)
            {
                User user = users.get(i);
                if(user.getHistory() != null)
                {
                    user.getHistory().getFiles().size();
                }
            }
            return users;
        }
    }
}
