const API_URL = "http://localhost:8080/api/Instrumentos"; // Endpoint base da API

// Executa 'listarFilmes' assim que o HTML da página terminar de carregar
document.addEventListener("DOMContentLoaded", listarInstrumentos);

function listarFilmes() {
    fetch(API_URL) // Faz requisição GET para buscar os dados
        .then(res => res.json()) // Converte a resposta para JSON
        .then(instrumentos => {
            const lista = document.getElementById("listarInstrumentos");
            lista.innerHTML = ""; // Limpa a lista atual para evitar duplicação
            if (filmes.length === 0) {
                lista.innerHTML += `<p style="grid-column: 1/-1; text-align: center; color: #666;">Nenhum filme encontrado.</p>`;
                return;
            }

            // --- Renderização ---
            let html = "";
            instrumentos.forEach(f => {
                html += `
                  <div class="instrumento-card">
                    <div class="acoes-instrumento">
                    </div>

                    <img src="${f.urlCapa}" alt="${f.titulo}" onerror="this.src='https://via.placeholder.com/300x450?text=Sem+Capa'">
                    <strong>${f.nomeModelo}</strong><br>
                    <em>${f.descricao }</em><br>
                    (${f.numeroSerie || ""}) - ${f.Anofabricacao || ""}
                  </div>
                `;
            });

            lista.innerHTML += html; // Insere todos os cards no DOM
        });
}

