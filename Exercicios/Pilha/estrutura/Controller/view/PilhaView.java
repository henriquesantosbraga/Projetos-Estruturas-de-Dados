package estrutura.Controller.view;
import estrutura.Controller.PilhaController;
public class PilhaView {
    public static void main(String[] args) {
        try{
            PilhaController obj = new PilhaController();
            System.out.println(obj.teste());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
