package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatarDatas {

	public static String converterData(String dt_prevista)
			throws ParseException {

		Calendar c = Calendar.getInstance();
		int ano = Integer.parseInt(dt_prevista.substring(0, 4));
		int mes = Integer.parseInt(dt_prevista.substring(5, 7));
		int dia = Integer.parseInt(dt_prevista.substring(8, 10));

		c.set(ano, mes - 1, dia, 23, 59, 59);
		Date data = c.getTime();
		DateFormat dtHora = DateFormat.getDateTimeInstance();
		String dataFormatada = dtHora.format(data);
		return dataFormatada;
	}

	public static String dataAtual() throws ParseException {
		Calendar c1 = Calendar.getInstance();
		Date data1 = c1.getTime();
		DateFormat dtHora = DateFormat.getDateTimeInstance();
		String dataFormatada = dtHora.format(data1);
		return dataFormatada;
	}

	public static Integer converterStringtoInteger(String dataString) {
		SimpleDateFormat ano = new SimpleDateFormat("yyyy");
		SimpleDateFormat mes = new SimpleDateFormat("MM");
		SimpleDateFormat dia = new SimpleDateFormat("dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date data;
		Integer dataInteger = 0;
		try {
			data = sdf.parse(dataString);
			dataInteger = Integer.parseInt(ano.format(data)) * 10000
					+ Integer.parseInt(mes.format(data)) * 100
					+ Integer.parseInt(dia.format(data));
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		return dataInteger;
	}

	public static Integer incrementarData(Integer dt_inicio) {
		Calendar calendar;
		Date data;
		Integer dataInteger;
		
		//converte de inteiro para data e adiciona um dia
		calendar = converterIntegertoDate(dt_inicio);
		calendar.add(Calendar.DATE, 01);
		data = calendar.getTime();
		
		//converte para o padrao usado e converte para Inteiro
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dataInteger = converterStringtoInteger(sdf.format(data));
	
		return dataInteger;
	}

	
	public static  Calendar converterIntegertoDate(Integer dataInteger) {
		String dataString;
		Calendar calendar;
		
		//converte o data de Integer para String e retira os dados da data
		dataString = String.valueOf(dataInteger);
		int anoInt = Integer.parseInt(dataString.substring(0, 4));
		int mesInt = Integer.parseInt(dataString.substring(4, 6));
		int diaInt = Integer.parseInt(dataString.substring(6, 8));
		
		//cria um nova data
		calendar = Calendar.getInstance();
		calendar.set(anoInt, mesInt-1, diaInt, 00, 00, 00);
	
		return calendar;
	}
	
}
