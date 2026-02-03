package org.dam;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Universo {
    private int idUniverso;
    private String nombre;
	private List<Saga> sagas = new ArrayList<>();
}
