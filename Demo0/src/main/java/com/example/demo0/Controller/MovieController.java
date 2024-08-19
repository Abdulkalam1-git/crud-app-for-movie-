package com.example.demo0.Controller;


import com.example.demo0.Model.MovieModel;
import com.example.demo0.Model.MovieResponse;
import com.example.demo0.Repository.MovieRepo;
import com.example.demo0.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restApi")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/post")
    public ResponseEntity<MovieResponse<MovieModel>> AddMovie(@RequestBody MovieModel movieModel) {
        MovieResponse<MovieModel> response = movieService.addMovie(movieModel);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<MovieResponse<List<MovieModel>>> getMovies() {
        MovieResponse<List<MovieModel>> response = movieService.getMovie();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/get/id/{id}")
    public ResponseEntity<MovieResponse<MovieModel>> getMovie( @PathVariable Long id  ) {
        MovieResponse<MovieModel> response = movieService.getMovieById(id);
        return  ResponseEntity.ok(response);
    }
    @GetMapping("/get/title/{title}")
    public ResponseEntity<MovieResponse<List<MovieModel>>> getMovie(@PathVariable String title) {
        MovieResponse<List<MovieModel>> response = movieService.getByTitle(title);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/Put/{id}")
    public ResponseEntity<MovieResponse<MovieModel>> updateMovie(@PathVariable Long id, @RequestBody MovieModel movieModel) {
        MovieResponse<MovieModel> response = movieService.updateMovie(id, movieModel);
        return ResponseEntity.ok(response);
    }

//    @DeleteMapping("delete/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        movieService.deleteMovieModelById(id);
//        return ResponseEntity.ok().build();
//
//
//    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MovieResponse<Void>> delete(@PathVariable Long id) {
        MovieResponse<Void> response = movieService.deleteMovieModelById(id);
        return ResponseEntity.ok(response);
    }

}
