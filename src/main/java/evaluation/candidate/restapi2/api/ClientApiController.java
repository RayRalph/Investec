package evaluation.candidate.restapi2.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import evaluation.candidate.restapi2.data.dao.ClientDao;
import evaluation.candidate.restapi2.data.persistance.ClientRepository;
import evaluation.candidate.restapi2.exceptions.ApiException;
import evaluation.candidate.restapi2.exceptions.NotFoundException;
import evaluation.candidate.restapi2.model.Client;
import evaluation.candidate.restapi2.model.ClientCreate;
import evaluation.candidate.restapi2.model.ClientUpdate;
import evaluation.candidate.restapi2.service.ClientService;
import evaluation.candidate.restapi2.utils.IdNumberValidator;
import evaluation.candidate.restapi2.utils.JsonString;
import evaluation.candidate.restapi2.utils.PatchComparator;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientApiController implements ClientApi {

    private static final Logger log = LoggerFactory.getLogger(ClientApiController.class);
    private static final String ACCEPT = "Accept";
    private static final String NOT_FOUND_MSG = "Unable to find an Client with the id of: [%s]";
    private static final String REQUIRED_FIELD_EMPTY_MSG = "Required field [%s] has not been provided";
    private static final String JSON = "application/json";
    private static final String SERIALIZE_ERROR = "Couldn't serialize response for content type application/json";

    @Autowired
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Autowired
    private JsonString jsonString;
    @Autowired
    private final ClientRepository clientRepository;
    @Autowired
    PatchComparator comparator;
    @Autowired
    IdNumberValidator idnumberValidator;

    @Autowired
    private final ClientService clientService;

    @org.springframework.beans.factory.annotation.Autowired
    public ClientApiController(ObjectMapper objectMapper, HttpServletRequest request, ClientRepository clientRepository, ClientService clientService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public ResponseEntity<Client> createClient(@ApiParam(value = "The Client to be created", required = true) @Valid @RequestBody ClientCreate client) {
        String accept = request.getHeader(ACCEPT);
        if (accept != null && accept.contains(JSON)) {
            if (null == client.getFirstName() || client.getFirstName().isEmpty()) {
                throw new ApiException(HttpStatus.BAD_REQUEST.value(), String.format(REQUIRED_FIELD_EMPTY_MSG, "firstname"));
            }

            if (null == client.getLastName() || client.getLastName().isEmpty()) {
                throw new ApiException(HttpStatus.BAD_REQUEST.value(), String.format(REQUIRED_FIELD_EMPTY_MSG, "lastname"));
            }

            if (null == client.getIDNumber() || client.getIDNumber().isEmpty()) {
                throw new ApiException(HttpStatus.BAD_REQUEST.value(), String.format(REQUIRED_FIELD_EMPTY_MSG, "idnumber"));
            } else {
                idnumberValidator.validate(client.getIDNumber());
            }
            try {
                ClientDao entity = ClientCreate.mapModelToEntity(client);
                clientService.saveOrUpdate(entity);
                String json = jsonString.toJsonString(entity);
                return new ResponseEntity<Client>(objectMapper.readValue(json, Client.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error(SERIALIZE_ERROR, e);
                return new ResponseEntity<Client>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Client>(HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "/clientDelete/{idnumber}",
            produces = {"application/json;charset=utf-8"},
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteClient(@ApiParam(value = "Identifier of the Client", required = true) @PathVariable("idnumber") String idnumber) {
        String accept = request.getHeader(ACCEPT);
        if (accept != null && accept.contains(JSON)) {
            ClientDao client = clientService.getClientByIDNumber(idnumber);
            if (null == client) {
                throw new NotFoundException(HttpStatus.NOT_FOUND.value(), String.format(NOT_FOUND_MSG, idnumber));
            }
            clientRepository.deleteById(client.getId());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "/clientUpdate/{idnumber}",
            produces = {"application/json;charset=utf-8"},
            method = RequestMethod.PATCH)
    public ResponseEntity<Client> updateClient(@ApiParam(value = "Identifier of the Client", required = true) @PathVariable("idnumber") String idnumber, 
            @ApiParam(value = "The Client to be updated", required = true) @Valid @RequestBody ClientUpdate client) {
        String accept = request.getHeader(ACCEPT);
        if (accept != null && accept.contains(JSON)) {
            try {
                ClientDao dao = clientService.getClientByIDNumber(idnumber);
                if (null == dao) {
                    throw new NotFoundException(HttpStatus.NOT_FOUND.value(), String.format(NOT_FOUND_MSG, idnumber));
                }
                ClientCreate dto = comparator.compareForPatch(ClientCreate.mapEntityToModel(dao), client);
                ClientDao entity = ClientCreate.mapModelToEntity(dto);
                clientRepository.save(entity);
                return new ResponseEntity<Client>(objectMapper.readValue(jsonString.toJsonString(entity), Client.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error(SERIALIZE_ERROR, e);
                return new ResponseEntity<Client>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Client>(HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "/clientName",
            produces = {"application/json;charset=utf-8"},
            method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getClientByFirstName(@ApiParam(value = "The first name of the client")
            @Valid @RequestParam(value = "firstname", required = true) String firstName) {
        String accept = request.getHeader(ACCEPT);
        if (accept != null && accept.contains(JSON)) {
            if (null == firstName || firstName.isEmpty()) {
                throw new ApiException(HttpStatus.BAD_REQUEST.value(), String.format(REQUIRED_FIELD_EMPTY_MSG, "firstname"));
            } else {

                try {
                    List<ClientDao> clients = new ArrayList();
                    StringBuilder sb = new StringBuilder();
                    clients = clientService.getClientByFirstName(firstName);
                    clients.stream().forEach(ind -> {
                        String map = jsonString.toParameterizedJsonString(ind);
                        sb.append(map).append(", ");
                    });
                    String values = sb.toString();
                    return new ResponseEntity<List<Client>>(objectMapper.readValue(String.format("[ %s ]",
                            values.substring(0, values.lastIndexOf(","))), List.class), HttpStatus.OK);
                } catch (IOException e) {
                    log.error(SERIALIZE_ERROR, e);
                    return new ResponseEntity<List<Client>>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        return new ResponseEntity<List<Client>>(HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "/clientID/{idnumber}",
            produces = {"application/json;charset=utf-8"},
            method = RequestMethod.GET)
    public ResponseEntity<Client> getClientByIDNumber(@ApiParam(value = "Identifier of the Client", required = true)
            @PathVariable("idnumber") String idnumber) {
        String accept = request.getHeader(ACCEPT);
        if (accept != null && accept.contains(JSON)) {
            try {
                ClientDao client = clientService.getClientByIDNumber(idnumber);
                if (null == client) {
                    throw new NotFoundException(HttpStatus.NOT_FOUND.value(), String.format(NOT_FOUND_MSG, idnumber));
                } else {
                    String msg = jsonString.toJsonString(client);
                    return new ResponseEntity<Client>(objectMapper.readValue(msg, Client.class), HttpStatus.OK);
                }
            } catch (IOException e) {
                log.error(SERIALIZE_ERROR, e);
                return new ResponseEntity<Client>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Client>(HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "/clientPhone/{mobilenumber}",
            produces = {"application/json;charset=utf-8"},
            method = RequestMethod.GET)
    public ResponseEntity<Client> getClientByMobileNumber(@ApiParam(value = "Mobile number of the Client", required = true)
            @PathVariable("mobilenumber") String mobileNumber) {
        String accept = request.getHeader(ACCEPT);
        if (accept != null && accept.contains(JSON)) {
            try {
                ClientDao client = clientService.getClientByMobileNumber(mobileNumber);
                if (null == client) {
                    throw new NotFoundException(HttpStatus.NOT_FOUND.value(), String.format(NOT_FOUND_MSG, mobileNumber));
                } else {
                    String msg = jsonString.toJsonString(client);
                    return new ResponseEntity<Client>(objectMapper.readValue(msg, Client.class), HttpStatus.OK);
                }
            } catch (IOException e) {
                log.error(SERIALIZE_ERROR, e);
                return new ResponseEntity<Client>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Client>(HttpStatus.NOT_IMPLEMENTED);
    }

}
