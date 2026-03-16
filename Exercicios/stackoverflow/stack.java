public class stack {
	private String nome;
	private int idade;
	private int cpf;
	

	
	public stack(String nome) {
		this.nome = nome;
	}
	
	public stack(int idade) {
		this.idade = idade;
	}
	
	public stack(int cpf) {
		this.cpf = cpf;
	}
	
	public stack(String nome, int idade, int cpf) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
	}
}