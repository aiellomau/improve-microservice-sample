package com.improve.reservations.campsite.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.improve.reservations.campsite.model.Campsite;

@RepositoryRestResource(collectionResourceRel = "campsite", path = "campsite")
public interface CampsiteRepository extends PagingAndSortingRepository<Campsite, Long> {

}
