package com.ota.tour.service.impl;

import com.ota.tour.data.document.TourDocument;
import com.ota.tour.data.repository.TourRepository;
import com.ota.tour.service.TourService;
import com.ota.tour.util.Helper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;

    @Override
    public TourDocument saveOrUpdate(TourDocument tourDocument) {
        Assert.notNull(tourDocument, "tourDocument must not be null");
        ZonedDateTime now = ZonedDateTime.now();
        if (tourDocument.getId() != null) {
            Optional<TourDocument> updatedOpt = tourRepository.findById(tourDocument.getId());
            if (updatedOpt.isPresent()) {
                TourDocument updated = updatedOpt.get();
                if (updated.getName() == null) {
                    updated.setName(new ArrayList<>());
                }
                Helper.updateTranslation(tourDocument.getName(), updated.getName());
                if (tourDocument.getMeetUpLocation() != null) {
                    updated.setMeetUpLocation(tourDocument.getMeetUpLocation());
                }

                if (updated.getMeetUpAddress() == null) {
                    updated.setMeetUpAddress(new ArrayList<>());
                }
                Helper.updateTranslation(tourDocument.getMeetUpAddress(), updated.getMeetUpAddress());

                if (updated.getOverview() == null) {
                    updated.setOverview(new ArrayList<>());
                }
                Helper.updateTranslation(tourDocument.getOverview(), updated.getOverview());

                if (updated.getPolicy() == null) {
                    updated.setPolicy(new ArrayList<>());
                }
                Helper.updateTranslation(tourDocument.getPolicy(), updated.getPolicy());

                if (updated.getHowToUse() == null) {
                    updated.setHowToUse(new ArrayList<>());
                }
                Helper.updateTranslation(tourDocument.getHowToUse(), updated.getHowToUse());

                if (updated.getImportantInformation() == null) {
                    updated.setImportantInformation(new ArrayList<>());
                }
                Helper.updateTranslation(tourDocument.getImportantInformation(), updated.getImportantInformation());

                if (updated.getMeetUpInformation() == null) {
                    updated.setMeetUpInformation(new ArrayList<>());
                }
                Helper.updateTranslation(tourDocument.getMeetUpInformation(), updated.getMeetUpInformation());
                if (updated.getNote() == null) {
                    updated.setNote(new ArrayList<>());
                }
                Helper.updateTranslation(tourDocument.getNote(), updated.getNote());
                if (tourDocument.getDestinationIds() != null) {
                    updated.setDestinationIds(tourDocument.getDestinationIds());
                }
                updated.setStatus(tourDocument.getStatus());
                updated.setLastModifiedDate(now);
                updated = tourRepository.save(updated);

                return updated;
            }
        }
        tourDocument.setLastModifiedDate(now);
        tourDocument.setCreatedDate(now);
        tourDocument.setLastModifiedBy(tourDocument.getLastModifiedBy());
        tourDocument = tourRepository.save(tourDocument);
        return tourDocument;
    }

    @Override
    public List<TourDocument> findAllTours() {
        return tourRepository.findAll();
    }
}
