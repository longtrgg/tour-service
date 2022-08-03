package com.ota.tour.resource;

import com.ota.tour.converter.CommonConverter;
import com.ota.tour.converter.OccupancyConverter;
import com.ota.tour.data.document.OccupancyDocument;
import com.ota.tour.data.model.ManagementPageResult;
import com.ota.tour.data.model.TourOccupancyDTO;
import com.ota.tour.service.OccupancyService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/tour/occupancy")
@AllArgsConstructor
public class TourOccupancyManagementResource {
    private final OccupancyService occupancyService;
    private final OccupancyConverter occupancyConverter;
    private final CommonConverter commonConverter;

    @ApiOperation(value = "getOccupancyByActivityIdUsingGET")
    @GetMapping()
    public ResponseEntity<ManagementPageResult<TourOccupancyDTO>> getOccupancyByActivityId(
            @RequestParam() Long activityId) {
        List<OccupancyDocument> occupancyDocuments = occupancyService.getOccupancyByActivityId(activityId);
        ManagementPageResult<TourOccupancyDTO> pageResult = commonConverter.toPageResult(occupancyDocuments
                .stream().map(occupancyDocument -> occupancyConverter.toOccupancyResult(occupancyDocument)).filter(Objects::nonNull).collect(Collectors.toList()));
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @ApiOperation(value = "createOrUpdateOccupancyUsingPOST")
    @PostMapping()
    public ResponseEntity<TourOccupancyDTO> createOrUpdateOccupancy(@RequestBody TourOccupancyDTO occupancyDTO) {
        OccupancyDocument occupancyDocument = occupancyConverter.toOccupancyDocument(occupancyDTO);
        occupancyDocument = occupancyService.saveOrUpdate(occupancyDocument);
        TourOccupancyDTO tourOccupancyDTO = occupancyConverter.toOccupancyResult(occupancyDocument);
        return new ResponseEntity<>(tourOccupancyDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "deleteOccupancyUsingDELTE")
    @DeleteMapping()
    public ResponseEntity<?> deleteOccupancy(@RequestParam Long occupancyId) {
        occupancyService.deleteOccupancy(occupancyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
