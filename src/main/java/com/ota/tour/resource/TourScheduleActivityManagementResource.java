package com.ota.tour.resource;

import com.ota.tour.converter.CommonConverter;
import com.ota.tour.converter.ScheduleActivityConverter;
import com.ota.tour.data.document.ScheduleActivityDocument;
import com.ota.tour.data.model.ManagementPageResult;
import com.ota.tour.data.model.TourScheduleActivityDTO;
import com.ota.tour.service.ScheduleActivityService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/tour/schedule-activity")
@AllArgsConstructor
public class TourScheduleActivityManagementResource {
    private final ScheduleActivityService scheduleActivityService;
    private final ScheduleActivityConverter scheduleActivityConverter;
    private final CommonConverter commonConverter;

    @ApiOperation(value = "getScheduleActivityByScheduleIdUsingGET")
    @GetMapping()
    public ResponseEntity<ManagementPageResult<TourScheduleActivityDTO>> getScheduleActivityByScheduleId(
            @RequestParam() Long scheduleId) {
        List<ScheduleActivityDocument> scheduleActivityDocuments = scheduleActivityService.getScheduleActivityByScheduleId(scheduleId);
        ManagementPageResult<TourScheduleActivityDTO> pageResult = commonConverter.toPageResult(scheduleActivityDocuments
                .stream().map(scheduleActivityDocument -> scheduleActivityConverter.toScheduleActivityResult(scheduleActivityDocument)).filter(Objects::nonNull).collect(Collectors.toList()));
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @ApiOperation(value = "createOrUpdateScheduleActivityUsingPOST")
    @PostMapping()
    public ResponseEntity<TourScheduleActivityDTO> createOrUpdateScheduleActivity(@RequestBody TourScheduleActivityDTO scheduleDTO) {
        ScheduleActivityDocument scheduleActivityDocument = scheduleActivityConverter.toScheduleActivityDocument(scheduleDTO);
        scheduleActivityDocument = scheduleActivityService.saveOrUpdate(scheduleActivityDocument);
        TourScheduleActivityDTO tourScheduleActivityDTO = scheduleActivityConverter.toScheduleActivityResult(scheduleActivityDocument);
        return new ResponseEntity<>(tourScheduleActivityDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "deleteScheduleActivityUsingDELTE")
    @DeleteMapping()
    public ResponseEntity<?> deleteScheduleActivity(@RequestParam Long scheduleActivityId) {
        scheduleActivityService.deleteScheduleActivity(scheduleActivityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
