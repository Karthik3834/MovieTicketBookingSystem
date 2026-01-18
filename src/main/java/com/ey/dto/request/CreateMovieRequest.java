package com.ey.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateMovieRequest {
	
	
	@NotBlank(message = "Movie Title Cannot Be Blank")
	private String title;
	
	@NotBlank(message = "Language cannot be blank")
	private String language;
	
	@NotNull(message = "Duration is rerquired")
	@Positive(message = "Mins must be Positive")
	private Integer durationMins;
	
	@NotBlank(message = "Genre is required")
	private String genre;
	
	@NotBlank(message = "Certificate is required")
	private String certificate;
	
	@NotNull(message = "Release date is required")
	private LocalDate releaseDate;

	public CreateMovieRequest() {
		super();
	}

	public CreateMovieRequest(@NotBlank(message = "Movie Title Cannot Be Blank") String title,
			@NotBlank(message = "Language cannot be blank") String language,
			@NotNull(message = "Duration is rerquired") @Positive(message = "Mins must be Positive") Integer durationMins,
			@NotBlank(message = "Genre is required") String genre,
			@NotBlank(message = "Certificate is required") String certificate,
			@NotNull(message = "Release date is required") LocalDate releaseDate) {
		super();
		this.title = title;
		this.language = language;
		this.durationMins = durationMins;
		this.genre = genre;
		this.certificate = certificate;
		this.releaseDate = releaseDate;
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
	
	

}
