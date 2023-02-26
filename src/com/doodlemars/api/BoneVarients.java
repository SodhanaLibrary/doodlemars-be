package com.doodlemars.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.doodlemars.dao.BoneVarientsDAO;
import com.doodlemars.db.DBException;
import com.doodlemars.dto.VarientDTO;
import com.doodlemars.utils.JsonGenerator;
import com.doodlemars.utils.Logger;


/**
 * Servlet implementation class PathVarients
 */
@WebServlet("/api/v1/BoneVarients")
public class BoneVarients extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = new Logger("BoneVarients");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoneVarients() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			response.getWriter().append(JsonGenerator.generateJson(BoneVarientsDAO.getBoneVarients(name)));
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
			BoneVarientsDAO.addBoneVarients(mod);
			response.getWriter().append(JsonGenerator.generateJson(BoneVarientsDAO.getBoneVarients(mod.getName())));
		} catch (DBException e) {
			logger.error(e);
		}
		
	}

}
