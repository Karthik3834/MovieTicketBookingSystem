package com.ey.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long movieId;
	
	private String title;
	private String language;
	private Integer durationMins;
	private String genre;
	private String certificate;
	private LocalDate releaseDate;
	private Boolean active;
	public Movie() {
		super();
	}
	public Movie(Long movieId, String title, String language, Integer durationMins, String genre, String certificate,
			LocalDate releaseDate, Boolean active) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.language = language;
		this.durationMins = durationMins;
		this.genre = genre;
		this.certificate = certificate;
		this.releaseDate = releaseDate;
		this.active = active;
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Integer getDurationMins() {
		return durationMins;
	}
	public void setDurationMins(Integer durationMins) {
		this.durationMins = durationMins;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	
	

}
