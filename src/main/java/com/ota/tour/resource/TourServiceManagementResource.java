package com.ota.tour.resource;

import com.ota.tour.converter.CommonConverter;
import com.ota.tour.converter.ServiceConverter;
import com.ota.tour.data.document.ServiceDocument;
import com.ota.tour.data.model.ManagementPageResult;
import com.ota.tour.data.model.TourServiceDTO;
import com.ota.tour.service.ServiceService;
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
@RequestMapping("api/tour/service")
@AllArgsConstructor
public class TourServiceManagementResource {
    private final ServiceService serviceService;
    private final ServiceConverter serviceConverter;
    private final CommonConverter commonConverter;

    @ApiOperation(value = "getServiceByActivityIdUsingGET")
    @GetMapping()
    public ResponseEntity<ManagementPageResult<TourServiceDTO>> getServiceByActivityId(
            @RequestParam(required = false) Long activityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ServiceDocument> serviceDocumentPage = serviceService.getServiceByActivityId(activityId, pageable);
        List<TourServiceDTO> serviceDTOList = serviceDocumentPage.getContent()
                .stream().map(serviceDocument -> serviceConverter.toServiceResult(serviceDocument)).filter(Objects::nonNull).collect(Collectors.toList());
        ManagementPageResult<TourServiceDTO> pageResult = commonConverter.toPageResult(serviceDocumentPage, serviceDTOList);
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @ApiOperation(value = "createOrUpdateServiceUsingPOST")
    @PostMapping()
    public ResponseEntity<TourServiceDTO> createOrUpdateService(@RequestBody TourServiceDTO serviceDTO) {
        ServiceDocument serviceDocument = serviceConverter.toServiceDocument(serviceDTO);
        serviceDocument = serviceService.saveOrUpdate(serviceDocument);
        TourServiceDTO tourServiceDTO = serviceConverter.toServiceResult(serviceDocument);
        return new ResponseEntity<>(tourServiceDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "deleteServiceUsingDELTE")
    @DeleteMapping()
    public ResponseEntity<?> deleteService(@RequestParam Long serviceId) {
        serviceService.deleteService(serviceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
