package com.example.demo0.Service;

import com.example.demo0.Model.MovieModel;
import com.example.demo0.Repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepo movieRepo;

    //Post
    public MovieModel AddMovie(MovieModel movieModel) {
        return movieRepo.save(movieModel);
    }

    //get
    public List<MovieModel> getAllMovies() {
        return movieRepo.findAll();
    }


    //update
    public MovieModel updateMovie(Long id, MovieModel updatedmovieModel) {
        MovieModel existmovieModel = movieRepo.findById(id).
                orElseThrow(() -> new RuntimeException("Movie Not Found"));
        existmovieModel.setTitle(updatedmovieModel.getTitle());
        existmovieModel.setYear(updatedmovieModel.getYear());
        return movieRepo.save(existmovieModel);
    }

    public void deleteMovieModelById(Long id) {
        movieRepo.deleteById(id);
//
    }

}
