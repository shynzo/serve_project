package teste1;

public class Mensagens {
	
	public static void mensagem(String mens){
		switch(mens) {
			case "te amo":
				System.out.println("Tamb�m te amo!");
				break;
			case "piada":
				double a = Math.random();
				int b = (int) (0 + a * (5-0));
					switch(b) {
						case 0:
							System.out.println("Porque � que o cavalo pegou numa p�?\n-Para cav�-lo.");
							break;
						case 1:
							System.out.println("Qual � o mar preferido dos doces?\nMar-shmallow.");
							break;
						case 2:
							System.out.println("Qual o contr�rio do Bailarino?\nBaila-voltano.");
							break;
						case 3:
							System.out.println("Como termina um jogo de patos?\nEmPATADO.");
							break;
						case 4:
							System.out.println("Como � a comida do peixe?\nMolhada.");
							break;
						case 5:
							System.out.println("Qual o nome da irm� do Garfield?\nColherfield.");
							break;
					}
				break;
			
			default:
				System.out.println("Mensagem: " + mens);
				break;
	}
	
	}
}
