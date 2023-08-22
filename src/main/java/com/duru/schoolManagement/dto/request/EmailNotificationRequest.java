package com.duru.schoolManagement.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.duru.schoolManagement.utils.AppUtils.APP_EMAIL;
import static com.duru.schoolManagement.utils.AppUtils.APP_NAME;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotificationRequest {
    private  final Sender sender = new Sender(APP_NAME,APP_EMAIL);
    @JsonProperty("to")
    private List<Recipient> recipients;
    @JsonProperty("cc")
    private List<String>copiedEmails;
    private String TextContents;
    private String subject;
    @JsonProperty("htmlContent")
    private String mailContents;

}
