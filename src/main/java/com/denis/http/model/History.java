package com.denis.http.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "histories")
public class History
{
    @Id
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<FileModel> files;

    public History()
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

    public List<FileModel> getFiles()
    {
        return files;
    }

    public void setFiles(List<FileModel> files)
    {
        this.files = files;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    /**
     * toString method (optional)
     */
    @Override
    public String toString()
    {
        String filesResult = "";
        for(int i = 0; i < files.size(); i++)
        {
            FileModel file = files.get(i);
            String result = "(" + file.toString() + ")";
            filesResult += result;
        }
        return "Id: " + id + " ; Files: [" + filesResult + "]";
    }
}
