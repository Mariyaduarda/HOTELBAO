package hotebao.service.interf;

import hotebao.dto.Response;
import hotebao.entity.EstadiaEntity;

public interface InterfaceEstadiaService {

    Response SalvaEstadia(Long idEstadia, Long idUsuario, EstadiaEntity estadiaRequest);

    Response findEstadiaByConfirmationCode(String confirmationCode);

    Response getEstadia();

    Response cancelarEstadia(Long idEstadia);
}
