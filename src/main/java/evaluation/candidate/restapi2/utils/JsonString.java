package evaluation.candidate.restapi2.utils;

import evaluation.candidate.restapi2.data.dao.ClientDao;
import org.springframework.stereotype.Component;

@Component
public class JsonString {

    public String toJsonString(ClientDao client) {
        String value = String.format("{  \"firstname\" : \"%s\",  \"lastname\" : \"%s\",  " +
                        "\"mobilenumber\" : \"%s\",  \"idnumber\" : \"%s\",  \"physicaladdress\" : \"%s\"}",
                client.getFirstName(), client.getLastName(), client.getMobileNumber(), client.getIDNumber(), client.getPhysicalAddress());
        return value;
    }

    public String toParameterizedJsonString(ClientDao client) {
        StringBuilder sb = new StringBuilder("{");
        if(null != client.getFirstName()){
            sb.append("  \"firstname\" : \"").append(client.getFirstName()).append("\", ");
        }
        if(null != client.getLastName()){
            sb.append("  \"lastname\" : \"").append(client.getLastName()).append("\", ");
        }
        if(null != client.getMobileNumber()){
            sb.append("  \"mobilenumber\" : \"").append(client.getMobileNumber()).append("\", ");
        }
        if(null != client.getIDNumber()){
            sb.append("  \"idnumber\" : \"").append(client.getIDNumber()).append("\", ");
        }
        if(null != client.getPhysicalAddress()){
            sb.append("  \"physicaladdress\" : \"").append(client.getPhysicalAddress()).append("\", ");
        }
        String str = sb.toString();
        str = str.substring(0, str.lastIndexOf(","));
        return str.concat(" }");
    }

}
