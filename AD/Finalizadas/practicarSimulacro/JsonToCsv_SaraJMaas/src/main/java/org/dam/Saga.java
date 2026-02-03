package org.dam;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Saga {
    @SerializedName("idSeries")
    private Integer id;
    @SerializedName("title")
    private String titulo;
    @SerializedName("books")
    private List<Libro> libros;
}
