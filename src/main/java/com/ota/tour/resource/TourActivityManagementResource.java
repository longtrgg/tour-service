package com.ota.tour.resource;

import com.ota.tour.converter.ActivityConverter;
import com.ota.tour.data.document.ActivityDocument;
import com.ota.tour.data.model.ActivityManagementPageResult;
import com.ota.tour.data.model.TourActivityDTO;
import com.ota.tour.service.ActivityService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/activity")
@AllArgsConstructor
public class TourActivityManagementResource {
    private final ActivityService activityService;
    private final ActivityConverter activityConverter;

    @ApiOperation(value = "getActivityUsingGET")
    @GetMapping()
    public ResponseEntity<ActivityManagementPageResult> getActivities(
            @RequestParam(required = false) Long activityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ActivityDocument> activityDocumentPage = activityService.getActivity(activityId, pageable);
        ActivityManagementPageResult pageResult = activityConverter.toActivityPageResult(activityDocumentPage);
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @ApiOperation(value = "createOrUpdateActivityUsingPOST")
    @PostMapping()
    public ResponseEntity<ActivityDocument> createOrUpdateActivity(@RequestBody TourActivityDTO activityDTO) {
        ActivityDocument activityDocument = activityConverter.toActivityDocument(activityDTO);
        activityDocument = activityService.saveOrUpdate(activityDocument);
        return new ResponseEntity<>(activityDocument, HttpStatus.CREATED);
    }

    @ApiOperation(value = "deleteActivityUsingDELTE")
    @DeleteMapping()
    public ResponseEntity<?> deleteActivity(@RequestParam Long activityId) {
        activityService.deleteActivity(activityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
