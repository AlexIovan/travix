package com.travix.medusa.busyflights.domain.toughjet;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ToughJetRequest {

    @NotNull
    @Size(min = 3, max = 3)
    private String from;
    @NotNull
    @Size(min = 3, max = 3)
    private String to;
    @NotNull
    private String outboundDate;
    @NotNull
    private String inboundDate;
    @NotNull
    private int numberOfAdults;

}
