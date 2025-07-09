package hotebao.service.obj;

import hotebao.dto.EstadiaDTO;
import hotebao.dto.Response;
import hotebao.entity.EstadiaEntity;
import hotebao.entity.QuartoEntity;
import hotebao.entity.UsuarioEntity;
import hotebao.exception.OurException;
import hotebao.repository.EstadiaRepository;
import hotebao.repository.QuartoRepository;
import hotebao.repository.UsuarioRepository;
import hotebao.service.interf.InterfaceEstadiaService;
import hotebao.service.interf.InterfaceQuartoService;
import hotebao.utils.Utils;
import jdk.jshell.execution.Util;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EstadiaService implements InterfaceEstadiaService {

    @Autowired
    private EstadiaRepository estadiaRepository;

    @Autowired
    private InterfaceQuartoService quartoService;

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Corrigido: era UsuarioRepository

    @Override
    public Response SalvaEstadia(Long idQuarto, Long idUsuario, EstadiaEntity estadiaRequest){
        Response response = new Response();

        try {
            // Corrigido: lógica da data invertida
            if(estadiaRequest.getDataEntrada().isAfter(estadiaRequest.getDataSaida())){
                throw new IllegalArgumentException("Data de entrada deve ser antes da data de saída");
            }

            // Corrigido: idQuarto ao invés de idEstadia
            QuartoEntity quarto = quartoRepository.findById(idQuarto).orElseThrow(() -> new OurException("Quarto não encontrado"));
            UsuarioEntity usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new OurException("Usuario não encontrado"));

            List<EstadiaEntity> existingEstadia = quarto.getEstadia();
            //virou um boolean - codigo mais legivel
            if(!(boolean) quartoIsDisponivel(estadiaRequest, existingEstadia)){
                throw new OurException("O quarto não está disponivel para a data desejada"); // Corrigido: ponto e vírgula
            }

            // Configurar a estadia
            estadiaRequest.setQuarto(quarto);
            estadiaRequest.setUsuario(usuario);

            // Salvar a estadia
            EstadiaEntity estadiaSalva = estadiaRepository.save(estadiaRequest);

            response.setMessage("Estadia salva com sucesso");
            response.setStatusCodigo(200);
            // Você pode adicionar um DTO da estadia na response se necessário

        } catch (IllegalArgumentException e) {
            response.setStatusCodigo(400);
            response.setMessage("Erro de validação: " + e.getMessage());
        } catch (OurException e) {
            response.setStatusCodigo(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCodigo(500);
            response.setMessage("Erro interno: " + e.getMessage());
        }
        return response;
    }

    private Object quartoIsDisponivel(EstadiaEntity estadiaRequest, List<EstadiaEntity> existingEstadia) {
        return null;
    }

    private boolean quartoIsDisponivelDetalhado(EstadiaEntity estadiaRequest, List<EstadiaEntity> existingEstadias) {
        LocalDate novaEntrada = estadiaRequest.getDataEntrada();
        LocalDate novaSaida = estadiaRequest.getDataSaida();

        return existingEstadias.stream()
                .noneMatch(existingEstadia -> {
                    LocalDate entradaExistente = existingEstadia.getDataEntrada();
                    LocalDate saidaExistente = existingEstadia.getDataSaida();

                    // verifica todos os cenários de sobreposição - overlap
                    // 1. nova estadia começa durante uma existente
                    boolean comecaDurante = novaEntrada.isBefore(saidaExistente) &&
                            novaEntrada.isAfter(entradaExistente.minusDays(1));

                    // 2. nova estadia termina durante uma existente
                    boolean terminaDurante = novaSaida.isAfter(entradaExistente) &&
                            novaSaida.isBefore(saidaExistente.plusDays(1));

                    // 3. nova estadia engloba completamente uma existente
                    boolean englobaCompleta = novaEntrada.isBefore(entradaExistente.plusDays(1)) &&
                            novaSaida.isAfter(saidaExistente.minusDays(1));

                    // 4. nova estadia está completamente dentro de uma existente
                    boolean dentroDeExistente = novaEntrada.isAfter(entradaExistente.minusDays(1)) &&
                            novaSaida.isBefore(saidaExistente.plusDays(1));

                    return comecaDurante || terminaDurante || englobaCompleta || dentroDeExistente;
                });
    }

    @Override
    public Response findEstadiaByConfirmationCode(String confirmationCode){
        Response response = new Response();

        try {
            EstadiaEntity estadia = estadiaRepository.findEstadiaEntityConfirmationCode(confirmationCode)
                    .orElseThrow(() -> new OurException("Estadia não encontrada com este código de confirmação"));

            EstadiaDTO estadiaDTO = Utils.mapEstadiaEntityToEstadiaDTOPlusEstadiaQuarto(estadia, true);

            response.setMessage("Estadia encontrada com sucesso");
            response.setStatusCodigo(200);
            response.setEstadiaDTO(estadiaDTO);

        } catch (OurException e) {
            response.setStatusCodigo(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCodigo(500);
            response.setMessage("Erro tentando adicionar a estadia de confirmação: " + e.getMessage());
        }

        return response;
    }

    @Override
    public Response getEstadia(){
        Response response = new Response();

        try {
            List<EstadiaEntity> estadiaList = estadiaRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            List<EstadiaDTO> estadiaDTO = Utils.mapEstadiaEntityListToEstadiaDTOList(estadiaList);

            response.setMessage("Sucesso nas estadias");
            response.setStatusCodigo(200);
            response.setEstadiaEntityList(estadiaDTO);

        } catch (OurException e) {
            response.setStatusCodigo(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCodigo(500);
            response.setMessage("Erro ao registrar as estadias: " + e.getMessage());
        }

        return response;
    }

    @Override
    public Response cancelarEstadia(Long idEstadia){
        Response response = new Response();

        try {
            estadiaRepository.findById(idEstadia).orElseThrow(() -> new OurException("Estadia não encontrada"));
            estadiaRepository.deleteById(idEstadia);

            response.setMessage("Estadia cancelada com sucesso");
            response.setStatusCodigo(200);

        } catch (OurException e) {
            response.setStatusCodigo(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCodigo(500);
            response.setMessage("Erro interno: " + e.getMessage());
        }

        return response;
    }
}