package com.example.demo0.Service;

import com.example.demo0.Exception.ErrorHandler;
import com.example.demo0.Model.MovieModel;
import com.example.demo0.Model.MovieResponse;
import com.example.demo0.Repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepo movieRepo;

    // Post
    public MovieResponse<MovieModel> addMovie(MovieModel movieModel) {
        MovieModel savedMovie = movieRepo.save(movieModel);
        return new MovieResponse<>(savedMovie, "Movie added successfully", "success");
    }


    //get
    public MovieResponse<List<MovieModel>> getMovie() {
        List<MovieModel> getMovies = movieRepo.findAll();
        return new MovieResponse<>(getMovies, "Movie retrieved successfully", "success");
    }

    //get by id
    public MovieResponse<MovieModel> getMovieById(Long id) {
        MovieModel getMovieById = movieRepo.findById(id).orElseThrow(() -> new ErrorHandler("id not found"));
        return new MovieResponse<>(getMovieById, "Movie retrieved successfully", "success");
    }

    //get by title
    public MovieResponse<List<MovieModel>> getByTitle(String title) {
        List<MovieModel> getMovieByTitle = movieRepo.findByTitle(title);
        if (getMovieByTitle.isEmpty()) {
            throw new ErrorHandler("movie not found in this title");}
        return new MovieResponse<>(getMovieByTitle, "Movie retrieved successfully", "success");}


    //update
    public MovieResponse<MovieModel> updateMovie(Long id, MovieModel updatedmovieModel) {
        MovieModel existmovieModel = movieRepo.findById(id).
                orElseThrow(() -> new ErrorHandler("Movie Not Found"));
        existmovieModel.setTitle(updatedmovieModel.getTitle());
        existmovieModel.setYear(updatedmovieModel.getYear());
        MovieModel savedMovie = movieRepo.save(existmovieModel);
        return new MovieResponse<>(existmovieModel, "Movie updated successfully", "success");
    }

    public MovieResponse<Void> deleteMovieModelById(Long id) {
        if (movieRepo.existsById(id)) {
            movieRepo.deleteById(id);
            return new MovieResponse<>(null, "Movie deleted successfully", "success");
        } else {
            throw new ErrorHandler("id not found");
        }
    }


}
