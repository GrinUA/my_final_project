package ua.nure.uvarov.entity;

public class Genre {
    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    private int id;
    private String name;

    public Genre(int id) {
        this.id = id;
    }
    public Genre(String name) {
        this.name = name;
    }

    public Genre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
