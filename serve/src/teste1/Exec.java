package teste1;

import java.io.IOException;

public class Exec {
	
	public static void Executar(String a) {
		try {
			String[] command = {"cmd.exe", "/C", "Start", "C:\\Users\\Usuário\\Desktop\\programas\\".concat(a).concat(".bat")};
			Process p =  Runtime.getRuntime().exec(command);
		} catch (IOException e){
			System.out.println("Erro! Programa não encontrado.");
		}
	}
	
}
