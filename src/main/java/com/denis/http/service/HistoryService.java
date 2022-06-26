package com.denis.http.service;

import com.denis.http.model.History;
import com.denis.http.repository.HistoryRepository;
import com.denis.http.repository.hibernate.HibernateHistoryRepositoryImpl;

import java.util.List;

public class HistoryService
{
    HistoryRepository historyRepository = new HibernateHistoryRepositoryImpl(){};

    public History findById(Integer id)
    {
        return historyRepository.findById(id);
    }
    public void delete(History history)
    {
        historyRepository.delete(history);
    }
    public List<History> findAll()
    {
        return historyRepository.findAll();
    }
    public History save(History history)
    {
        return historyRepository.save(history);
    }
    public History update(History history)
    {
        return historyRepository.update(history);
    }
}
