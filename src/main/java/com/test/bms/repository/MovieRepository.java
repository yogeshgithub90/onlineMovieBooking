package com.test.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.test.bms.enums.MovieLanguage;
import com.test.bms.model.MovieEntity;

/**
 * 
 * @author yogesh
 *
 * @date 04-Sep-2019
 */
@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long>, JpaSpecificationExecutor<MovieEntity> {

	boolean existsByNameAndLanguage(String name, MovieLanguage language);
}