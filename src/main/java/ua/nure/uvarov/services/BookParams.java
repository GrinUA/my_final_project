package ua.nure.uvarov.services;



import java.util.Date;

public class BookParams {
    private String name;
    private String author;
    private String edition;
    private Date publicationDate;
    private String genre;
    private String orderBy;
    private boolean asc;

    @Override
    public String toString() {
        return "BookParams{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", edition='" + edition + '\'' +
                ", publicationDate=" + publicationDate +
                ", genre='" + genre + '\'' +
                ", orderBy='" + orderBy + '\'' +
                ", asc=" + asc +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }




}
