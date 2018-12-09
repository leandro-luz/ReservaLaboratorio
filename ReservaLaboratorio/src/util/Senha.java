package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Senha {  
    public static String criptografar(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(senha.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return senha;
    }
    

    public static boolean verificarFormato(String senha) {
		boolean resultado = true;
  
		int low = 0;
		int uper = 0;
		int num = 0;
		int car = 0;
		int sum =0;
		
		for (int i = 0; i < senha.length(); i++) {
			int x = (int) senha.charAt(i);
				if(x>=48 && x<=57) {num++;}//numeros
				if(x>=97 && x<=122) {low++;}//minusculas
				if(x>=65 && x<=90) {uper++;}//maiusculas
				if(x>=35 && x<=45) {car++;}//caracteres especiais
		}
		
		sum = num+low+uper+car;
		
		if(num==0||low==0||uper==0||car==0||sum<8) {
			resultado = false;
		}

		return resultado;
    }
    
    
}
