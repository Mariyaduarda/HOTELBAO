package hotebao.service.interf;

import hotebao.dto.Response;
import hotebao.entity.EstadiaEntity;

public interface InterfaceEstadiaService {

    Response salvarEstadia(Long idQuarto, Long idUsuario, EstadiaEntity estadiaRequest);

    Response findEstadiaByConfirmationCode(String confirmationCode);

    Response listarTodasEstadias();

    Response buscarEstadiaPorId(Long idEstadia);

    Response getEstadia();

    Response cancelarEstadia(Long idEstadia);

    Response getAllEstadias();

    Response getEstadiaByConfirmationCode(String confirmationCode);

    Response getAllEstadia(Long id);
}