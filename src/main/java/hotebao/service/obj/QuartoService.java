package hotebao.service.obj;

import hotebao.dto.QuartoDTO;
import hotebao.dto.Response;
import hotebao.entity.QuartoEntity;
import hotebao.exception.OurException;
import hotebao.repository.EstadiaRepository;
import hotebao.repository.QuartoRepository;
import hotebao.service.interf.InterfaceQuartoService;
import hotebao.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class QuartoService implements InterfaceQuartoService {

    @Autowired
    private QuartoRepository quartoRepository;
    @Autowired
    private EstadiaRepository estadiaRepository;

    // Configuração do diretório de upload
    @Value("${upload.directory:uploads/}")
    private String uploadDirectory;

    @Override
    public List<String> getQuartosByTipo(QuartoEntity.TipoQuarto quartoTipo) {
        Response response = new Response();

        try {
            List<QuartoEntity> quartos = quartoRepository.findByTipoQuarto(quartoTipo);
            List<QuartoDTO> quartoDTOList = Utils.mapQuartoEntityListToQuartoDTOList(quartos);

            response.setMessage("sucesso");
            response.setStatusCodigo(200);
            response.setQuartoDTOList(quartoDTOList);

        } catch (Exception e) {
            response.setStatusCodigo(500);
            response.setMessage("Erro ao buscar quartos por tipo: " + e.getMessage());
        }

        return (List<String>) response;
    }

    @Override
    public Response addNovoQuarto(MultipartFile imagem, QuartoEntity.TipoQuarto tipoQuarto, BigDecimal preco, String descricao) throws Exception {
        Response response = new Response();

        try {
            String imagemUrl = null;

            // Processar imagem se fornecida
            if (imagem != null && !imagem.isEmpty()) {
                imagemUrl = salvarImagem(imagem);
            }

            // bora criar novo quarto
            QuartoEntity novoQuarto = new QuartoEntity();
            novoQuarto.setTipoQuarto(tipoQuarto);
            novoQuarto.setPreco(preco);
            novoQuarto.setDescricao(descricao);
            novoQuarto.setImagem(imagemUrl);

            // setar nome baseado no tipo (campo obrigatório)
            novoQuarto.setNome("Quarto " + tipoQuarto.getDescricao());

            // salvar no banco
            QuartoEntity quartoSalvo = quartoRepository.save(novoQuarto);
            QuartoDTO quartoDTO = Utils.mapQuartoEntityToQuartoDTO(quartoSalvo);

            response.setStatusCodigo(200);
            response.setQuartoDTO(quartoDTO);
            response.setMessage("Quarto adicionado com sucesso");

        } catch (Exception e) {
            response.setStatusCodigo(500);
            response.setMessage("Erro interno: " + e.getMessage());
            throw e; // re-throw para a interface
        }

        return response;
    }

    private String salvarImagem(MultipartFile imagem) throws IOException {
        // criar diretório se não existir
        Path uploadPath = Paths.get(uploadDirectory);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // gerar nome único para a imagem
        String nomeOriginal = imagem.getOriginalFilename();
        String extensao = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
        String nomeUnico = UUID.randomUUID().toString() + extensao;

        // salvar arquivo
        Path caminhoArquivo = uploadPath.resolve(nomeUnico);
        Files.write(caminhoArquivo, imagem.getBytes());

        // retornar URL relativa
        return "/uploads/" + nomeUnico;
    }

    @Override
    public Response getAllQuartos(){
        Response response = new Response();

        try {
            List<QuartoEntity> quartoListEntity = quartoRepository.findAll(Sort.by(Sort.Direction.DESC, "idQuarto"));
            List<QuartoDTO> quartoDTOList = Utils.mapQuartoEntityListToQuartoDTOList(quartoListEntity);

            response.setMessage("sucesso");
            response.setStatusCodigo(200);
            response.setQuartoDTOList(quartoDTOList);

        } catch (RuntimeException e) {
            response.setStatusCodigo(500); // Fixed: changed from 200 to 500
            response.setMessage("Erro interno: " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteQuarto (long idQuarto){
        Response response = new Response();

        try {
            quartoRepository.findById(idQuarto).orElseThrow(() -> new OurException("Quarto não encontrado")); // Fixed lambda syntax
            quartoRepository.deleteById(idQuarto);

            response.setMessage("sucesso");
            response.setStatusCodigo(200);

        } catch (RuntimeException e) {
            response.setStatusCodigo(404);
            response.setMessage("Erro ao excluir o quarto: " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response updateQuarto(long idQuarto, String descricao, QuartoEntity.TipoQuarto tipoQuarto, BigDecimal preco, MultipartFile imagem) throws Exception{
        Response response = new Response();

        try {
            String imagemUrl = null;
            if (imagem != null && !imagem.isEmpty()) {
                imagemUrl = salvarImagem(imagem);
            }
            QuartoEntity quarto = quartoRepository.findById(idQuarto).orElseThrow(() -> new OurException("Quarto não encontrado"));
            if(tipoQuarto != null) quarto.setTipoQuarto(tipoQuarto);
            if(preco != null) quarto.setPreco(preco);
            if(descricao != null) quarto.setDescricao(descricao);
            if(imagemUrl != null) quarto.setImagem(imagemUrl);

            QuartoEntity quartoSalvo = quartoRepository.save(quarto);
            QuartoDTO quartoDTO = Utils.mapQuartoEntityToQuartoDTO(quartoSalvo);

            response.setMessage("sucesso");
            response.setStatusCodigo(200);
            response.setQuartoDTO(quartoDTO);

        } catch (RuntimeException e) {
            response.setStatusCodigo(404);
            response.setMessage("Erro ao atualizar o quarto: " + e.getMessage()); // Fixed error message
        }
        return response;
    }

    @Override
    public Response getQuartoById(long idQuarto){
        Response response = new Response();

        try {
            QuartoEntity quarto = quartoRepository.findById(idQuarto).orElseThrow(() -> new OurException("Quarto não encontrado")); // Fixed lambda syntax
            QuartoDTO quartoDTO = Utils.mapQuartoEntityToQuartoDTOPlusEstadia(quarto);

            response.setMessage("sucesso");
            response.setStatusCodigo(200);
            response.setQuartoDTO(quartoDTO);

        } catch (RuntimeException e) {
            response.setStatusCodigo(404);
            response.setMessage("Erro ao buscar o quarto: " + e.getMessage()); // Fixed error message
        }
        return response;
    }

    @Override
    public Response getDisponibilidadeById(long idQuarto, LocalDate dataEntrada, LocalDate dataSaida){
        Response response = new Response();

        try {
            List<QuartoEntity> quartosDisponiveis = quartoRepository.findQuartosDisponiveisByDateAndType(dataEntrada, dataSaida, String.valueOf(idQuarto));
            List<QuartoDTO> quartoDTOList = Utils.mapQuartoEntityListToQuartoDTOList(quartosDisponiveis);

            response.setMessage("sucesso");
            response.setStatusCodigo(200);
            response.setQuartoDTOList(quartoDTOList);

        } catch (RuntimeException e) {
            response.setStatusCodigo(404);
            response.setMessage("Erro ao verificar disponibilidade: " + e.getMessage()); // Fixed error message
        }
        return response;
    }

    // buscar quartos disponíveis em um período
    @Override
    public Response getQuartosDisponiveis(){
        Response response = new Response();

        try {
            List<QuartoEntity> quartosDisponiveis = quartoRepository.findQuartosDisponiveisByDateAndType(dataEntrada, dataSaida, String.valueOf(tipoQuarto)); // Fixed parameter
            List<QuartoDTO> quartoDTOList = Utils.mapQuartoEntityListToQuartoDTOList(quartosDisponiveis);

            response.setMessage("sucesso");
            response.setStatusCodigo(200);
            response.setQuartoDTOList(quartoDTOList);

        } catch (OurException e) {
            response.setStatusCodigo(404);
            response.setMessage("Erro ao buscar quartos disponíveis: " + e.getMessage()); // Fixed error message
        } catch (Exception e) {
            response.setStatusCodigo(500);
            response.setMessage("Erro ao buscar quartos disponíveis: " + e.getMessage()); // Fixed error message
        }
        return response;
    }

    // verificar disponibilidade geral (sem datas específicas)
    @Override
    public Response getDisponibilidadeGeral(LocalDate dataEntrada, LocalDate dataSaida, QuartoEntity.TipoQuarto tipoQuarto){
        Response response = new Response();

        try {
            //List<QuartoEntity> quartoList = quartoRepository.findQuartosDisponiveis();
            List<QuartoEntity> quartoList = quartoRepository.getDisponibilidadeGeral();
            List<QuartoDTO> quartoDTOList = Utils.mapQuartoEntityListToQuartoDTOList(quartoList);

            response.setMessage("sucesso");
            response.setStatusCodigo(200);
            response.setQuartoDTOList(quartoDTOList);

        } catch (Exception e) {
            response.setStatusCodigo(500);
            response.setMessage("Erro ao verificar disponibilidade geral: " + e.getMessage()); // Fixed error message
        }
        return response;
    }

    public Response getQuartosDisponiveis(Long idQuarto) {
        return null;
    }

    public List<String> getTipoQuartos() {

    }
}