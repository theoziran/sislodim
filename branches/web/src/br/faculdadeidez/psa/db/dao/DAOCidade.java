package br.faculdadeidez.psa.db.dao;

import java.util.List;
import java.util.Vector;

import br.faculdadeidez.psa.db.entity.Cidade;
import br.faculdadeidez.psa.vo.CidadeVO;
/**
 * Classe que contém todos os métodos específicos de Cidade
 *
 */
public class DAOCidade extends DAOFactory<Cidade> {
	/**
	 * Construtor default
	 */
	public DAOCidade() {
		super();
	}
	/**
	 * Encapsulamento do método find genérico
	 * Recebe a chave do objeto e procura a chave no banco de dados retornando uma entidade
	 * @param chave int
	 * @return CidadeVO
	 */
	public CidadeVO find(int chave){		
		return Cidade.VO(super.find(Cidade.class, chave));
	}
	
	/**
	 * Encapsulamento do método findByField genérico
	 * Recebe o nome do campo e o valor a ser pesquisado nele
	 * @param campo String
	 * @param valor String
	 * @return List<CidadeVO>
	 */
	public List<CidadeVO> findByField(String campo, String valor){
		return ConvertList(super.findByField(Cidade.class, campo, valor));
	}
	
	/**
	 * Encapsulamento do método findAll genérico
	 * Retorna todas as entidades como um select * from
	 * @return List<CidadeVO>
	 */
	public List<CidadeVO> findAll(){
		return ConvertList(super.findAll(Cidade.class));
	}
	/**
	 * Encapsulamento do método update genérico
	 * Atualiza a entidade no banco de dados
	 * @param vo CidadeVO
	 */
	public void update(CidadeVO vo){		
		super.update(new Cidade(vo));
	}
	/**
	 * Encapsulamento do método persist genérico
	 * Insere a entidade no banco de dados
	 * @param vo CidadeVO
	 */
	public void persist(CidadeVO vo){		
		super.persist(new Cidade(vo));
	}
	
	/**
	 * Encapsulamento do método remove genérico
	 * Remove a linha do banco de dados
	 * @param vo CidadeVO
	 */
	public void remove(CidadeVO vo){		
		super.remove(new Cidade(vo));
	}
	
	/**
	 * Método que converte uma lista de entidades em Value Object
	 * @param lista List<Cidade>
	 * @return List<CidadeVO>
	 */
	public List<CidadeVO> ConvertList(List<Cidade> lista)
	{
		List<CidadeVO> newLista = new Vector<CidadeVO>();
		for(Cidade set : lista)
			newLista.add(Cidade.VO(set));		
		return newLista;
	}
	
	/**
	 * Método que converte uma lista de Value Object em entidades
	 * @param lista List<CidadeVO>
	 * @return List<Cidade>
	 */
	public List<Cidade> ConverteEntidade(List<CidadeVO> lista)
	{
		List<Cidade> newLista = new Vector<Cidade>();
		for(CidadeVO cidade : lista)
			newLista.add(super.find(Cidade.class, cidade.getCodigo()));
		return newLista;
	}
}
