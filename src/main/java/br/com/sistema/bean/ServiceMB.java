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
import br.com.sistema.controller.ServiceDAO;
import br.com.sistema.model.Sector;
import br.com.sistema.model.Service;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "serviceMB")
@ViewScoped
public class ServiceMB extends CrudBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Service service;
	private ServiceDAO daoService;
	private List<Service> list_service;
	private Service selected_service;
	private Map list_service_map = null;

	/* Capturar os nomes dos Bancos */
	public Map getListService() throws Exception {
		ServiceDAO dao = new ServiceDAO();
		list_service_map = new LinkedHashMap();
		for (Iterator iter = dao.findAll().iterator(); iter.hasNext();) {
			Service f = (Service) iter.next();
			// armazenando os cargos encontrados no bd em um map
			list_service_map.put(f.getServiceName(), f.getServiceName());
		}
		return list_service_map;
	}

	public ServiceMB() {
		service = new Service(); // cria
		daoService = new ServiceDAO();
		try {
			list_service = daoService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void prepareInsert() {

		try {
			AlterInsert();
			service = new Service(); // cria
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prepareEdit() {

		try {
			AlterEdit();
			service = daoService.find(selected_service);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() throws Exception {
		try {
			if (IsInsert()) {
				daoService.save(service);
				list_service = daoService.findAll();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso.", null));
			}
			if (IsEdit()) {

				daoService.update(service);
				list_service = daoService.findAll();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com sucesso.", null));

			}

		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			service = null;
			AlterFind();
			list_service = daoService.findAll();
		}

	}

	public void cancel() {

		service = null;
		AlterFind();

	}

	public void delete() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			daoService.delete(selected_service);
			list_service = daoService.findAll();
			service = null;
			AlterFind();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Excluido com sucesso..", null));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
	}

	/* <---- Getters and Setters---> */

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public List<Service> getList_service() {
		return list_service;
	}

	public void setList_service(List<Service> list_service) {
		this.list_service = list_service;
	}

	public Service getSelected_service() {
		return selected_service;
	}

	public void setSelected_service(Service selected_service) {
		this.selected_service = selected_service;
	}
	public Map getList_service_map() {
		return list_service_map;
	}

	public void setList_service_map(Map list_service_map) {
		this.list_service_map = list_service_map;
	}
}
