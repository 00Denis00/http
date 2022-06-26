package com.denis.http.model;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class FileModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    public FileModel()
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

    /**
     * toString method (optional)
     */
    @Override
    public String toString()
    {
        return "Id: " + id + " ; Name: " + name;
    }
}
