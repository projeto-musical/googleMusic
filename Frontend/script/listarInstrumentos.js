const API_URL = "http://localhost:8080/api/instrumentos";

// Executa assim que o HTML carregar
document.addEventListener("DOMContentLoaded", () => {
    carregarTemaSalvo();
    listarInstrumentos();
});

// --- 1. BUSCAR E LISTAR EM COLUNAS (TABELA) ---
function listarInstrumentos() {
    const tabela = document.getElementById("instrumentos-list");
    if (!tabela) return;

    fetch(API_URL)
        .then(res => {
            if (!res.ok) {
                throw new Error(`Erro na requisição. Status: ${res.status}`);
            }
            return res.json();
        })
        .then(data => {
            tabela.innerHTML = ""; // Limpa a tabela

            if (!data || data.length === 0) {
                tabela.innerHTML = `
                    <tr>
                        <td colspan="6" style="text-align: center; padding: 2rem; color: var(--text-muted);">
                            <i class="fa-solid fa-box-open" style="font-size: 1.5rem; display: block; margin-bottom: 8px;"></i>
                            Nenhum instrumento cadastrado.
                        </td>
                    </tr>
                `;
                return;
            }

            // Popula cada linha (tr) da tabela
            let html = "";
            data.forEach(item => {
                const id = item.id || item.idInstrumento || '-';
                const nomeModelo = item.nomeModelo || item.nome || '-';
                const familia = item.familia || '-';
                const marca = item.marca?.nome || item.nomeMarca || '-';
                const luthier = item.luthier?.nome || item.nomeLuthier || '-';

                html += `
                    <tr>
                        <td>${id}</td>
                        <td><strong>${nomeModelo}</strong></td>
                        <td>${familia}</td>
                        <td>${marca}</td>
                        <td>${luthier}</td>
                        <td class="text-center">
                            <button class="btn-action-delete" onclick="excluirInstrumento(${id})" title="Excluir">
                                <i class="fa-solid fa-trash"></i>
                            </button>
                        </td>
                    </tr>
                `;
            });

            tabela.innerHTML = html;
        })
        .catch(erro => {
            console.error("Erro na requisição:", erro);
            tabela.innerHTML = `
                <tr>
                    <td colspan="6" style="text-align: center; color: #ef4444; padding: 2rem;">
                        <i class="fa-solid fa-triangle-exclamation"></i> 
                        Não foi possível conectar à API (<strong>http://localhost:8080</strong>). Certifique-se de que o backend está rodando.
                    </td>
                </tr>
            `;
        });
}

// --- 2. EXCLUIR INSTRUMENTO ---
function excluirInstrumento(id) {
    if (!id || id === '-') return;

    if (confirm(`Deseja realmente excluir o instrumento de ID ${id}?`)) {
        fetch(`${API_URL}/${id}`, {
            method: "DELETE"
        })
        .then(res => {
            if (res.ok) {
                listarInstrumentos(); // Recarrega a tabela
            } else {
                alert("Erro ao tentar excluir o registro.");
            }
        })
        .catch(err => console.error("Erro ao deletar:", err));
    }
}

// --- 3. FILTRAR LINHAS DA TABELA ---
function filtrarTabela() {
    const input = document.getElementById("search-input").value.toLowerCase();
    const tabela = document.getElementById("instrumentos-list");
    const linhas = tabela.getElementsByTagName("tr");

    for (let i = 0; i < linhas.length; i++) {
        const textoLinha = linhas[i].innerText.toLowerCase();
        if (textoLinha.includes(input)) {
            linhas[i].style.display = "";
        } else {
            linhas[i].style.display = "none";
        }
    }
}

// --- 4. TEMA CLARO E ESCURO ---
function toggleTheme() {
    document.body.classList.toggle("dark-theme");
    const isDark = document.body.classList.contains("dark-theme");
    
    // Salva no navegador para manter ao mudar de página
    localStorage.setItem("theme", isDark ? "dark" : "light");
    atualizarIconeTema(isDark);
}

function carregarTemaSalvo() {
    const temaSalvo = localStorage.getItem("theme");
    if (temaSalvo === "dark") {
        document.body.classList.add("dark-theme");
        atualizarIconeTema(true);
    }
}

function atualizarIconeTema(isDark) {
    const icon = document.querySelector("#theme-toggle i");
    if (!icon) return;
    
    if (isDark) {
        icon.className = "fa-solid fa-sun";
    } else {
        icon.className = "fa-solid fa-moon";
    }
}