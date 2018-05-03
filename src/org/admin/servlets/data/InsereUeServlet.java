package org.admin.servlets.scolarite;

import org.admin.servlets.BaseServlet;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.admin.beans.scolarite.Mention;
import org.admin.beans.scolarite.Parcours;
import org.admin.beans.scolarite.Ue;

import org.admin.dao.ParcoursDao;
import org.admin.dao.MentionDao;
import org.admin.dao.DaoException;
import org.admin.dao.DaoFactory;
import org.admin.dao.UeDao;
import java.util.List;
import org.admin.beans.scolarite.Ue;

@WebServlet("/ajoutUe")
public class InsereUeServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	private MentionDao mentionDao;
	private ParcoursDao parcoursDao;
	private UeDao ueDao;

	public InsereUeServlet(){
		super();
	}

	public void init(){

		DaoFactory daoFactory = DaoFactory.getInstance();
		mentionDao = daoFactory.getMentionDao();
		parcoursDao = daoFactory.getParcoursDao();
		ueDao = daoFactory.getUeDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("base_url",base_url);
		String content = "../preinscription/list_parcours.jsp";
		request.setAttribute ("content",content);
		request.setAttribute ("title", "Parcours");

		String nomUe = request.getParameter("nomUe");
		float credit = Float.parseFloat(request.getParameter("credit"));
		// int credit = Integer.parseInt(request.getParameter("credit"));
		int id_parcours = Integer.parseInt(request.getParameter("id_parcours"));
		String nom_Resp = request.getParameter("nomResp");
		String niveau = request.getParameter("niveau");
		int id_mention = Integer.parseInt(request.getParameter("mention"));
		String semestre = request.getParameter("semestre");
		String sem = "S1";

		//Condition semestre en fonction de chaque niveau
		//System.out.println(semestre);
		if(semestre.equals("impair")){
			if(niveau.equals("L1")){
				sem = "S1";
			}
			if(niveau.equals("L2")){
				sem = "S3";
			}
			if(niveau.equals("L3")){
				sem = "S5";
			}
			if(niveau.equals("M1")){
				sem = "S7";
			}
			if(niveau.equals("M2")){
				sem = "S9";
			}
			System.out.println("Semestre impair");
		}
		
		if(semestre.equals("pair")){
			if(niveau.equals("L1")){
				sem = "S2";
			}
			if(niveau.equals("L2")){
				sem = "S4";
			}
			if(niveau.equals("L3")){
				sem = "S6";
			}
			if(niveau.equals("M1")){
				sem = "S8";
			}
			if(niveau.equals("M2")){
				sem = "S10";
			}
			System.out.println("Semestre pair");
		}

		
  
		System.out.println(nomUe+"   " + credit+"   "+id_parcours+" "+nom_Resp+" "+niveau + " " + sem);

		try
		{
			HttpSession session = request.getSession();
			if(session.getAttribute("utilisateur") == null){
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/login.jsp").forward(request, response);
			}else
			{
				try{
					ueDao.addUe(nomUe,niveau,id_parcours,nom_Resp,credit, id_mention, sem);
					System.out.println("mety ka tafiditra");
				}
				catch(DaoException e){
					// throw DaoException("eerreur "+e.getMessage());
					System.out.println("efa manomboka");
				}
				 
			}
			System.out.println("id_mention = " + id_mention);
			List<Parcours> parcours = parcoursDao.getAllParcours(id_mention);
			List<Ue> ue = ueDao.getAllUeByMention(id_mention);
			request.setAttribute("ue", ue);
			request.setAttribute("parcours", parcours);
			
		}catch(DaoException e){
			request.setAttribute("erreur", e.getMessage());
		}
		
		

		//response.sendRedirect("/admin/liste_parcours");
		this.getServletContext().getRequestDispatcher("/WEB-INF/layout/admin.jsp").forward(request, response);

	}
}