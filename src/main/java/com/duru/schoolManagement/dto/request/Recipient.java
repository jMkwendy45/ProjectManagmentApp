package com.duru.schoolManagement.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Recipient {
    private String name;
    @NonNull
    private String email;

}
