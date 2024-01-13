package com.example.jiraffeserver.organization.util;

import com.example.jiraffeserver.organization.request.OrganizationPageableRequest;
import org.springframework.data.domain.Pageable;

public class OrganizationPageableRequestUtil {
    public static OrganizationPageableRequest getRequest(Long organizationId, Pageable pageable) {
       return OrganizationPageableRequest
               .builder()
               .organizationId(organizationId)
               .pageable(pageable)
               .build();
    }
}
