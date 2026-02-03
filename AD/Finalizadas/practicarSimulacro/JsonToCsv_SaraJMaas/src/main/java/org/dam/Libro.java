package org.dam;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    @SerializedName("code")
    private Integer id;
    @SerializedName("title")
    private String titulo;
}
