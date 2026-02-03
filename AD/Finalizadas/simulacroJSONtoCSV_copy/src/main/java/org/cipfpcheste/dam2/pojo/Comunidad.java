package org.cipfpcheste.dam2.pojo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Comunidad {
    private long parent_code;
    private long code;
    private String label;
    List<Provinces> provinces;

}
