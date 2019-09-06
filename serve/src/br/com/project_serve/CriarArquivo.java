package br.com.project_serve;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class CriarArquivo {
	private static String convert(String str) {
		char ch[] = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {
			if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {
				if (ch[i] >= 'a' && ch[i] <= 'z') {
					ch[i] = (char) (ch[i] - 'a' + 'A');
				}
			} else if (ch[i] >= 'A' && ch[i] <= 'Z') {
				ch[i] = (char) (ch[i] + 'a' - 'A');
			}
		}
		String st = new String(ch);
		return st;
	}

	public static void criararq(String nome, String nomexe, String camP) {
		try {
			File file = new File("C:\\SERVE\\".concat(convert(nome)).concat(".bat"));
			boolean fvar = file.createNewFile();
			if (fvar) {
				PrintWriter pw = new PrintWriter(file);
				pw.print("@echo off" + System.getProperty("line.separator") + "cd ".concat(camP)
						+ System.getProperty("line.separator") + "start ".concat(nomexe)
						+ System.getProperty("line.separator") + "exit");
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
