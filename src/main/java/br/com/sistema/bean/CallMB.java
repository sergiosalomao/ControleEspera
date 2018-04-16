package br.com.sistema.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.sistema.controller.CallDAO;
import br.com.sistema.controller.SectorDAO;
import br.com.sistema.model.Call;
import br.com.sistema.model.Sector;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "callMB")
@ViewScoped
public class CallMB extends CrudBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Call call;
	private CallDAO daoCall;
	private List<Call> list_call;
	private Call selected_call;
	

	public CallMB() {
		call = new Call(); // cria
		daoCall = new CallDAO();
		try {
			list_call = daoCall.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void prepareInsert() {

		try {
			
			AlterInsert();
			call = new Call(); // cria
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prepareEdit() {

		try {
			AlterEdit();
			call = daoCall.find(selected_call);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void GeraSenha() throws Exception {
		
	}
	public void save() throws Exception {
		try {
			if (IsInsert()) {
				
				/*Pega a Primeira letra*/
				char letra =  call.getService().charAt(0);
				
				/*Pega a Hora */
				GregorianCalendar calendar = new GregorianCalendar(); 
				int hora = calendar.get(calendar.HOUR_OF_DAY);
				int min = calendar.get(Calendar.MINUTE);
				int sec = calendar.get(Calendar.SECOND);

			
				String h = (hora + ":" + min + ":" + sec);
				
				
				
				call.setHour(""+ h);
				call.setNumber("" + letra + list_call.size());
				
				
				
				daoCall.save(call);
				list_call = daoCall.findAll();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso.", null));
			}
			if (IsEdit()) {

				daoCall.update(call);
				list_call = daoCall.findAll();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com sucesso.", null));

				
			}

		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			call = null;
			AlterFind();
			list_call = daoCall.findAll();
		}

	}

	public void cancel() {

		call = null;
		AlterFind();
		
	}

	public void delete() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			daoCall.delete(selected_call);
			list_call = daoCall.findAll();
			call = null;
			AlterFind();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Excluido com sucesso..", null));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
	}

	/* <---- Getters and Setters---> */

	public Call getCall() {
		return call;
	}

	public void setCall(Call call) {
		this.call = call;
	}

	public List<Call> getList_call() {
		return list_call;
	}

	public void setList_call(List<Call> list_call) {
		this.list_call = list_call;
	}

	public Call getSelected_call() {
		return selected_call;
	}

	public void setSelected_call(Call selected_call) {
		this.selected_call = selected_call;
	}
	




}
