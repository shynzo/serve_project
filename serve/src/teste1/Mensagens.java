package teste1;

public class Mensagens {
	private String mens;

	public Mensagens(String mens) {
		this.mens = mens;
	}
	
	public void mensagem(){
		switch(mens) {
			case "te amo":
				System.out.println("Também te amo!");
				break;
			case mens.contains("piada"):
				
			default:
				System.out.println("Mensagem: " + mens);
				break;
	}
	
	}
}
