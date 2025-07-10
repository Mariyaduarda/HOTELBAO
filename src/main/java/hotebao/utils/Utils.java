package hotebao.utils;

import hotebao.dto.*;
import hotebao.entity.*;
import java.security.*;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    // String contendo todos os caracteres possíveis para a geração (letras maiúsculas e números)
    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    // gerador de números aleatórios seguro (mais seguro que Random comum)
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    /**
     * gerar uma string aleatória alfanumérica com o comprimento especificado
     * @param length - comprimento da string a ser gerada
     * @return string aleatória contendo apenas letras maiúsculas e números
     */
    public static String generateString(int length) {
        // stringBuffer para construir a string (mais eficiente que concatenação)
        StringBuffer stringBuilder = new StringBuffer();

        // loop para gerar cada caractere da string
        for (int i = 0; i < length; i++) {
            // gera um índice aleatório entre 0 e o tamanho da string de caracteres
            int randomIndex = SECURE_RANDOM.nextInt(ALPHANUMERIC_STRING.length());

            // seleciona o caractere no índice aleatório
            char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);

            // adiciona o caractere ao resultado final
            stringBuilder.append(randomChar);
        }

        // retorna a string gerada
        return stringBuilder.toString();
    }

    /**
     * mapeia UsuarioEntity para UsuarioDTO
     * metodo factory
     */
    public static UsuarioDTO mapUsuarioEntityToUsuarioDTO(UsuarioEntity usuarioEntity) {
        if (usuarioEntity == null) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setIdUser(usuarioEntity.getId());
        usuarioDTO.setLoginUser(usuarioEntity.getLoginUser());
        usuarioDTO.setEmail(usuarioEntity.getEmail());
        usuarioDTO.setSenha(usuarioEntity.getSenha());
        usuarioDTO.setCpf(usuarioEntity.getCpf());
        usuarioDTO.setEstadia(new ArrayList<>());

        usuarioDTO.generateSessionToken();

        return usuarioDTO;
    }

    /**
     * mapeia QuartoEntity para QuartoDTO
     */
    public static QuartoDTO mapQuartoEntityToQuartoDTO(QuartoEntity quartoEntity){

        if (quartoEntity == null) {
            return null;
        }

        QuartoDTO quartoDTO = new QuartoDTO();

        quartoDTO.setIdQuarto(quartoEntity.getId());
        quartoDTO.setNome(quartoEntity.getNome());
        quartoDTO.setPreco(quartoEntity.getPreco());
        quartoDTO.setImagem(quartoEntity.getImagem());
        quartoDTO.setDescricao(quartoEntity.getDescricao());
        quartoDTO.setEstadia(new ArrayList<>());

        quartoDTO.generateSessionToken();

        return quartoDTO;
    }

    /**
     * mapeia ClienteEntity para ClienteDTO
     */
    public static ClienteDTO mapClienteEntityToClienteDTO(ClienteEntity clienteEntity) {

        if (clienteEntity == null) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setIdCliente(clienteEntity.getId());
        clienteDTO.setNome(clienteEntity.getNome());
        clienteDTO.setCpf(clienteEntity.getCpf());
        clienteDTO.setTelefone(clienteEntity.getTelefone());
        clienteDTO.setEmail(clienteEntity.getEmail());

        clienteDTO.setEstadia(new ArrayList<>());

        // cliente provavelmente não precisa de token de sessão
        clienteDTO.generateSessionToken();

        return clienteDTO;
    }

    /**
     * mapeia EstadiaEntity para EstadiaDTO
     */
    public static EstadiaDTO mapEstadiaEntityToEstadiaDTO(EstadiaEntity estadiaEntity) {
        if (estadiaEntity == null) {
            return null;
        }

        EstadiaDTO estadiaDTO = new EstadiaDTO();

        estadiaDTO.setIdEstadia(estadiaEntity.getIdEstadia());
        estadiaDTO.setDataEntrada(estadiaEntity.getDataEntrada());
        estadiaDTO.setDataSaida(estadiaEntity.getDataSaida());
        estadiaDTO.setValorTotal(estadiaEntity.getValorTotal());
        estadiaDTO.setEstadia(new ArrayList<>());

        // gerar 'token' de sessão
        estadiaDTO.generateSessionToken();

        return estadiaDTO;
    }

    /*
     * pega o objeto entity do db e converte para dto, para enviar pela API.
     * copia dados e os transporta
     */

    public  static List<UsuarioDTO> mapUsuarioEntityListToUsuarioDTOList(List<UsuarioEntity> usuarioEntityList){
        return usuarioEntityList.stream().map(u -> mapUsuarioEntityToUsuarioDTO(u)).collect(Collectors.toList());
    }

    public  static List<QuartoDTO> mapQuartoEntityListToQuartoDTOList(List<QuartoEntity> quartoEntityList){
        return quartoEntityList.stream().map(u -> mapQuartoEntityToQuartoDTO(u)).collect(Collectors.toList());
    }

    public  static List<EstadiaDTO> mapEstadiaEntityListToEstadiaDTOList(List<EstadiaEntity> estadiaEntityList){
        return estadiaEntityList.stream().map(u -> mapEstadiaEntityToEstadiaDTO(u)).collect(Collectors.toList());
    }

    public  static List<ClienteDTO> mapClienteEntityListToClienteDTOList(List<ClienteEntity> clienteEntityList){
        return clienteEntityList.stream().map(u -> mapClienteEntityToClienteDTO(u)).collect(Collectors.toList());
    }

    /**
     * mapeia QuartoEntity para QuartoDTO incluindo as estadias associadas
     * usado quando você precisa dos dados do quarto junto com seu histórico de estadias
     */
    public static QuartoDTO mapQuartoEntityToQuartoDTOPlusEstadia(QuartoEntity quartoEntity) {
        if (quartoEntity == null) {
            return null;
        }

        // primeiro mapeia os dados básicos do quarto
        QuartoDTO quartoDTO = new QuartoDTO();

        quartoDTO.setIdQuarto(quartoEntity.getId());
        quartoDTO.setNome(quartoEntity.getNome());
        quartoDTO.setPreco(quartoEntity.getPreco());
        quartoDTO.setImagem(quartoEntity.getImagem());
        quartoDTO.setDescricao(quartoEntity.getDescricao());

        // mapeia as estadias associadas ao quarto (se existirem)
        if (quartoEntity.getEstadias() != null && !quartoEntity.getEstadias().isEmpty()) {
            List<EstadiaDTO> estadiasDTO = quartoEntity.getEstadias().stream()
                    .map(estadia -> mapEstadiaEntityToEstadiaDTO(estadia))
                    .collect(Collectors.toList());
            quartoDTO.setEstadia(estadiasDTO);
        } else {
            quartoDTO.setEstadia(new ArrayList<>());
        }

        // gerar token de sessão
        quartoDTO.generateSessionToken();

        return quartoDTO;
    }

    public static EstadiaDTO mapEstadiaEntityToEstadiaDTOPlusEstadiaQuarto(EstadiaEntity estadia, boolean b) {
        return null;
    }
}