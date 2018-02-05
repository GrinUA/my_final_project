package ua.nure.uvarov.bean;

public class FilterParams {
    private String name;
    private String author;
    private String edition;
    private String publicationDate;
    private String orderBy;
    private String sortBy;

    private String genreId;

    public FilterParams(String name, String author, String edition, String publicationDate, String orderBy, String sortBy, String genreId) {
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.publicationDate = publicationDate;
        this.orderBy = orderBy;
        this.sortBy = sortBy;
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

    public String getOrderBy() {
        return orderBy;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getGenreId() {
        return genreId;
    }
}
