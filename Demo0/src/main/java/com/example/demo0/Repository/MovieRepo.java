package com.example.demo0.Repository;

import com.example.demo0.Model.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<MovieModel,Long> {
  //for getting a details by title
List<MovieModel> findByTitle(String title) ;
List<MovieModel> findByYear(String year) ;
}
