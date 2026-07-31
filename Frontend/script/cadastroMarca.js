const API_URL = "http://localhost:8080/api/marca";

document.addEventListener("DOMContentLoaded", async () => {
    const paramsUrl = new URLSearchParams(window.location.search);
    const idEdicao = paramsUrl.get("id"); 

    if (idEdicao) {
        if (typeof prepararModoEdicao === 'function') {
            prepararModoEdicao(idEdicao);
        }
        await carregarMarca(idEdicao);
    }

    const form = document.getElementById("formCadastro");
    if (form) {
        form.addEventListener("submit", salvarMarca);
    }
});

// Busca os dados da marca para preencher o formulário na edição
async function carregarMarca(id) {
    try {
        const res = await fetch(`${API_URL}/${id}`); 
        if (!res.ok) throw new Error("Erro ao buscar dados no servidor.");
        
        const marca = await res.json();

        // Preenche os inputs do HTML
        document.getElementById("nome").value = marca.nome || "";
        // Trata caso o back-end retorne em camelCase (paisOrigem) ou snake_case (pais_origem)
        document.getElementById("paisOrigem").value = marca.paisOrigem || marca.pais_origem || "";
        
    } catch (error) {
        alert("Erro ao carregar dados da marca: " + error.message);
        window.location.href = "marcas.html";
    }
}

// Salva (POST) ou Atualiza (PUT) no Banco de Dados
async function salvarMarca(event) {
    event.preventDefault(); 

    const paramsUrl = new URLSearchParams(window.location.search);
    const idEdicao = paramsUrl.get("id"); 

    // Pega os valores digitados nos inputs da tela
    const nomeInput = document.getElementById("nome").value;
    const paisInput = document.getElementById("paisOrigem").value;

    // Objeto JSON enviado para a API
    const marca = {
        nome: nomeInput,
        paisOrigem: paisInput, // Se o seu Model/DTO no Java/Node usa snake_case, mude para: pais_origem: paisInput
    };

    const url = idEdicao ? `${API_URL}/${idEdicao}` : API_URL;
    const metodo = idEdicao ? "PUT" : "POST";

    try {
        const res = await fetch(url, {
            method: metodo,
            headers: { 
                "Content-Type": "application/json" 
            },
            body: JSON.stringify(marca)
        });

        if (!res.ok) {
            throw new Error("Não foi possível salvar no banco de dados.");
        }

        alert(idEdicao ? "Marca atualizada com sucesso!" : "Marca cadastrada com sucesso!");
        window.location.href = "marcas.html";

    } catch (err) {
        alert("Erro ao salvar marca: " + err.message);
    }
}