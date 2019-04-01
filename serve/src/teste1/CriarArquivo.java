package teste1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CriarArquivo {
	static Scanner t = new Scanner(System.in);
	public static String arq;
	public static void criararq(String nome, String nomexe, String camP) {
		try {
			File file = new File("C:\\Users\\Usuário\\Desktop\\programas\\".concat(nome).concat(".bat"));
			boolean fvar = file.createNewFile();
			
			if(fvar) {
				PrintWriter pw = new PrintWriter(file);
				pw.print("@echo off" + System.getProperty("line.separator") + "cd ".concat(camP) + System.getProperty("line.separator") + 
						"start ".concat(nomexe) + System.getProperty("line.separator") + "exit");
				pw.close();
				System.out.println("Programa configurado!");
			} else {
				System.out.println("ERRO!\nPrograma já existente.");
			}
		} catch (IOException e) {
			System.out.println("Ocorreu uma exceção: ");
			e.printStackTrace();
		}
	}
	
	
}
