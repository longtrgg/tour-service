package com.ota.tour.resource;

import com.ota.tour.converter.CommonConverter;
import com.ota.tour.converter.ServicePriceConverter;
import com.ota.tour.data.document.ServicePriceDocument;
import com.ota.tour.data.model.TourServicePriceDTO;
import com.ota.tour.service.ServicePriceService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tour/service-price")
@AllArgsConstructor
public class TourServicePriceManagementResource {
    private final ServicePriceService servicePriceService;
    private final ServicePriceConverter servicePriceConverter;
    private final CommonConverter commonConverter;

//    @ApiOperation(value = "getServiceByActivityIdUsingGET")
//    @GetMapping()
//    public ResponseEntity<ManagementPageResult<TourServicePriceDTO>> getServiceByActivityId(
//            @RequestParam(required = false) Long activityId,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "20") int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<ServicePriceDocument> servicePriceDocumentPage = servicePriceService.getServiceByActivityId(activityId, pageable);
//        List<TourServicePriceDTO> servicePriceDTOList = servicePriceDocumentPage.getContent()
//                .stream().map(servicePriceDocument -> servicePriceConverter.toServiceResult(servicePriceDocument)).filter(Objects::nonNull).collect(Collectors.toList());
//        ManagementPageResult<TourServicePriceDTO> pageResult = commonConverter.toPageResult(servicePriceDocumentPage, servicePriceDTOList);
//        return new ResponseEntity<>(pageResult, HttpStatus.OK);
//    }

    @ApiOperation(value = "createOrUpdateServiceUsingPOST")
    @PostMapping()
    public ResponseEntity<TourServicePriceDTO> createOrUpdateService(@RequestBody TourServicePriceDTO servicePriceDTO) {
        ServicePriceDocument servicePriceDocument = servicePriceConverter.toServicePriceDocument(servicePriceDTO);
        servicePriceDocument = servicePriceService.saveOrUpdate(servicePriceDocument);
        TourServicePriceDTO tourServiceDTO = servicePriceConverter.toServicePriceResult(servicePriceDocument);
        return new ResponseEntity<>(tourServiceDTO, HttpStatus.CREATED);
    }
}
