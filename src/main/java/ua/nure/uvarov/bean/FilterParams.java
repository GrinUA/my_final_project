package ua.nure.uvarov.bean;

public class FilterParams {
    private String name;
    private String author;
    private String edition;
    private String publicationDate;

    private String genreId;

    public FilterParams(String name, String author, String edition, String publicationDate, String genreId) {
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.publicationDate = publicationDate;
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getEdition() {
        return edition;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getGenreId() {
        return genreId;
    }
}
