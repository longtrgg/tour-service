package com.ota.tour.resource;

import com.ota.tour.data.document.TourDocument;
import com.ota.tour.service.TourService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tour")
@AllArgsConstructor
public class TourManagementResource {
    private final TourService tourService;

    @GetMapping()
    public ResponseEntity<List<TourDocument>> getAllTours() {
        List<TourDocument> tourDocumentList = tourService.findAllTours();
        return new ResponseEntity<>(tourDocumentList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<TourDocument> createOrUpdateTour(@RequestBody TourDocument tourDocument) {
        tourDocument = tourService.saveOrUpdate(tourDocument);
        return new ResponseEntity<>(tourDocument, HttpStatus.CREATED);
    }
}
