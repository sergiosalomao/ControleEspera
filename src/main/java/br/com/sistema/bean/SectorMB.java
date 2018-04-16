package br.com.sistema.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.sistema.controller.SectorDAO;
import br.com.sistema.model.Sector;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "sectorMB")
@ViewScoped
public class SectorMB extends CrudBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sector sector;
	private SectorDAO daoSector;
	private List<Sector> list_sector;
	private Sector selected_sector;
	private Map list_sector_map = null;

	
	/* Capturar os nomes dos Bancos */
	public Map getListSector() throws Exception {
		SectorDAO dao = new SectorDAO();
		list_sector_map = new LinkedHashMap();
		for (Iterator iter = dao.findAll().iterator(); iter.hasNext();) {
			Sector f = (Sector) iter.next();
			// armazenando os cargos encontrados no bd em um map
			list_sector_map.put(f.getSectorname(), f.getSectorname());
		}
		return list_sector_map;
	}
	
	
	
	
	
	public SectorMB() {
		sector = new Sector(); // cria
		daoSector = new SectorDAO();
		try {
			list_sector = daoSector.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void prepareInsert() {

		try {
			AlterInsert();
			sector = new Sector(); // cria
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prepareEdit() {

		try {
			AlterEdit();
			sector = daoSector.find(selected_sector);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() throws Exception {
		try {
			if (IsInsert()) {
				daoSector.save(sector);
				list_sector = daoSector.findAll();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso.", null));
			}
			if (IsEdit()) {

				daoSector.update(sector);
				list_sector = daoSector.findAll();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com sucesso.", null));

				
			}

		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sector = null;
			AlterFind();
			list_sector = daoSector.findAll();
		}

	}

	public void cancel() {

		sector = null;
		AlterFind();

	}

	public void delete() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			daoSector.delete(selected_sector);
			list_sector = daoSector.findAll();
			sector = null;
			AlterFind();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Excluido com sucesso..", null));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
	}

	/* <---- Getters and Setters---> */

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public List<Sector> getList_sector() {
		return list_sector;
	}

	public void setList_sector(List<Sector> list_sector) {
		this.list_sector = list_sector;
	}

	public Sector getSelected_sector() {
		return selected_sector;
	}

	public void setSelected_sector(Sector selected_sector) {
		this.selected_sector = selected_sector;
	}
	public Map getList_sector_map() {
		return list_sector_map;
	}

	public void setList_sector_map(Map list_sector_map) {
		this.list_sector_map = list_sector_map;
	}
}
