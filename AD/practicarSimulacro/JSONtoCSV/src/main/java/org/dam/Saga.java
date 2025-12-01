package org.dam;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Saga {
    private int idSaga;
	private String titulo;
	private List<Libro> libros = new ArrayList<>();
}
