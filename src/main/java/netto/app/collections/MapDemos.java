package netto.app.collections;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import netto.app.common.dto.Persona;

public class MapDemos {

	
	protected static void createMapOfPersonas() {
		//Version 1.8
		Map<String, Persona> mapPersona = new HashMap<String, Persona>(){
			{
				put("Persona1", new Persona("violeta" )); 
				put("Persona2", new Persona("Diana" ));
				put("Persona3", new Persona("Mario" ));
			}
		}; //*/
		
		//Declarando un arreglo de pagos
		Payment[] pagos = {
			new Payment() {
				{
					persona= mapPersona.get("Persona1");
					date = LocalDate.of(2021, 2, 4);
					amount = 5.0;
				}
			},
			new Payment() {
				{
					persona= mapPersona.get("Persona3");
					date = LocalDate.of(2021, 2, 14);
					amount = 6.0;
				}
			},
			new Payment() {
				{
					persona= mapPersona.get("Persona1");
					date = LocalDate.of(2021, 2, 4);
					amount = 7.0;
				}
			},
			new Payment() {
				{
					persona= mapPersona.get("Persona2");
					date = LocalDate.of(2021, 2, 18);
					amount = 8.0;
				}
			},
			new Payment() {
				{
					persona= mapPersona.get("Persona3");
					date = LocalDate.of(2021, 2, 22);
					amount = 8.0;
				}
			}
		};
		
	}
}
