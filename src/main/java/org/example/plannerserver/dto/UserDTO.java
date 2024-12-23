package org.example.plannerserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.example.plannerserver.entity.ApplicationData;

@Data
@Builder
public class UserDTO {
    private Long userId;
    private String username;
    private String password;
    private String email;

    private ApplicationData applicationData;

    public UserDTO(Long userId, String username, String password, String email, ApplicationData applicationData) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.applicationData = applicationData;
    }

    @JsonProperty("applicationData")
    public Long getApplicationData() {
        return applicationData != null ? applicationData.getData_id() : null;
    }


}
