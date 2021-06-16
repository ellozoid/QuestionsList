package com.questionsList.service.api;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.questionsList.service.interfaces.Repository;
import com.questionsList.service.repository.RepositoryFactory;
import com.sun.jersey.api.client.ClientResponse.Status;

@Path("/questions")
public class QuestionsApiService {
	
		@Path("/search")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getQuestions(
				@DefaultValue("se") @QueryParam("questionService") String questionService,
				@DefaultValue("") @QueryParam("query") String query,
				@DefaultValue("1") @QueryParam("page") int page,
				@DefaultValue("10") @QueryParam("pageSize") int pageSize) {
			Response response = Response.status(Status.OK).build();
			try {
				RepositoryFactory repoFactory = new RepositoryFactory(); 
				Repository repository = repoFactory.createRepository(questionService);
				JSONObject jsonArray = repository.getQuestions(query, page, pageSize);
				response = Response.status(Status.OK).entity(jsonArray.toString()).build();
			} 
			catch (IllegalArgumentException ex) {
				response = Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
				System.out.print(ex.getMessage());
			}
			catch (Exception ex) {
				response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
				System.out.print(ex.getMessage());
			}

			return response;
		}
}
