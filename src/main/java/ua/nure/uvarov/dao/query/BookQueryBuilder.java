package ua.nure.uvarov.dao.query;

import ua.nure.uvarov.bean.FilterParams;
import ua.nure.uvarov.constants.MySQL;

public class BookQueryBuilder {
    private FilterParams params;
    private String query;

    public void setParams(FilterParams params) {
        this.params = params;
    }

    public void build() {
        StringBuilder sb = new StringBuilder(MySQL.FIND_ALL_BOOK_GROUP);
        if (params != null) {
            boolean updated = false;
            if ((params.getName() != null && !params.getName().isEmpty())
                    || (params.getAuthor() != null && !params.getAuthor().isEmpty())
                    || (params.getEdition() != null && !params.getEdition().isEmpty())
                    ||(params.getPublicationDate() != null && !params.getPublicationDate().isEmpty())
                    || (params.getGenreId()!=null && !params.getGenreId().isEmpty())) {
                sb.append(" WHERE ");
            }
            if (params.getName() != null && !params.getName().isEmpty()) {
                if (updated) {
                    sb.append(" AND ");
                }
                updated = true;
                sb.append(" ( ").append(" name LIKE '%").append(params.getName()).append("%' )");
            }
            if (params.getAuthor() != null && !params.getAuthor().isEmpty()) {
                if (updated) {
                    sb.append(" AND ");
                }
                updated = true;
                sb.append(" ( ").append(" author LIKE '%").append(params.getAuthor()).append("%' )");
            }
            if (params.getEdition() != null && !params.getEdition().isEmpty()) {
                if (updated) {
                    sb.append(" AND ");
                }
                updated = true;
                sb.append(" ( ").append(" edition LIKE '%").append(params.getEdition()).append("%' )");
            }
            if (params.getPublicationDate() != null && !params.getPublicationDate().isEmpty()) {
                if (updated) {
                    sb.append(" AND ");
                }
                updated = true;
                sb.append(" ( ").append(" publicationDate='").append(params.getPublicationDate()).append("' )");
            }
            if (params.getGenreId() != null && !params.getGenreId().isEmpty()) {
                if (updated) {
                    sb.append(" AND ");
                }
                updated = true;
                sb.append(" ( ").append(" genreId=").append(params.getGenreId()).append(" )");
            }

           /* if (params.getOrderBy() != null && !params.getOrderBy().isEmpty()) {
                if (!params.isAsc()) {
                    sb.append(" ORDER BY ").append(params.getOrderBy()).append(" ASC ");
                } else {
                    sb.append(" ORDER BY ").append(params.getOrderBy()).append(" DESC ");
                }
            }*/
        }
        query = sb.toString();
    }

    @Override
    public String toString() {
        return query;
    }
}
