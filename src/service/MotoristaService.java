package service;

import exceptions.CpfFalhaException;
import exceptions.MotoristaInvalidoException;
import exceptions.MotoristaNaoEncontradoException;
import exceptions.SalvaFalhaException;
import model.Avaliacao;
import model.Motorista;
import static utils.VerificaCpf.verificaCPF;

import repository.AvaliacaoRepository;
import repository.ContaBancoRepository;
import repository.MotoristaRepository;
import repository.VeiculoRepository;

import java.util.List;
import java.util.Random;

public class MotoristaService {
    //aleterar para repositorio assim que criado
    private final MotoristaRepository motoristaRepository;
    private final ContaBancoRepository contaBancoRepository;
    private final VeiculoRepository veiculoRepository;
    private final AvaliacaoRepository avaliacaoRepository;

    public MotoristaService (MotoristaRepository motoristaRepository, ContaBancoRepository contaBancoRepository,
                             VeiculoRepository veiculoRepository, AvaliacaoRepository avaliacaoRepository) {
        this.motoristaRepository = motoristaRepository;
        this.contaBancoRepository = contaBancoRepository;
        this.veiculoRepository = veiculoRepository;
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public void cadastrarMotorista(Motorista motorista) {
        try {
            motoristaRepository.save(motorista);
            validarMotorista(motorista);
            System.out.println("Motorista adicionado com sucesso.");
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao adicionar Motorista.", e);
        }
    }

    public boolean validarMotorista(Motorista motorista){

        if(verificaCPF(motorista.getCpf()) && motorista.getHabilitacao().length() == 11){
            motorista.setValidado(true);
            motoristaRepository.save(motorista);
            return true;
        }else {
            motorista.setValidado(false);
            throw new MotoristaInvalidoException("Motorista inválido: CPF ou habilitação inválidos incorretos");
        }
    }

    public void adicionarAvaliacaoMotorista(Motorista motorista ,Avaliacao avaliacao){
        motorista.adicionarAvaliacao(avaliacao);
    }

    //aleterar para repositorio assim que criado
    public Motorista selecionarMotoristaAleatorio(){
        List<Motorista> motoristas = this.motoristaRepository.findALL();
        if(motoristas.isEmpty()){
            throw new MotoristaNaoEncontradoException("Nenhum motorista disponível");
        }

        Random random = new Random();
        int index = random.nextInt(this.motoristaRepository.findALL().size());

        return this.motoristaRepository.findALL().get(index);

    }

    public Motorista buscarMotoristaPorCpf(String cpf) {
        try {
            return motoristaRepository.motoristaFindByCpf(cpf);
        } catch (Exception e) {
            throw new CpfFalhaException("Motorista não encontrado com o CPF: " + cpf);
        }
    }

    public List<Motorista> listarMotoristas() {
        try {
            return motoristaRepository.findALL();
        } catch (Exception e) {
            throw new CpfFalhaException("Nenhum motorista encontrado.");
        }
    }

    public Motorista buscarMotoristaPorNome(String nome) {
        try {
            return motoristaRepository.motoristaFindByNome(nome);
        } catch (Exception e) {
            throw new CpfFalhaException("Motorista não encontrado com o nome: " + nome);
        }
    }

    public void removerMotorista(String cpf) {
        try {
            Motorista motorista = motoristaRepository.motoristaFindByCpf(cpf);
            if (motorista == null) {
                throw new MotoristaNaoEncontradoException("Motorista não encontrado com o CPF: " + cpf);
            }

            if (motorista.getConta() != null) {
                String conta = motorista.getConta().getNumeroConta();
                contaBancoRepository.deleteByNumero(conta);
            }

            if (motorista.getVeiculo() != null) {
                veiculoRepository.delete(motorista.getVeiculo());
            }

            if(motorista.getAvaliacoes() != null) {
                for (Avaliacao avaliacao : motorista.getAvaliacoes()) {
                    avaliacaoRepository.delete(avaliacao);
                }
            }

            motoristaRepository.deleteByCpf(cpf);
        } catch (MotoristaNaoEncontradoException e) {
            throw e; // Repassa a exceção específica
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao remover motorista.", e);
        }
    }

    public void atualizarMotorista(Motorista motorista) {
        try {
            motoristaRepository.save(motorista);
            System.out.println("Motorista atualizado com sucesso.");
        } catch (Exception e) {
            throw new SalvaFalhaException("Erro ao atualizar motorista.", e);
        }
    }
}
