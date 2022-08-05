package com.ota.tour.resource;

import com.ota.tour.converter.CommonConverter;
import com.ota.tour.converter.ServicePriceConverter;
import com.ota.tour.data.document.ServicePriceDocument;
import com.ota.tour.data.model.ServicePrice;
import com.ota.tour.data.model.ServicePriceResult;
import com.ota.tour.data.model.TourServicePriceDTO;
import com.ota.tour.service.ServicePriceService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static com.ota.tour.converter.CommonConverter.ZONE_ID_DEFAULT;

@RestController
@RequestMapping("api/tour/service-price")
@AllArgsConstructor
public class TourServicePriceManagementResource {
    private final ServicePriceService servicePriceService;
    private final ServicePriceConverter servicePriceConverter;
    private final CommonConverter commonConverter;

    @ApiOperation(value = "getServicePriceByActivityIdAndTimeRangeUsingGET")
    @GetMapping()
    public ResponseEntity<ServicePriceResult> getServicePriceByActivityIdAndTimeRange(
            @RequestParam() Long activityId,
            @RequestParam() String dateFrom,
            @RequestParam() String dateTo) {
        ZonedDateTime from = ZonedDateTime.of(LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MIN, ZoneId.of(ZONE_ID_DEFAULT));
        ZonedDateTime to = ZonedDateTime.of(LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MIN, ZoneId.of(ZONE_ID_DEFAULT));
        Map<Long, Map<String, List<ServicePrice>>> longMapTourServicePrice = servicePriceService.findByActivityIdAndTimeRange(activityId, from, to);
        ServicePriceResult result = servicePriceConverter.toDatePriceResult(longMapTourServicePrice);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "createOrUpdateServicePriceUsingPOST")
    @PostMapping()
    public ResponseEntity<TourServicePriceDTO> createOrUpdateServicePrice(@RequestBody TourServicePriceDTO servicePriceDTO) {
        ServicePriceDocument servicePriceDocument = servicePriceConverter.toServicePriceDocument(servicePriceDTO);
        servicePriceDocument = servicePriceService.saveOrUpdate(servicePriceDocument);
        TourServicePriceDTO tourServiceDTO = servicePriceConverter.toServicePriceResult(servicePriceDocument);
        return new ResponseEntity<>(tourServiceDTO, HttpStatus.CREATED);
    }
}
