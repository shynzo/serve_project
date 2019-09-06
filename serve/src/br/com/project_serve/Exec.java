package br.com.project_serve;

import java.io.IOException;

public class Exec {
	
	public static void Executar(String a) {
		try {
			String[] command = {"cmd.exe", "/C", "Start", "C:\\SERVE\\".concat(a).concat(".bat")};
			Runtime.getRuntime().exec(command);
		} catch (IOException e){
			System.out.println("Erro! Programa não encontrado.");
		}
	}
	
}
