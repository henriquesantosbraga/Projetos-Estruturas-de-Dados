package estrutura.Controller;

import estrutura.No;
import estrutura.Pilha;

public class PilhaController {
    public PilhaController() {
        super();
    }
    
    public String teste() throws Exception{
        Pilha  pilha = new Pilha();
        
        pilha.push(1);
        pilha.push(2);
        pilha.push(3);
        pilha.push(4);
        
        return pilha.toString();
    }
}
