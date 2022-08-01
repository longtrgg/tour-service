package com.ota.tour.resource;

import com.ota.tour.converter.CommonConverter;
import com.ota.tour.converter.ScheduleConverter;
import com.ota.tour.data.document.ScheduleDocument;
import com.ota.tour.data.model.ManagementPageResult;
import com.ota.tour.data.model.TourScheduleDTO;
import com.ota.tour.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/tour/schedule")
@AllArgsConstructor
public class TourScheduleManagementResource {
    private final ScheduleService scheduleService;
    private final ScheduleConverter scheduleConverter;
    private final CommonConverter commonConverter;

    @ApiOperation(value = "getScheduleUsingGET")
    @GetMapping()
    public ResponseEntity<ManagementPageResult<TourScheduleDTO>> getScheduleByActivityId(
            @RequestParam() Long activityId) {
        List<ScheduleDocument> scheduleDocuments = scheduleService.getScheduleByActivityId(activityId);
        ManagementPageResult<TourScheduleDTO> pageResult = commonConverter.toPageResult(scheduleDocuments
                .stream().map(scheduleDocument -> scheduleConverter.toScheduleResult(scheduleDocument)).filter(Objects::nonNull).collect(Collectors.toList()));
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @ApiOperation(value = "createOrUpdateScheduleUsingPOST")
    @PostMapping()
    public ResponseEntity<TourScheduleDTO> createOrUpdateSchedule(@RequestBody TourScheduleDTO scheduleDTO) {
        ScheduleDocument scheduleDocument = scheduleConverter.toScheduleDocument(scheduleDTO);
        scheduleDocument = scheduleService.saveOrUpdate(scheduleDocument);
        TourScheduleDTO tourScheduleDTO = scheduleConverter.toScheduleResult(scheduleDocument);
        return new ResponseEntity<>(tourScheduleDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "deleteScheduleUsingDELTE")
    @DeleteMapping()
    public ResponseEntity<?> deleteSchedule(@RequestParam Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
