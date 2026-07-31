const API_URL = "http://localhost:8080/api/instrumentos";

// Verifica se há parâmetros na URL (ex: cadastro.html?id=10)
const paramsUrl = new URLSearchParams(window.location.search);
const idEdicao = paramsUrl.get("id"); // Se existir, guarda o ID; se não, fica null

// 'async' permite usar 'await' dentro da função para esperar requisições terminarem
document.addEventListener("DOMContentLoaded", async () => {
    // 1. Carrega o <select> de gêneros primeiro. 
    // O 'await' é crucial aqui: garante que as opções existam antes de tentarmos selecionar uma na edição

    // 2. Se for Edição (tem ID na URL):
    if (idEdicao) {
        prepararModoEdicao(); // Ajusta textos e cores da tela
        await carregarinstrumento(id); // Busca os dados do filme e preenche os campos
    }

    // Adiciona o evento de salvar ao formulário
    document.getElementById("formCadastro").addEventListener("submit", salvarFilme);
});

async function carregarDadosFilme(id) {
    try {
        const res = await fetch(`${API_URL}/${id}`); // GET /filmes/10
        const filme = await res.json();

        // Preenche os inputs com os dados recebidos do backend
        document.getElementById("nomeModelo").value = filme.nomeModelo;
        document.getElementById("numeroSerie").value = filme.numeroserie;
        document.getElementById("Anofabricacao").value = filme.anofabricacao;
        document.getElementById("descricao").value = filme.descricao;
        
        // Se o filme tem gênero, seleciona automaticamente a option correta no <select>
       
    } catch (error) {
        alert("Erro ao buscar dados do filme: " + error);
        window.location.href = "filmes.html"; // Volta para listagem em caso de erro (ex: ID inválido)
    }
}

function salvarInstrumento(event) {
    event.preventDefault(); // Evita reload da página

    // Monta o objeto JSON. Nota: 'genero' é enviado como objeto { id: ... } 
    // para que o JPA no backend faça a associação correta.
    const instrumento = {
        Anofabricacao: document.getElementById("nomeModelo").value,
        Anofabricacao: document.getElementById("numeroSerie").value,
        descricao: document.getElementById("descricao").value,
        Anofabricacao: document.getElementById("Anofabricacao").value,
    };

    // LÓGICA PRINCIPAL:
    // Se tem idEdicao, usa PUT (atualizar). Se não, usa POST (criar).


    fetch(url, {
        method: metodo,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(instrumento)
    })
    .then(() => {
        alert("Filme cadastrado com sucesso!");
        window.location.href = "cadastroInstrumento.html"; // Redireciona para a lista
    })
    .catch(err => alert("Erro ao salvar: " + err));
}