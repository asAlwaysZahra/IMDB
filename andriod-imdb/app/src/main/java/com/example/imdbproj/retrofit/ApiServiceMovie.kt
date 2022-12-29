package com.example.imdbproj.retrofit

import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.classes.mainClasses.Person
import com.example.imdbproj_1.mainClasses.Rating
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiServiceMovie {

    @GET("/movies")
    fun getMovies(): Call<List<Movie>>

    @GET("/movies/{titleId}")
    fun getMovie(@Body titleId: String): Call<Movie>

    @GET("/movies/{titleId}/directors")
    fun getDirectors(@Body titleId: String): Call<List<Person>>

    @GET("/movies/{titleId/actors")
    fun getActors(@Body titleId: String): Call<List<Person>>

    @GET("/movies/{titleId}/ratings")
    fun getRating(@Body titleId: String): Call<Rating>


    @POST("/movies")
    fun addMovie(@Body movie: Movie): Call<Movie>

    @PUT("/movies/{titleId}")
    fun updateMovie(@Body titleId: String): Call<Movie>

    @DELETE("/movies/{titleId}")
    fun deleteMovie(@Body titleId: String): Call<Movie>


}