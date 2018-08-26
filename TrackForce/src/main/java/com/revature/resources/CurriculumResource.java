package com.revature.resources;
import com.revature.entity.TfCurriculum;
import com.revature.services.CurriculumService;
import com.revature.services.JWTService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;
import static com.revature.utils.LogUtil.logger;

/** @version v6.18.06.13 */
@Path("/skillset")
@Api(value = "skillset")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CurriculumResource
{
	// You're probably thinking, why would you ever do this? Why not just just make the methods all static in the service class?
	// This is to allow for Mockito tests, which have problems with static methods
	// - Adam 06.18.06.13
	private CurriculumService curriculumService = new CurriculumService();

	/** @author Adam L.
	 * @version v6.18.06.13 */
	@GET
	@ApiOperation(value = "Returns all curriculums", notes = "Returns a list of all curriculums.")
	public Response getAllCurriculums(@HeaderParam("Authorization") String token) {
		logger.info("getAllCurriculums()...");
		Status status;
		List<TfCurriculum> curriculum = curriculumService.getAllCurriculums();
		Claims payload = JWTService.processToken(token);
		if (payload == null)  // invalid token
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		else if (payload.getId().equals("5"))  // wrong roleid
			return Response.status(Status.FORBIDDEN).build();
		else
			status = curriculum == null || curriculum.isEmpty() ? Status.NO_CONTENT : Status.OK;
		return Response.status(status).entity(curriculum).build();
	}

	@GET
	@ApiOperation(value = "Gets how many unmapped are in each curriculum (excluding empties)",
			notes="Gets how many unmapped are in each curriculum (excluding empties)")
	@Path("/unmapped/{statusId}")
	public Response getUnmappedInfo(@HeaderParam("Authorization") String token, @PathParam("statusId") int statusId) {
		logger.info("getUnmappedInfo()...");
		Claims payload = JWTService.processToken(token);
		
		if (payload == null)  // invalid token
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		else if (!(payload.getId().equals("1") || payload.getId().equals("3") || payload.getId().equals("4")))
			return Response.status(Status.FORBIDDEN).build(); // wrong roleid
		else
			return Response.ok(curriculumService.getUnmappedInfo(statusId)).build();
	}
}