package br.com.project_serve;

import java.io.File;
import java.io.IOException;

public class Comandos {
	
	private static boolean check = false;
	private static boolean cumpriu = false;
	private static String programa;
	
	public static void executar(String a) {
		  File folder = new File("C:\\SERVE");
		  String[] files = folder.list();
		  for (String file : files){
			  file = file.replace(".bat", "");
			  if(file.equalsIgnoreCase(a)) {
				  Exec.Executar(file);
			  	  check = true;
			  	  programa = a;
			  }
			  else
				  continue;
		  }
	}
	
	public static void listarquivos() {
		  File folder = new File("C:\\SERVE");
		  String[] files = folder.list();
		  for (String file : files){
			  file = file.replace(".bat", "");
		    System.out.println("\t> " + file);
		  }
		}

	public static void mensagem(String mens) throws IOException{
		String[] split = mens.split(" ");
		for(int i = 0; i < split.length; i++) {
		switch(split[i]) {
			case "abrir":
				if(split[i].equalsIgnoreCase("abrir") && split.length == 1) {
					System.out.println("Programas disponíveis para serem executados:\n");
					listarquivos();
					System.out.println("\nPara executar algum, digite:\n\t \"abrir (nome do programa)\"\n");
					cumpriu = true;
					break;
				} else {
				split[i] = "";
				for(int j = 0; j < split.length; j++) {
					executar(split[j]);
					if(!check)
						continue;
					else
						break;
					}
					if(check) {
						System.out.println("O programa " + programa + " foi aberto!");
						cumpriu = true;
					} else {
						System.out.println("O programa " + programa + " não foi encontrado!");
						cumpriu = false;
					}
				
					check = false;
					break;
				}
			case "fechar":
				if(split[i].equalsIgnoreCase("fechar") && split.length == 1) {
					System.out.println("Para encerrar um programa, digite:\n\t \"fechar (nome do prgrama\"\n");
				} else {
				split[i] = "";
				for(int j = 0; j < split.length; j++) {
					boolean check1 = VerificaAberto.verf(split[j]);
					if(check1) {
						Runtime.getRuntime().exec("TASKKILL /F /IM ".concat(split[j].concat("*")) + " /T");
						check = true;
						programa = split[j];
					} else {
						continue;
					}
					break;
					}
					if(check) {
						System.out.println("O programa " + programa + " foi encerrado!");
						cumpriu = true;
					} else {
						System.out.println("O programa " + programa + " não encontra-se aberto!");
						cumpriu = false;
					}
				check = false;
				break;
			}
			case "piada":
				double a = Math.random();
				int b = (int) (0 + a * (5-0));
					switch(b) {
						case 0:
							System.out.println("Porque é que o cavalo pegou numa pá?\n-Para cavá-lo.");
							break;
						case 1:
							System.out.println("Qual é o mar preferido dos doces?\nMar-shmallow.");
							break;
						case 2:
							System.out.println("Qual o contrário do Bailarino?\nBaila-voltano.");
							break;
						case 3:
							System.out.println("Como termina um jogo de patos?\nEmPATADO.");
							break;
						case 4:
							System.out.println("Como é a comida do peixe?\nMolhada.");
							break;
						case 5:
							System.out.println("Qual o nome da irmã do Garfield?\nColherfield.");
							break;
					}
				cumpriu = true;
				break;

			default:
				continue;
				
		}
	}
		if(!cumpriu) {
			System.out.println("Mensagem: " + mens);
		} else {
			cumpriu = false;
		}

	}
}