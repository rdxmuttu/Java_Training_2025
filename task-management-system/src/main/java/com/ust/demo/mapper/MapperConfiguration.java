package com.ust.demo.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
        componentModel = "spring",                 // Register mappers as Spring beans
        unmappedTargetPolicy = ReportingPolicy.IGNORE // Ignores unmapped fields (prevents compile warnings)
)
public interface MapperConfiguration {
}
