package com.doodlemars.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.doodlemars.dao.BodyModelDAO;
import com.doodlemars.dao.BodyVarientDAO;
import com.doodlemars.db.DBException;
import com.doodlemars.dto.VarientDTO;
import com.doodlemars.utils.JsonGenerator;
import com.doodlemars.utils.Logger;


/**
 * Servlet implementation class PathVarients
 */
@WebServlet("/api/v1/BodyModels")
public class BodyModels extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = new Logger("BodyModels");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BodyModels() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.getWriter().append(JsonGenerator.generateJson(BodyModelDAO.getBodyModels()));
		} catch (DBException e) {
			logger.error(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String inpStr = IOUtils.toString(request.getReader());
			VarientDTO mod = (VarientDTO) JsonGenerator.generateTOfromJson(inpStr, VarientDTO.class);
			BodyModelDAO.addBodyModels(mod);
			response.getWriter().append(JsonGenerator.generateJson(BodyModelDAO.getBodyModels()));
		} catch (DBException e) {
			logger.error(e);
		}
		
	}

}
