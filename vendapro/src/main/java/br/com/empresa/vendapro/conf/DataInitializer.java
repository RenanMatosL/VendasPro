package br.com.empresa.vendapro.conf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.empresa.vendapro.dao.CategoriaDao;
import br.com.empresa.vendapro.model.Categoria;
import br.com.empresa.vendapro.model.Subcategoria;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private CategoriaDao categoriaDao;

	@Override
	public void run(String... args) throws Exception {

		// Criar categorias
		Categoria acessoriosVeiculos = new Categoria();
		acessoriosVeiculos.setNomeCategoria("Acessórios para Veículos");
		
		List<Subcategoria> subcategoriasVeiculos = List.of(
				new Subcategoria("Aces. de Carros e Caminhonetes", acessoriosVeiculos),
				new Subcategoria("Aces. de Motos e Quadriciclos", acessoriosVeiculos),
				new Subcategoria("Acessórios Náuticos", acessoriosVeiculos),
				new Subcategoria("Acessórios de Linha Pesada", acessoriosVeiculos),
				new Subcategoria("Ferramentas para Veículos", acessoriosVeiculos),
				new Subcategoria("GNV", acessoriosVeiculos),
				new Subcategoria("Limpeza Automotiva", acessoriosVeiculos),
				new Subcategoria("Lubrificantes e Fluidos", acessoriosVeiculos),
				new Subcategoria("Motos", acessoriosVeiculos),
				new Subcategoria("Navegadores GPS para Vehículos", acessoriosVeiculos),
				new Subcategoria("Outros", acessoriosVeiculos),
				new Subcategoria("Performance", acessoriosVeiculos),
				new Subcategoria("Peças Náuticas", acessoriosVeiculos),
				new Subcategoria("Peças de Carros e Caminhonetes", acessoriosVeiculos),
				new Subcategoria("Peças de Linha Pesada", acessoriosVeiculos),
				new Subcategoria("Peças de Motos e Quadriciclos", acessoriosVeiculos),
				new Subcategoria("Pneus e Acessórios", acessoriosVeiculos),
				new Subcategoria("Rodas", acessoriosVeiculos),
				new Subcategoria("Segurança Veicular", acessoriosVeiculos),
				new Subcategoria("Serviços Programados", acessoriosVeiculos),
				new Subcategoria("Som Automotivo", acessoriosVeiculos),
				new Subcategoria("Tags de Pagamento de Pedágio", acessoriosVeiculos),
				new Subcategoria("Tuning", acessoriosVeiculos),
				new Subcategoria("", acessoriosVeiculos)
				);
		
		// Adicionando subcategorias à categoria
		acessoriosVeiculos.setSubcategorias(subcategoriasVeiculos);
		categoriaDao.salvarCategoria(acessoriosVeiculos);

		
		
		
		
		//* Criar categorias
		Categoria agro = new Categoria();
		agro.setNomeCategoria("Agro");
		
		// Criar subcategorias para "Agro"
		List<Subcategoria> subcategoriasAgro = List.of(
				new Subcategoria("Agricultura de Precisão", agro),
				new Subcategoria("Animais", agro),
				new Subcategoria("Apicultura", agro),
				new Subcategoria("Armezenamento", agro),
				new Subcategoria("Energia Renovável", agro),
				new Subcategoria("Ferramentas de Trabalho", agro),
				new Subcategoria("Fertilizantes", agro),
				new Subcategoria("Infra-estrutura Rural", agro),
				new Subcategoria("Insumos Agrícolas", agro),
				new Subcategoria("Insumos Gadeiros", agro),
				new Subcategoria("Irrigação", agro),
				new Subcategoria("Lubrificantes e Fluidos", agro),
				new Subcategoria("Maquinaria Agrícola", agro),
				new Subcategoria("Máquinas Forrageiras", agro),
				new Subcategoria("Outros", agro),
				new Subcategoria("Peças Maquinaria Agrícola", agro),
				new Subcategoria("Produçao Animal", agro),
				new Subcategoria("Proteção de Culturas", agro),
				new Subcategoria("Sementes Agrícolas", agro),
				new Subcategoria("", agro),
				new Subcategoria("Bebidas", agro),
				new Subcategoria("Comida Preparada", agro),
				new Subcategoria("Congelados", agro),
				new Subcategoria("Frescos", agro),
				new Subcategoria("Kefir", agro),
				new Subcategoria("Mercearia", agro),
				new Subcategoria("Outros", agro),
				new Subcategoria("", agro)
				
				);
		
		// Adicionando subcategorias à categoria agro
				agro.setSubcategorias(subcategoriasAgro);
				categoriaDao.salvarCategoria(agro);
				
		
				
				
				
				
		//* Criando categoria alimentosEBebidas
		Categoria alimentosEBebidas = new Categoria();
		
		//Setando o nome da Categoria alimentosEBebidas no BD
		alimentosEBebidas.setNomeCategoria("Alimentos e Bebidas");
		
		// Criando lista de subcategorias para "alimentosEBebidas"
		List<Subcategoria> subcategoriasAlimentosEBebidas = List.of(
						new Subcategoria("Bebidas", alimentosEBebidas),
						new Subcategoria("Comida Preparada", alimentosEBebidas),
						new Subcategoria("Congelados", alimentosEBebidas),
						new Subcategoria("Frescos", alimentosEBebidas),
						new Subcategoria("Kefir", alimentosEBebidas),
						new Subcategoria("Mercearia", alimentosEBebidas),
						new Subcategoria("Outros", alimentosEBebidas)
						);
				
		// Setando subcategorias à categoria alimentosEBebidas
		alimentosEBebidas.setSubcategorias(subcategoriasAlimentosEBebidas);
		
		// Salvando subcategorias à categoria alimentosEBebidas
		categoriaDao.salvarCategoria(alimentosEBebidas);
				
				
		//* Criando categoria Animais
		Categoria animais = new Categoria();
		
		//Setando o nome da categoria criada no BD
		animais.setNomeCategoria("Animais");
				
		// Criando lista de subcategorias para "alimentosEBebidas"
		List<Subcategoria> subcategoriasAnimais = List.of(
						
						//inserindo subcategorias, seguida do nome da categoria criada.
						new Subcategoria("Anfíbios e Répteis", animais),
						new Subcategoria("Aves e Acessórios", animais),
						new Subcategoria("Cavalos", animais),
						new Subcategoria("Coelhos", animais),
						new Subcategoria("Cães", animais),
						new Subcategoria("Gaiolas para Animais", animais),
						new Subcategoria("Gatos", alimentosEBebidas),
						new Subcategoria("Guias para Animais", animais),
						new Subcategoria("Insetos", animais),
						new Subcategoria("Outros", animais),
						new Subcategoria("Peixes", animais),
						new Subcategoria("Roedores", animais),
						new Subcategoria("", animais)
						);
				
		// Salvando subcategorias à categoria animais no BD
		animais.setSubcategorias(subcategoriasAnimais);
		categoriaDao.salvarCategoria(animais);

		
		
				
		//* Criando categoria antiguidadesEColeções
		Categoria antiguidadesEColeções = new Categoria();
		
		//Setando o nome da categoria criada no BD
		antiguidadesEColeções.setNomeCategoria("Antiguidades e Coleções");
		
		// Criando lista de subcategorias para "antiguidadesEColeções"
		List<Subcategoria> subcategoriasAntiguidadesEColeções = List.of(
				
				//inserindo subcategorias, seguida do nome da categoria criada.
				new Subcategoria("Antiguidades", antiguidadesEColeções),
				new Subcategoria("Bandeiras", antiguidadesEColeções),
				new Subcategoria("Colecionáveis de Esportes", antiguidadesEColeções),
				new Subcategoria("Cédulas e Moedas", antiguidadesEColeções),
				new Subcategoria("Esculturas", antiguidadesEColeções),
				new Subcategoria("Filatelia", antiguidadesEColeções),
				new Subcategoria("Militaria e Afins", antiguidadesEColeções),
				new Subcategoria("Outras Antiguidades", antiguidadesEColeções),
				new Subcategoria("Pôsteres", antiguidadesEColeções),
				new Subcategoria("Arte", antiguidadesEColeções),
				new Subcategoria("", antiguidadesEColeções)
				);
		
		// Carregando (setando) subcategorias à categoria no BD
		antiguidadesEColeções.setSubcategorias(subcategoriasAntiguidadesEColeções);
		
		// Salvando subcategorias à categoria no BD
		categoriaDao.salvarCategoria(antiguidadesEColeções);
		
				
		
			
		// Criando categoria 
		Categoria pessoal = new Categoria();
				
		//Setando o nome da categoria criada no BD
		pessoal.setNomeCategoria("Pessoal");
				
		// Criando lista de subcategorias para "Pessoal"
		List<Subcategoria> subcategoriasPessoal = List.of(
						
				//inserindo subcategorias, e em seguida o nome da categoria criada ao qual ela pertence.
				new Subcategoria("Artefatos para Cabelo", pessoal),
				new Subcategoria("Artigos para Cabeleireiros", pessoal),
				new Subcategoria("Barbearia", pessoal),
				new Subcategoria("Cuidados com a Pele", pessoal),
				new Subcategoria("Cuidados com o Cabelo", pessoal),
				new Subcategoria("Depilação", pessoal),
				new Subcategoria("Farmácia", pessoal),
				new Subcategoria("Higiene Pessoal", pessoal),
				new Subcategoria("Manicure e Pedicure", pessoal),
				new Subcategoria("Maquiagem", pessoal),
				new Subcategoria("Outros", pessoal),
				new Subcategoria("Perfumes", pessoal),
				new Subcategoria("Tratamentos de Beleza", pessoal),
				new Subcategoria("", pessoal)
						);
				
		// Carregando subcategorias à categoria alimentosEBebidas no BD
		pessoal.setSubcategorias(subcategoriasPessoal);
		
		// Salvando subcategorias à categoria alimentosEBebidas no BD
		categoriaDao.salvarCategoria(pessoal);
		

		//* Criando categoria 
		Categoria brinquedosEHobbies = new Categoria();
		
		//Setando o nome da categoria criada no BD
		brinquedosEHobbies.setNomeCategoria("Brinquedos e Hobbies");
		
		// Criando lista de subcategorias para "alimentosEBebidas"
		List<Subcategoria> subcategoriasBrinquedosEHobbies = List.of(
				
				//inserindo subcategorias, e em seguida o nome da categoria criada ao qual essas subcategorias são pertencentes.
				new Subcategoria("Anti-stress e Engenho", brinquedosEHobbies),
				new Subcategoria("Ar Livre e Playground", brinquedosEHobbies),
				new Subcategoria("Artes e Atividades", brinquedosEHobbies),
				new Subcategoria("Bonecos e Bonecas", brinquedosEHobbies),
				new Subcategoria("Brinquedos Eletrônicos", brinquedosEHobbies),
				new Subcategoria("Brinquedos de Faz de Conta", brinquedosEHobbies),
				new Subcategoria("Brinquedos de Montar", brinquedosEHobbies),
				new Subcategoria("Brinquedos de Pegadinhas", brinquedosEHobbies),
				new Subcategoria("Brinquedos de Praia e Piscina", brinquedosEHobbies),
				new Subcategoria("Brinquedos para Bebês", brinquedosEHobbies),
				new Subcategoria("Casinhas e Barracas", brinquedosEHobbies),
				new Subcategoria("Fantoches e Marionetas", brinquedosEHobbies),
				new Subcategoria("Hobbies", brinquedosEHobbies),
				new Subcategoria("Instrumentos Musicais", brinquedosEHobbies),
				new Subcategoria("Jogos de Salão", brinquedosEHobbies),
				new Subcategoria("Jogos de Tabuleiro e Cartas", brinquedosEHobbies),
				new Subcategoria("Lançadores de Brinquedo", brinquedosEHobbies),
				new Subcategoria("Mesas e Cadeiras", brinquedosEHobbies),
				new Subcategoria("Mini Veículos e Bicicletas", brinquedosEHobbies),
				new Subcategoria("Outros", brinquedosEHobbies),
				new Subcategoria("Patins e Skates", brinquedosEHobbies),
				new Subcategoria("Pelúcias", brinquedosEHobbies),
				new Subcategoria("Piscinas de Bolas e Infláveis", brinquedosEHobbies),
				new Subcategoria("Veículos de Brinquedo", brinquedosEHobbies),
				new Subcategoria("Álbuns e Figurinhas", brinquedosEHobbies),
				new Subcategoria("Calçados", brinquedosEHobbies),
				new Subcategoria("", brinquedosEHobbies)
				);
		
		// Salvando subcategorias à categoria alimentosEBebidas no BD
		brinquedosEHobbies.setSubcategorias(subcategoriasBrinquedosEHobbies);
		categoriaDao.salvarCategoria(brinquedosEHobbies);


		
		//* Criando categoria RoupasEBolsas
		Categoria roupasEBolsas = new Categoria();
				
		//Setando o nome da categoria criada no BD
		roupasEBolsas.setNomeCategoria("Roupas e Bolsas");
				
		// Criando lista de subcategorias para "RoupasEBolsas"
		List<Subcategoria> subcategoriasRoupasEBolsas = List.of(
				
				//inserindo subcategorias, e em seguida o nome da categoria criada ao qual essas subcategorias são pertencentes.
				new Subcategoria("Acessórios de Moda", roupasEBolsas),
				new Subcategoria("Agasalhos", roupasEBolsas),
				new Subcategoria("Bermudas e Shorts", roupasEBolsas),
				new Subcategoria("Blusas", roupasEBolsas),
				new Subcategoria("Calçados", roupasEBolsas),
				new Subcategoria("Calças", roupasEBolsas),
				new Subcategoria("Camisas", roupasEBolsas),
				new Subcategoria("Camisetas e Regatas", roupasEBolsas),
				new Subcategoria("Indumentária Laboral e Escolar", roupasEBolsas),
				new Subcategoria("Kimonos", roupasEBolsas),
				new Subcategoria("Kits de Conjuntos de Roupa", roupasEBolsas),
				new Subcategoria("Leggings", roupasEBolsas),
				new Subcategoria("Macacão", roupasEBolsas),
				new Subcategoria("Malas e Bolsas", roupasEBolsas),
				new Subcategoria("Moda Fitness", roupasEBolsas),
				new Subcategoria("Moda Praia", roupasEBolsas),
				new Subcategoria("Moda Íntima e Lingerie", roupasEBolsas),
				new Subcategoria("Outros", roupasEBolsas),
				new Subcategoria("Roupas para Bebês", roupasEBolsas),
				new Subcategoria("Saias", roupasEBolsas),
				new Subcategoria("Ternos", roupasEBolsas),
				new Subcategoria("Vestidos", roupasEBolsas),
				new Subcategoria("", roupasEBolsas)
				);
				
		// Salvando subcategorias à categoria alimentosEBebidas no BD
		roupasEBolsas.setSubcategorias(subcategoriasRoupasEBolsas);
		categoriaDao.salvarCategoria(roupasEBolsas);
		
				
				
		//* Criando categoria 
		Categoria camerasEAcessorios = new Categoria();
		
		//Setando o nome da categoria criada no BD
		camerasEAcessorios.setNomeCategoria("Câmeras e Acessórios");
				
		// Criando lista de subcategorias 
		List<Subcategoria> subcategoriasCamerasEAcessorios = List.of(
						
				//inserindo subcategorias, e em seguida o nome da categoria criada ao qual essas subcategorias são pertencentes.
				new Subcategoria("Adultos", camerasEAcessorios),
				new Subcategoria("Artigos para Fumadores", camerasEAcessorios),
				new Subcategoria("Birutas de Vento", camerasEAcessorios),
				new Subcategoria("Coberturas Estendidas", camerasEAcessorios),
				new Subcategoria("Criptomoedas", camerasEAcessorios),
				new Subcategoria("Equipamento para Tatuagens", camerasEAcessorios),
				new Subcategoria("Esoterismo e Ocultismo", camerasEAcessorios),
				new Subcategoria("Fornos Crematórios", camerasEAcessorios),
				new Subcategoria("Gift Cards", camerasEAcessorios),
				new Subcategoria("Kits de Criminologia", camerasEAcessorios),
				new Subcategoria("Licenças para Taxis", camerasEAcessorios),
				new Subcategoria("Outros", camerasEAcessorios)
				);
				
		// Salvando subcategorias à categoria alimentosEBebidas no BD
		camerasEAcessorios.setSubcategorias(subcategoriasCamerasEAcessorios);
		categoriaDao.salvarCategoria(camerasEAcessorios);
			
				
				
		//* Criando categoria motoEOutros
		Categoria motoEOutros = new Categoria();
				
		//Setando o nome da categoria criada no BD
		motoEOutros.setNomeCategoria("Motos e Outros");
		
		// Criando lista de subcategorias 
		List<Subcategoria> subcategoriasMotoEOutros = List.of(
						
				//inserindo subcategorias, e em seguida o nome da categoria criada ao qual essas subcategorias são pertencentes.
				new Subcategoria("Caminhões", motoEOutros),
				new Subcategoria("Carros Antigos", motoEOutros),
				new Subcategoria("Carros e Caminhonetes", motoEOutros),
				new Subcategoria("Consórcios", motoEOutros),
				new Subcategoria("Motorhomes", motoEOutros),
				new Subcategoria("Motos", motoEOutros),
				new Subcategoria("Náutica", motoEOutros),
				new Subcategoria("Outros Veículos", motoEOutros),
				new Subcategoria("Veículos Pesados", motoEOutros),
				new Subcategoria("Ônibus", motoEOutros),
				new Subcategoria("Casa", motoEOutros),
				new Subcategoria("", motoEOutros)
				);
				
		// Salvando subcategorias à categoria no BD
		motoEOutros.setSubcategorias(subcategoriasMotoEOutros);
		categoriaDao.salvarCategoria(motoEOutros);
		
		
		//* Criando categoria 
		Categoria moveisEDecoracao = new Categoria();
		
		//Setando o nome da categoria criada no BD
		moveisEDecoracao.setNomeCategoria("Móveis e Decoração");
		
		// Criando lista de subcategorias 
		List<Subcategoria> subcategoriasMoveisEDecoracao = List.of(
				
				//inserindo subcategorias, e em seguida o nome da categoria 
				new Subcategoria("Banheiros", moveisEDecoracao),
				new Subcategoria("Camas", moveisEDecoracao),
				new Subcategoria("Colchões e Acessórios", moveisEDecoracao),
				new Subcategoria("Cozinha", moveisEDecoracao),
				new Subcategoria("Cuidado da Casa e Lavanderia", moveisEDecoracao),
				new Subcategoria("Enfeites e Decoração da Casa", moveisEDecoracao),
				new Subcategoria("Iluminação Residencial", moveisEDecoracao),
				new Subcategoria("Jardim e Ar Livre", moveisEDecoracao),
				new Subcategoria("Móveis para Casa", moveisEDecoracao),
				new Subcategoria("Organização para Casa", moveisEDecoracao),
				new Subcategoria("Outros", moveisEDecoracao),
				new Subcategoria("Segurança para Casa", moveisEDecoracao),
				new Subcategoria("Têxteis de Casa e Decoração", moveisEDecoracao)
				);
		
		// Salvando subcategorias em categoria 			
		moveisEDecoracao.setSubcategorias(subcategoriasMoveisEDecoracao);
		categoriaDao.salvarCategoria(moveisEDecoracao);
		

		//* Criando categoria 
		Categoria celularesETelefones = new Categoria();
		
		//Setando o nome da categoria criada no BD
		celularesETelefones.setNomeCategoria("Celulares e Telefones");
		
		// Criando lista de subcategorias 
		List<Subcategoria> subcategoriasCelularesETelefones = List.of(
				
				//inserindo subcategorias, e em seguida o nome da categoria 
				new Subcategoria("Acessórios para Celulares", celularesETelefones),
				new Subcategoria("Celulares e Smartphones", celularesETelefones),
				new Subcategoria("Outros", celularesETelefones),
				new Subcategoria("Peças para Celular", celularesETelefones),
				new Subcategoria("Rádio Comunicadores", celularesETelefones),
				new Subcategoria("Smartwatches e Acessórios", celularesETelefones),
				new Subcategoria("Tarifadores e Cabines", celularesETelefones),
				new Subcategoria("Telefonia Fixa e Sem Fio", celularesETelefones),
				new Subcategoria("VoIP", celularesETelefones),
				new Subcategoria("Óculos de Realidade Virtual", celularesETelefones)
				);
		
		// Salvando subcategorias em categoria 		
		celularesETelefones.setSubcategorias(subcategoriasCelularesETelefones);
		categoriaDao.salvarCategoria(celularesETelefones);
		
		
		//* Criando categoria 
		Categoria construcao = new Categoria();
				
			//Setando o nome da categoria criada no BD
		construcao.setNomeCategoria("Construção");
			
			// Criando lista de subcategorias 
			List<Subcategoria> subcategoriasConstrucao = List.of(
					
					//inserindo subcategorias, e em seguida o nome da categoria 
					new Subcategoria("Aberturas", construcao),
					new Subcategoria("Acessórios de Construção", construcao),
					new Subcategoria("Encanamento", construcao),
					new Subcategoria("Energia", construcao),
					new Subcategoria("Loja das Tintas", construcao),
					new Subcategoria("Materiais de Obra", construcao),
					new Subcategoria("Mobiliário para Banheiros", construcao),
					new Subcategoria("Mobiliário para Cozinhas", construcao),
					new Subcategoria("Máquinas para Construção", construcao),
					new Subcategoria("Outros", construcao),
					new Subcategoria("Pisos e Rejuntes", construcao)
					);
			
		// Setando subcategorias em categoria 	
		construcao.setSubcategorias(subcategoriasConstrucao);
		// Salvando subcategorias em categoria 	
		categoriaDao.salvarCategoria(construcao);
			
		//* Criando categoria 
		Categoria eletrodomesticos = new Categoria();
					
		//Setando o nome da categoria criada no BD
		eletrodomesticos.setNomeCategoria("Eletrodomesticos");
				
				// Criando lista de subcategorias 
		List<Subcategoria> subcategoriasEletrodomesticos = List.of(
						
					//inserindo subcategorias, e em seguida o nome da categoria 
					new Subcategoria("Ar e Ventilação", eletrodomesticos),
					new Subcategoria("Bebedouros e Purificadores", eletrodomesticos),
					new Subcategoria("Cuidado Pessoal", eletrodomesticos),
					new Subcategoria("Fornos e Fogões", eletrodomesticos),
					new Subcategoria("Lavadores", eletrodomesticos),
					new Subcategoria("Outros", eletrodomesticos),
					new Subcategoria("Pequenos Eletrodomésticos", eletrodomesticos),
					new Subcategoria("Refrigeração", eletrodomesticos),
					new Subcategoria("Eletrônicos", eletrodomesticos)
					);
				
		// Setando subcategorias em categoria 	
		eletrodomesticos.setSubcategorias(subcategoriasEletrodomesticos);
		// Salvando subcategorias em categoria 	
		categoriaDao.salvarCategoria(eletrodomesticos);
				
		//* Criando categoria 
		Categoria audioEVideo = new Categoria();
					
		//Setando o nome da categoria criada no BD
		audioEVideo.setNomeCategoria("Áudio e Vídeo");
					
		// Criando lista de subcategorias 
		List<Subcategoria> subcategoriasAudioEVideo = List.of(
						
				//inserindo subcategorias, e em seguida o nome da categoria 
				new Subcategoria("Acessórios para TV", audioEVideo),
				new Subcategoria("Acessórios para Áudio e Vídeo", audioEVideo),
				new Subcategoria("Aparelhos DVD e Bluray", audioEVideo),
				new Subcategoria("Bolsas e Estojos", audioEVideo),
				new Subcategoria("Cabos", audioEVideo),
				new Subcategoria("Componentes Eletrônicos", audioEVideo),
				new Subcategoria("Controles Remotos", audioEVideo),
				new Subcategoria("Drones e Acessórios", audioEVideo),
				new Subcategoria("Media Streaming", audioEVideo),
				new Subcategoria("Outros Eletrônicos", audioEVideo),
				new Subcategoria("Peças para TV", audioEVideo),
				new Subcategoria("Pilhas e Carregadores", audioEVideo),
				new Subcategoria("Projetores e Telas", audioEVideo),
				new Subcategoria("Televisores", audioEVideo),
				new Subcategoria("Áudio", audioEVideo)
				);
					
		// Setando subcategorias em categoria 	
		audioEVideo.setSubcategorias(subcategoriasAudioEVideo);
		// Salvando subcategorias em categoria 	
		categoriaDao.salvarCategoria(audioEVideo);
		
		//* Criando categoria 
		Categoria esportesEFitness = new Categoria();
			
		//Setando o nome da categoria criada no BD
		esportesEFitness.setNomeCategoria("Esportes e Fitness");
		
		// Criando lista de subcategorias 
		List<Subcategoria> subcategoriasEsportesEFitness = List.of(
				
				//inserindo subcategorias, e em seguida o nome da categoria 
				new Subcategoria("Artes Marciais e Boxe", audioEVideo),
				new Subcategoria("Badminton", audioEVideo),
				new Subcategoria("Baseball e Softball", audioEVideo),
				new Subcategoria("Basquete", audioEVideo),
				new Subcategoria("Camping", audioEVideo),
				new Subcategoria("Caça e Pesca", audioEVideo),
				new Subcategoria("Canoas", audioEVideo),
				new Subcategoria("Caiaques e Infláveis", audioEVideo),
				new Subcategoria("Ciclismo", audioEVideo),
				new Subcategoria("Equitação", audioEVideo),
				new Subcategoria("Esgrima", audioEVideo),
				new Subcategoria("Esqui e Snowboard", audioEVideo),
				new Subcategoria("Fitness e Musculação", audioEVideo),
				new Subcategoria("Futebol", audioEVideo),
				new Subcategoria("Futebol Americano", audioEVideo),
				new Subcategoria("Golfe", audioEVideo),
				new Subcategoria("Handebol", audioEVideo),
				new Subcategoria("Hóquei", audioEVideo),
				new Subcategoria("Jogos de Salão", audioEVideo),
				new Subcategoria("Kitesurf", audioEVideo),
				new Subcategoria("Mergulho", audioEVideo),
				new Subcategoria("Moda Fitness", audioEVideo),
				new Subcategoria("Monitores Esportivos", audioEVideo),
				new Subcategoria("Natação", audioEVideo),
				new Subcategoria("Outros", audioEVideo),
				new Subcategoria("Paintball", audioEVideo),
				new Subcategoria("Parapente", audioEVideo),
				new Subcategoria("Patinetes e Scooters", audioEVideo),
				new Subcategoria("Patín e Skateboard", audioEVideo),
				new Subcategoria("Pilates e Yoga", audioEVideo),
				new Subcategoria("Rapel", audioEVideo),
				new Subcategoria("Montanhismo e Escalada", audioEVideo),
				new Subcategoria("Rugby", audioEVideo),
				new Subcategoria("Slackline", audioEVideo),
				new Subcategoria("Suplementos e Shakers", audioEVideo),
				new Subcategoria("Surf e Bodyboard", audioEVideo),
				new Subcategoria("Tiro Esportivo", audioEVideo),
				new Subcategoria("Tênis", audioEVideo),
				new Subcategoria("Tênis", audioEVideo),
				new Subcategoria("Paddle e Squash", audioEVideo),
				new Subcategoria("Vôlei", audioEVideo),
				new Subcategoria("Wakeboard e Esquí Acuático", audioEVideo),
				new Subcategoria("Windsurfe", audioEVideo)
				);
		
		// Setando subcategorias em categoria 	
		esportesEFitness.setSubcategorias(subcategoriasEsportesEFitness);
		// Salvando subcategorias em categoria 	
		categoriaDao.salvarCategoria(esportesEFitness);
		
		
		//* Criando categoria 
		Categoria ferramentas = new Categoria();
			
		//Setando o nome da categoria criada no BD
		ferramentas.setNomeCategoria("Ferramentas");
		
		// Criando lista de subcategorias 
		List<Subcategoria> subcategoriasFerramentas = List.of(
				
				//inserindo subcategorias, e em seguida o nome da categoria 
				new Subcategoria("Acessórios para Ferramentas", audioEVideo),
				new Subcategoria("Caixas e Organizadores", audioEVideo),
				new Subcategoria("Ferramentas Elétricas", audioEVideo),
				new Subcategoria("Ferramentas Industriais", audioEVideo),
				new Subcategoria("Ferramentas Manuais", audioEVideo),
				new Subcategoria("Ferramentas Pneumáticas", audioEVideo),
				new Subcategoria("Ferramentas para Jardim", audioEVideo),
				new Subcategoria("Medições e Instrumentação", audioEVideo),
				new Subcategoria("Outros", audioEVideo)
				);
		
		// Setando subcategorias em categoria 	
		ferramentas.setSubcategorias(subcategoriasFerramentas);
		// Salvando subcategorias em categoria 	
		categoriaDao.salvarCategoria(ferramentas);
		
					

	}
}