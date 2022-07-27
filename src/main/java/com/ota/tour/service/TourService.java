package com.ota.tour.service;

import com.ota.tour.data.document.TourDocument;

import java.util.List;

public interface TourService {

    TourDocument saveOrUpdate(TourDocument tourDocument);

    List<TourDocument> findAllTours();

}
