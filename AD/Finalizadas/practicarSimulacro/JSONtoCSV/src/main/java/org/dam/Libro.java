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

public class Libro {
    private int codigo;
	private String titulo;
}
