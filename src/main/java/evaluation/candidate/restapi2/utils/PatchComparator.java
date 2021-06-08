package evaluation.candidate.restapi2.utils;

import evaluation.candidate.restapi2.model.ClientCreate;
import evaluation.candidate.restapi2.model.ClientUpdate;
import org.springframework.stereotype.Component;


@Component
public class PatchComparator {
    public ClientCreate compareForPatch(ClientCreate dto, ClientUpdate update){
        dto.setFirstName((null != update.getFirstName())?update.getFirstName():dto.getFirstName());
        dto.setLastName((null != update.getLastName())?update.getLastName():dto.getLastName());
        dto.setMobileNumber((null != update.getMobileNumber())?update.getMobileNumber():dto.getMobileNumber());
        dto.setIDNumber((null != update.getIDNumber())?update.getIDNumber():dto.getIDNumber());
        dto.setPhysicalAddress((null != update.getPhysicalAddress())?update.getPhysicalAddress():dto.getPhysicalAddress());
        return dto;
    }
}
