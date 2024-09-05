package com.example.demo0.Service;

import com.example.demo0.Exception.ErrorHandler;
import com.example.demo0.Model.MovieModel;
import com.example.demo0.Model.MovieResponse;
import com.example.demo0.Repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepo movieRepo;

    private static final String PREFIX ="DEMO";
          public String generateUniqueId() {
        // checking in db
        String lastUniqueId = movieRepo.findMaxUniqueId();
        // Using ternary operator
        int newIdValue = (lastUniqueId == null) ? 1  // If lastUniqueId is null, start with 1
                : Integer.parseInt(lastUniqueId.replace(PREFIX, "")) + 1;

        // Return new uniqueID
        return PREFIX + String.format("%04d", newIdValue);
    }

    // Normalize the title
    public String normalizeTitle(String title) {
        return title.replaceAll("\\s", "").toLowerCase();
    }

    // Validate mobile number
    public static boolean isValidMobileNumber(String mobileNumber) {
        // Regular expression to match a 10-digit number starting with 6
        String regex = "6\\d{9}";
        return mobileNumber.matches(regex);
    }
    //public

    // Add Movie
    public MovieResponse<MovieModel> addMovie(MovieModel movieModel) {
        // Normalize the title
        String normalizedTitle = normalizeTitle(movieModel.getTitle());

        // Check if a movie with the same normalized title exists
        Optional<MovieModel> existingMovie = movieRepo.findAll().stream()
                .filter(movie -> normalizeTitle(movie.getTitle()).equals(normalizedTitle))
                .findFirst();

        if (existingMovie.isPresent()) {
            // Throw an error if a movie with the same title already exists
            throw new ErrorHandler("Movie with the same title already exists");
        }


        // Validate the mobile number (assuming it's part of the MovieModel)
        String mobileNumber = movieModel.getMobilenumber(); // Assuming movieModel has a mobileNumber field

        if (!isValidMobileNumber(mobileNumber)) {
            throw new ErrorHandler("Invalid mobile number. It should start with 6 and have 10 digits.");
        }

        // Set unique ID for the movie
        movieModel.setUniqueId(generateUniqueId());
        movieModel.setMobilenumber(isValidMobileNumber(mobileNumber) ? mobileNumber : null);

        // Save the movie to the database
        MovieModel savedMovie = movieRepo.save(movieModel);

        // Return the response
        return new MovieResponse<>(savedMovie, "Movie added successfully", "success");
    }

    // Get all movies
    public MovieResponse<List<MovieModel>> getMovie() {
        List<MovieModel> getMovies = movieRepo.findAll();
        return new MovieResponse<>(getMovies, "Movies retrieved successfully", "success");
    }

    // Get movie by ID
    public MovieResponse<MovieModel> getMovieById(Long id) {
        MovieModel getMovieById = movieRepo.findById(id).orElseThrow(() -> new ErrorHandler("ID not found"));
        return new MovieResponse<>(getMovieById, "Movie retrieved successfully", "success");
    }
     //update by id 
    public MovieResponse<MovieModel> updateMovie(Long id, MovieModel updatedmovieModel) {
        MovieModel existmovieModel = movieRepo.findById(id).
                orElseThrow(() -> new ErrorHandler("Movie Not Found"));
        existmovieModel.setTitle(updatedmovieModel.getTitle());
        existmovieModel.setYear(updatedmovieModel.getYear());
        MovieModel savedMovie = movieRepo.save(existmovieModel);
        return new MovieResponse<>(existmovieModel, "Movie updated successfully", "success");
    }
}
