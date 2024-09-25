package br.com.empresa.vendapro.validacao;

import jakarta.validation.GroupSequence;

@GroupSequence({ ValidacaoCadastro.class, ValidacaoAlteracao.class })
public interface SequenciaValidacaoPadrao {

}
