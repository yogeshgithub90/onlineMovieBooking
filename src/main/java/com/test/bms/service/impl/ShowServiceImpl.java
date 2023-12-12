/**
 * 
 */
package com.test.bms.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.test.bms.adapter.ShowAdapter;
import com.test.bms.dto.PageResponse;
import com.test.bms.dto.ShowDto;
import com.test.bms.exception.DependencyException;
import com.test.bms.helper.ShowHelper;
import com.test.bms.model.MovieEntity;
import com.test.bms.model.ShowEntity;
import com.test.bms.model.ShowSeatsEntity;
import com.test.bms.model.TheaterEntity;
import com.test.bms.model.TheaterSeatsEntity;
import com.test.bms.repository.MovieRepository;
import com.test.bms.repository.ShowRepository;
import com.test.bms.repository.ShowSeatsRepository;
import com.test.bms.repository.TheaterRepository;
import com.test.bms.service.ShowService;

import lombok.extern.log4j.Log4j2;

/**
 * @author yogesh
 *
 * @date 04-Sep-2019
 */
@Log4j2
@Service
public class ShowServiceImpl implements ShowService {

	@Autowired
	private ShowRepository showsRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TheaterRepository theaterRepository;

	@Autowired
	private ShowSeatsRepository showSeatsRepository;

	@Override
	public ShowDto addShow(ShowDto showDto) {

		Optional<MovieEntity> optionalMovie = movieRepository.findById(showDto.getMovie().getId());

		if (!optionalMovie.isPresent()) {
			throw new DependencyException("Movie Not Found with ID: " + showDto.getMovie().getId() + " to add New Show");
		}

		Optional<TheaterEntity> optionalTheater = theaterRepository.findById(showDto.getTheatre().getId());

		if (!optionalTheater.isPresent()) {
			throw new DependencyException("Theater Not Found with ID: " + showDto.getMovie().getId() + " to add New Show");
		}

		log.info("Adding New Show: " + showDto);

		ShowEntity showEntity = ShowAdapter.toEntity(showDto);

		showEntity.setMovie(optionalMovie.get());
		showEntity.setTheater(optionalTheater.get());
		showEntity.setSeats(generateShowSeats(showEntity.getTheater().getSeats(), showEntity));

		for (ShowSeatsEntity seatsEntity : showEntity.getSeats()) {
			seatsEntity.setShow(showEntity);
		}

		showEntity = showsRepository.save(showEntity);

		log.info("Successfully Added New Show [ID: " + showEntity.getId() + ", ShowDate: " + showEntity.getShowDate() + ", ShowTime: " + showEntity.getShowTime() + "]");

		return ShowAdapter.toDto(showEntity);
	}

	private List<ShowSeatsEntity> generateShowSeats(List<TheaterSeatsEntity> theaterSeatsEntities, ShowEntity showEntity) {

		List<ShowSeatsEntity> showSeatsEntities = new ArrayList<>();

		for (TheaterSeatsEntity theaterSeatsEntity : theaterSeatsEntities) {

			ShowSeatsEntity showSeatsEntity =
					ShowSeatsEntity.builder()
							.seatNumber(theaterSeatsEntity.getSeatNumber())
							.seatType(theaterSeatsEntity.getSeatType())
							.rate((int) (theaterSeatsEntity.getRate() * showEntity.getRateMultiplier()))
							.build();

			showSeatsEntities.add(showSeatsEntity);
		}

		return showSeatsRepository.saveAll(showSeatsEntities);
	}

	@Override
	public PageResponse<ShowDto> searchShows(String movieName, String city, LocalDate showDate, LocalTime showTime, int pageNo, int limit) {

		log.info("Searching Shows by Params: [showName: " + movieName + ", city: " + city + ", showDate: " + showDate + ", showTime: " + showTime + "]");

		Specification<ShowEntity> specifications = ShowHelper.createSpecification(movieName, city, showDate, showTime);

		Page<ShowEntity> showsPage = showsRepository.findAll(specifications, PageRequest.of(pageNo - 1, limit));

		log.info("Found " + showsPage.getNumberOfElements() + " Shows on Page: " + showsPage.getNumber());

		PageResponse<ShowDto> pageResponse = new PageResponse<>();

		if (showsPage.hasContent()) {
			pageResponse.setNumber(pageNo);
			pageResponse.setRecords(showsPage.getNumberOfElements());

			pageResponse.setTotalPages(showsPage.getTotalPages());
			pageResponse.setTotalRecords(showsPage.getTotalElements());

			pageResponse.setData(ShowAdapter.toDto(showsPage.getContent()));
		}

		return pageResponse;
	}

}