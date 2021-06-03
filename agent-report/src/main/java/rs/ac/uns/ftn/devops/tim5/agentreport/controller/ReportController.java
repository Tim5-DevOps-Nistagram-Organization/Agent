package rs.ac.uns.ftn.devops.tim5.agentreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.devops.tim5.agentreport.dto.ReportEarnedDTO;
import rs.ac.uns.ftn.devops.tim5.agentreport.dto.ReportSoldDTO;
import rs.ac.uns.ftn.devops.tim5.agentreport.model.Product;
import rs.ac.uns.ftn.devops.tim5.agentreport.service.ReportService;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
@CrossOrigin("*")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(path = "/sold")
    public ResponseEntity<Collection<ReportSoldDTO>> reportProductSold() {
        HashMap<Product, Integer> report = reportService.reportMostSold();
        Collection<ReportSoldDTO> reportDTO = report.entrySet().stream()
                .map(e -> new ReportSoldDTO(e.getKey().getId(), e.getKey().getName(), e.getValue()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(reportDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/earned")
    public ResponseEntity<Collection<ReportEarnedDTO>> reportProductEarned() {
        HashMap<Product, Double> report = reportService.reportMostEarned();
        Collection<ReportEarnedDTO> reportDTO = report.entrySet().stream()
                .map(e -> new ReportEarnedDTO(e.getKey().getId(), e.getKey().getName(), e.getValue()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(reportDTO, HttpStatus.OK);
    }

}
