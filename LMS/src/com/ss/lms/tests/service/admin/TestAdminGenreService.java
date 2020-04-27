package com.ss.lms.tests.service.admin;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import com.ss.lms.models.Genre;
import com.ss.lms.service.admin.AdminGenreService;

import org.junit.Test;

public class TestAdminGenreService {
    @Test
    public void addAGenreTest() throws SQLException {
        AdminGenreService aGenServ = new AdminGenreService();
        Genre genre = new Genre();
        genre.setGenreName("Test Genre");
        Integer primaryKey = aGenServ.addAGenre(genre);
        System.out.println(primaryKey);

        assertEquals(aGenServ.readAGenre(primaryKey).getGenreName().equals("Test Genre"), true);
    }

    @Test
    public void updateAGenreTest() throws SQLException {
        AdminGenreService aGenServ = new AdminGenreService();
        Genre genre = new Genre();
        genre.setGenreName("Test Genre");
        Integer primaryKey = aGenServ.addAGenre(genre);

        Genre updateBor = new Genre(primaryKey, "Updated Test Genre");
        aGenServ.updateAGenre(updateBor);

        assertEquals(aGenServ.readAGenre(primaryKey).getGenreName().equals("Updated Test Genre"), true);
    }

    @Test
    public void deleteAGenreTest() throws SQLException {
        AdminGenreService aGenServ = new AdminGenreService();
        Genre genre = new Genre();
        genre.setGenreName("Test Genre");
        Integer primaryKey = aGenServ.addAGenre(genre);
        genre.setGenreID(primaryKey);
        assertEquals(aGenServ.readAGenre(primaryKey).getGenreName().equals("Test Genre"), true);

        aGenServ.deleteAGenre(genre);
        Genre deletedGenre = aGenServ.readAGenre(primaryKey);

        assertEquals(deletedGenre == null, true);
    }

    @Test
    public void readAGenreTest() throws SQLException {
        AdminGenreService aGenServ = new AdminGenreService();
        Genre genre = aGenServ.readAGenre(1);

        assertEquals(genre.getGenreName().equals("Adventure"), true);
    }

    @Test
    public void readAllGenresTest() throws SQLException {
        AdminGenreService aGenServ = new AdminGenreService();
        List<Genre> genres = aGenServ.readAllGenres();

        assertEquals(genres.get(0) instanceof Genre, true);
    }
}