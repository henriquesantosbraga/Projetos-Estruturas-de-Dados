import java.io.File;

public class ArquivoController {
	public ArquivoController() {
		super();
	}
	
	public void procurar(String diretorio, String extencao) {
		File fileDir = new File(diretorio);
		File[] fileList = fileDir.listFile();
		
		if(fileList != null) {
			for(File file : fileList) {
				if(file.getPath().endsWitch(extencao)) {
					System.out.println(file.getPath);
				} else {
					procurar(file.getPath() , extencao);
				}
			}
		}
		return;
	}
}