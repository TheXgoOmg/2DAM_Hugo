package org.cipfpcheste.dam2.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class provinces {
    private long parent_code;
    private long code;
    private String label;
    @SerializedName( "towns")
    private List<towns> towns;

}
