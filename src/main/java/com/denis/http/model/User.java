package com.denis.http.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private History history;

    public User()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public History getHistory()
    {
        return history;
    }

    public void setHistory(History history)
    {
        this.history = history;
    }

    /**
     * toString method (optional)
     */
    @Override
    public String toString()
    {
        String historyResult = "";
        if(history != null)
        {
            historyResult = history.toString();
        }
        return "Id: " + id + " ; Name: " + name + " ; History: \n{" + historyResult + "}";
    }
}