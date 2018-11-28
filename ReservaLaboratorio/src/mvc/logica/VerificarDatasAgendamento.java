package mvc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AgendaDAO;
import modelo.Usuario;
import util.Erro;
import util.FormatarDatas;

public class VerificarDatasAgendamento implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		Erro erros = new Erro();
		String url = "mvc?logica=ListaAgendamento";
		String dt_form_inicio = null;
		String dt_form_fim = null;
		String data_inicio = "2018-01-01 00:00:00";
		
		Integer dt_inicio = 0;
		Integer dt_inic = 0;
		Integer dt_fim = 0;
		Integer dtInicialRef = 0;
		Integer areaId = 0;
		Integer laboratorioId = 0;

		Usuario user = (Usuario) req.getSession().getAttribute("usuarioLogado");

		if (user == null || Usuario.totalListaUsuario() == 0 || !Usuario.existeUsuarioLista(user.getId())) {
			req.getSession().invalidate();
			url = "login.jsp";
		} else {
			if (req.getParameter("bOK") != null	&& req.getParameter("bOK").equalsIgnoreCase("BUSCAR")) {
				// verificando e cast das informações
				if (req.getParameter("areaId") != null && !req.getParameter("areaId").equalsIgnoreCase("Selecione")) {
					areaId = Integer.parseInt(req.getParameter("areaId"));
				} else {
					erros.add("Faltando inserir a área!");
				}
				if (req.getParameter("laboratorioId") != null&& !req.getParameter("laboratorioId").equalsIgnoreCase("Selecione")) {
					laboratorioId = Integer.parseInt(req.getParameter("laboratorioId"));
				} else {
					erros.add("Faltando inserir o laboratório!");
				}
				
				if (!erros.isExisteErros()) {
					//recebe e converte as datas
					dt_form_inicio = req.getParameter("dt_inicio");
					dt_form_fim = req.getParameter("dt_fim");
					dt_inic = FormatarDatas.converterStringtoInteger(data_inicio);	
					dt_inicio = FormatarDatas.converterStringtoInteger(dt_form_inicio);
					dt_fim = FormatarDatas.converterStringtoInteger(dt_form_fim);
						
					
					//busca no banco qual a data maxima no intervalo
					AgendaDAO dao = new AgendaDAO();
					Integer dataMaxima = dao.getMaxData(areaId, laboratorioId,  dt_inic, dt_fim);
					
		//se a data retornada for nula, recebe a data inicial inserida na referencia, se não insere a que retornar
					if(dataMaxima == 0) {
						dtInicialRef = dt_inic;
					}else {
						dtInicialRef = FormatarDatas.incrementarData(dataMaxima);
					}
					
		//se a data de referencia for diferente da data final popular o banco com o as datas faltantes
					if(!dtInicialRef.equals(dt_fim)) {
						dao.novoItem(areaId, laboratorioId, dtInicialRef, dt_fim);	
					}
				}
			}	
		}
		
		req.getSession().setAttribute("areaId", areaId);
		req.getSession().setAttribute("laboId", laboratorioId);
		req.getSession().setAttribute("dt_inicio", dt_inicio);
		req.getSession().setAttribute("dt_fim", dt_fim);
		return url;
	}

}
