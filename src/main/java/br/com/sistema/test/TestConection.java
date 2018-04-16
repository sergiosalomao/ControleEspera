package br.com.sistema.test;

import br.com.sistema.controller.CompanyDAO;
import br.com.sistema.controller.SectorDAO;
import br.com.sistema.controller.ServiceDAO;
import br.com.sistema.error.ErroSistema;
import br.com.sistema.model.Company;
import br.com.sistema.model.Sector;
import br.com.sistema.model.Service;

public class TestConection {

	public static void main(String[] args) throws Exception {
	
		Sector sec =  new Sector();
		
		sec.setSectorname("testesassss");
		
		SectorDAO dao = new SectorDAO();
		dao.save(sec);
		
	

	}

}
