package com.jerry.common.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@Data
public class DriverUser implements Serializable {

    private Long id;

    private String address;

    private String driverName;

    private String driverPhone;

    private Integer driverGender;

    private LocalDate driverBirthday;

    private String driverNation;

    private String driverContactAddress;

    private String licenseId;

    private LocalDate getDriverLicenseDate;

    private LocalDate driverLicenseOn;

    private LocalDate driverLicenseOff;

    private Integer taxiDriver;

    private String certificateNo;

    private String networkCarIssueOrganization;

    private LocalDate networkCarIssueDate;

    private LocalDate getNetworkCarProofDate;

    private LocalDate networkCarProofOn;

    private LocalDate networkCarProofOff;

    private LocalDate registerDate;

    private Integer commercialType;

    private String contractCompany;

    private LocalDate contractOn;

    private LocalDate contractOff;

    private Integer state;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
