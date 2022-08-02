package com.ota.tour.resource;

import com.ota.tour.converter.ActivityConverter;
import com.ota.tour.converter.CommonConverter;
import com.ota.tour.data.document.ActivityDocument;
import com.ota.tour.data.model.ManagementPageResult;
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

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/tour/activity")
@AllArgsConstructor
public class TourActivityManagementResource {
    private final ActivityService activityService;
    private final ActivityConverter activityConverter;
    private final CommonConverter commonConverter;

    @ApiOperation(value = "getActivityUsingGET")
    @GetMapping()
    public ResponseEntity<ManagementPageResult<TourActivityDTO>> getActivities(
            @RequestParam(required = false) Long activityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ActivityDocument> activityDocumentPage = activityService.getActivity(activityId, pageable);
        List<TourActivityDTO> activityDTOList = activityDocumentPage.getContent()
                .stream().map(activityDocument -> activityConverter.toActivityResult(activityDocument)).filter(Objects::nonNull).collect(Collectors.toList());
        ManagementPageResult<TourActivityDTO> pageResult = commonConverter.toPageResult(activityDocumentPage, activityDTOList);
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @ApiOperation(value = "createOrUpdateActivityUsingPOST")
    @PostMapping()
    public ResponseEntity<TourActivityDTO> createOrUpdateActivity(@RequestBody TourActivityDTO activityDTO) {
        ActivityDocument activityDocument = activityConverter.toActivityDocument(activityDTO);
        activityDocument = activityService.saveOrUpdate(activityDocument);
        TourActivityDTO tourActivityDTO = activityConverter.toActivityResult(activityDocument);
        return new ResponseEntity<>(tourActivityDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "deleteActivityUsingDELTE")
    @DeleteMapping()
    public ResponseEntity<?> deleteActivity(@RequestParam Long activityId) {
        activityService.deleteActivity(activityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
