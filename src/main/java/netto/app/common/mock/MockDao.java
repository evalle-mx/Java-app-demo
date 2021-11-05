package netto.app.common.mock;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import netto.app.common.dto.Persona;

public class MockDao {

	public static List<Persona> getPeople() throws IOException {
        InputStream inputStream = Resources.getResource("personas.json").openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Type listType = new TypeToken<ArrayList<Persona>>() {
        }.getType();
        return new Gson().fromJson(json, listType);
    }
	
	
	public static void main(String[] args) {
		try {
			List<Persona> people = getPeople();
			System.out.println("size: " + people.size() );
			for(Persona per: people ) {
				System.out.println(per.getNombreCompleto());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
