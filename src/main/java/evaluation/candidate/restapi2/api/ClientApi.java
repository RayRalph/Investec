package evaluation.candidate.restapi2.api;

import evaluation.candidate.restapi2.model.Client;
import evaluation.candidate.restapi2.model.ClientCreate;
import evaluation.candidate.restapi2.model.ClientUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Api(value = "client", description = "the client API")
public interface ClientApi {

    @ApiOperation(value = "Creates a client", nickname = "createClient", notes = "This operation creates a Client entity.", 
            response = Client.class, tags={ "client", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created", response = Client.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    @RequestMapping(value = "/client",
        produces = { "application/json;charset=utf-8" }, 
        consumes = { "application/json;charset=utf-8" },
        method = RequestMethod.POST)
    ResponseEntity<Client> createClient(@ApiParam(value = "The Client to be created" ,required=true )  @Valid @RequestBody ClientCreate client);


    @ApiOperation(value = "Deletes a Client", nickname = "deleteClient", notes = "This operation deletes a client entity.", tags={ "client", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Deleted"),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    @RequestMapping(value = "/clientDelete/{idnumber}",
        produces = { "application/json;charset=utf-8" }, 
        consumes = { "application/json;charset=utf-8" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteClient(@ApiParam(value = "Identifier of the Client",required=true) @PathVariable("idnumber") String idnumber);


    @ApiOperation(value = "Updates partially a Client", nickname = "patchClient", notes = "This operation updates partially a Client entity.", 
            response = Client.class, tags={ "client", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Updated", response = Client.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 406, message = "Not Acceptable", response = Error.class),
        @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    @RequestMapping(value = "/clientUpdate/{idnumber}",
        produces = { "application/json;charset=utf-8" }, 
        consumes = { "application/json;charset=utf-8" },
        method = RequestMethod.PATCH)
    ResponseEntity<Client> updateClient(@ApiParam(value = "Identifier of the Client",required=true) @PathVariable("idnumber") String idnumber,
            @ApiParam(value = "The Client to be updated" ,required=true )  @Valid @RequestBody ClientUpdate client);


    @ApiOperation(value = "Retrieves a Client by ID number", nickname = "getClientByIDNumber", 
            notes = "This operation retrieves a Client entity. Attribute selection is enabled for all first level attributes.", 
            response = Client.class, tags={ "client", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Client.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    @RequestMapping(value = "/clientID/{idnumber}",
        produces = { "application/json;charset=utf-8" }, 
        consumes = { "application/json;charset=utf-8" },
        method = RequestMethod.GET)
    ResponseEntity<Client> getClientByIDNumber(@ApiParam(value = "ID Number of the client") @Valid 
    @RequestParam(value = "fields", required = false) String idnumber);


    @ApiOperation(value = "Retrieves a Client by their mobile number", nickname = "getClientByMobileNumber", 
            notes = "This operation retrieves a Client entity. Attribute selection is enabled for all first level attributes.", 
            response = Client.class, tags={ "client", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Client.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    @RequestMapping(value = "/clientPhone/{mobilenumber}",
        produces = { "application/json;charset=utf-8" }, 
        consumes = { "application/json;charset=utf-8" },
        method = RequestMethod.GET)
    ResponseEntity<Client> getClientByMobileNumber(@ApiParam(value = "Mobile number of the Client",required=true) 
    @PathVariable("mobilenumber") String mobilenumber);

    @ApiOperation(value = "List or find Client objects by their first name", nickname = "getClientByFirstName", 
            notes = "This operation list or find Client entities", response = Client.class, responseContainer = "List", tags={ "client", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Client.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    @RequestMapping(value = "/clientName/{firstname}",
        produces = { "application/json;charset=utf-8" }, 
        consumes = { "application/json;charset=utf-8" },
        method = RequestMethod.GET)
    ResponseEntity<List<Client>> getClientByFirstName(@ApiParam(value = "Name of the Client",required=true) @PathVariable("firstname")
            String firstname);

}
