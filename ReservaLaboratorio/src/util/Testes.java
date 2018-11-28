package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dao.AgendaDAO;

public class Testes {

	public static void main(String[] args) throws ParseException {

		Integer dt_inicio = 20180101;
		
		System.out.println(dt_inicio);
		
		String dataString;
		Calendar calendar;
		Date data;
		//converte o data de Integer para String e retira os dados da data
		dataString = String.valueOf(dt_inicio);
		int anoInt = Integer.parseInt(dataString.substring(0, 4));
		int mesInt = Integer.parseInt(dataString.substring(4, 6));
		int diaInt = Integer.parseInt(dataString.substring(6, 8));
		
		System.out.println(anoInt);
		System.out.println(mesInt);
		System.out.println(diaInt);
		
		//cria um nova data e adiciona um dia
		calendar = Calendar.getInstance();
		calendar.set(anoInt, mesInt-1, diaInt, 00, 00, 00);
		System.out.println(calendar.getTime());
		
		calendar.add(Calendar.DATE, 01);
		data = calendar.getTime();
		
		System.out.println(calendar.getTime());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String data2 = sdf.format(data);
		
	
		System.out.println(data2);

	}

}
