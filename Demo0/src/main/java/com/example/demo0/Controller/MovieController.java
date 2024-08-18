package com.example.demo0.Controller;


import com.example.demo0.Model.MovieModel;
import com.example.demo0.Repository.MovieRepo;
import com.example.demo0.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restApi")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @PostMapping("/post")
    public MovieModel post(@RequestBody MovieModel movieModel) {
        return movieService.AddMovie(movieModel);
    }

    @GetMapping("/get")
    public List<MovieModel> getAllMovies() {
        return movieService.getAllMovies();
    }


    @PutMapping("/Put/{id}")
    public MovieModel put(@PathVariable Long id, @RequestBody MovieModel movieModel) {
        return movieService.updateMovie(id, movieModel);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        movieService.deleteMovieModelById(id);
    }


}
