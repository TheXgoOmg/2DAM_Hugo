package org.dam;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Universo {
    @SerializedName("idUniverse")
    private Integer id;
    @SerializedName("name")
    private String nombre;
    @SerializedName("series")
    private List<Saga> sagas;
}
