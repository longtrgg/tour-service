package com.ota.tour.resource;

import com.ota.tour.converter.CommonConverter;
import com.ota.tour.converter.InventoryConverter;
import com.ota.tour.data.document.InventoryDocument;
import com.ota.tour.data.model.TourInventoryDTO;
import com.ota.tour.service.InventoryService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tour/inventory")
@AllArgsConstructor
public class TourInventoryManagementResource {
    private final InventoryService inventoryService;
    private final InventoryConverter inventoryConverter;
    private final CommonConverter commonConverter;

//    @ApiOperation(value = "getServicePriceByActivityIdAndTimeRangeUsingGET")
//    @GetMapping()
//    public ResponseEntity<ServicePriceResult> getServicePriceByActivityIdAndTimeRange(
//            @RequestParam() Long activityId,
//            @RequestParam() String dateFrom,
//            @RequestParam() String dateTo) {
//        ZonedDateTime from = ZonedDateTime.of(LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MIN, ZoneId.of(ZONE_ID_DEFAULT));
//        ZonedDateTime to = ZonedDateTime.of(LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MIN, ZoneId.of(ZONE_ID_DEFAULT));
//        Map<Long, Map<String, List<ServicePrice>>> longMapTourServicePrice = inventoryDocument.findByActivityIdAndTimeRange(activityId, from, to);
//        ServicePriceResult result = inventoryConverter.toDatePriceResult(longMapTourServicePrice);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @ApiOperation(value = "createOrUpdateInventoryUsingPOST")
    @PostMapping()
    public ResponseEntity<TourInventoryDTO> createOrUpdateInventory(@RequestBody TourInventoryDTO inventoryDTO) {
        InventoryDocument inventoryDocument = inventoryConverter.toInventoryDocument(inventoryDTO);
        inventoryDocument = inventoryService.saveOrUpdate(inventoryDocument);
        TourInventoryDTO tourServiceDTO = inventoryConverter.toInventoryResult(inventoryDocument);
        return new ResponseEntity<>(tourServiceDTO, HttpStatus.CREATED);
    }
}
